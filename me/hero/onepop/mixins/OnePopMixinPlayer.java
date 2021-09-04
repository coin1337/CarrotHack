package me.hero.onepop.mixins;

import me.hero.onepop.onepopclient.event.OnePopEventBus;
import me.hero.onepop.onepopclient.event.events.OnePopEventPlayerTravel;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityPlayer.class})
public class OnePopMixinPlayer extends OnePopMixinEntity {
   @Inject(
      method = {"travel"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void travel(float strafe, float vertical, float forward, CallbackInfo info) {
      OnePopEventPlayerTravel event_packet = new OnePopEventPlayerTravel(strafe, vertical, forward);
      OnePopEventBus.EVENT_BUS.post(event_packet);
      if (event_packet.isCancelled()) {
         this.func_70091_d(MoverType.SELF, this.field_70159_w, this.field_70181_x, this.field_70179_y);
         info.cancel();
      }

   }
}
