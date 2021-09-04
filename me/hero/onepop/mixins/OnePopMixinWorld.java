package me.hero.onepop.mixins;

import me.hero.onepop.onepopclient.event.OnePopEventBus;
import me.hero.onepop.onepopclient.event.events.OnePopEventEntityRemoved;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({World.class})
public class OnePopMixinWorld {
   @Inject(
      method = {"onEntityRemoved"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void onEntityRemoved(Entity event_packet, CallbackInfo p_Info) {
      OnePopEventEntityRemoved l_Event = new OnePopEventEntityRemoved(event_packet);
      OnePopEventBus.EVENT_BUS.post(l_Event);
   }
}
