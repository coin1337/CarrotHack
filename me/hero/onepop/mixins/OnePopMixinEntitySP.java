package me.hero.onepop.mixins;

import me.hero.onepop.onepopclient.event.OnePopEventBus;
import me.hero.onepop.onepopclient.event.events.OnePopEventMotionUpdate;
import me.hero.onepop.onepopclient.event.events.OnePopEventMove;
import me.hero.onepop.onepopclient.event.events.OnePopEventSwing;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.MoverType;
import net.minecraft.util.EnumHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityPlayerSP.class})
public class OnePopMixinEntitySP extends OnePopMixinEntity {
   @Inject(
      method = {"move"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void move(MoverType type, double x, double y, double z, CallbackInfo info) {
      OnePopEventMove event = new OnePopEventMove(type, x, y, z);
      OnePopEventBus.EVENT_BUS.post(event);
      if (event.isCancelled()) {
         super.func_70091_d(type, event.get_x(), event.get_y(), event.get_z());
         info.cancel();
      }

   }

   @Inject(
      method = {"onUpdateWalkingPlayer"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void OnPreUpdateWalkingPlayer(CallbackInfo p_Info) {
      OnePopEventMotionUpdate l_Event = new OnePopEventMotionUpdate(0);
      OnePopEventBus.EVENT_BUS.post(l_Event);
      if (l_Event.isCancelled()) {
         p_Info.cancel();
      }

   }

   @Inject(
      method = {"onUpdateWalkingPlayer"},
      at = {@At("RETURN")},
      cancellable = true
   )
   public void OnPostUpdateWalkingPlayer(CallbackInfo p_Info) {
      OnePopEventMotionUpdate l_Event = new OnePopEventMotionUpdate(1);
      OnePopEventBus.EVENT_BUS.post(l_Event);
      if (l_Event.isCancelled()) {
         p_Info.cancel();
      }

   }

   @Inject(
      method = {"swingArm"},
      at = {@At("RETURN")},
      cancellable = true
   )
   public void swingArm(EnumHand p_Hand, CallbackInfo p_Info) {
      OnePopEventSwing l_Event = new OnePopEventSwing(p_Hand);
      OnePopEventBus.EVENT_BUS.post(l_Event);
      if (l_Event.isCancelled()) {
         p_Info.cancel();
      }

   }
}
