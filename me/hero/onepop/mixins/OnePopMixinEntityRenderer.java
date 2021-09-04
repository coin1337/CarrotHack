package me.hero.onepop.mixins;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.event.OnePopEventBus;
import me.hero.onepop.onepopclient.event.events.OnePopEventSetupFog;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityRenderer.class})
public class OnePopMixinEntityRenderer {
   @Inject(
      method = {"setupFog"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void setupFog(int startCoords, float partialTicks, CallbackInfo p_Info) {
      OnePopEventSetupFog event = new OnePopEventSetupFog(startCoords, partialTicks);
      OnePopEventBus.EVENT_BUS.post(event);
      if (!event.isCancelled()) {
         ;
      }
   }

   @Inject(
      method = {"hurtCameraEffect"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hurtCameraEffect(float ticks, CallbackInfo info) {
      if (OnePop.get_setting_manager().get_setting_with_tag("NoRender", "NoRenderNoHurtCam").get_value(true)) {
         info.cancel();
      }

   }
}
