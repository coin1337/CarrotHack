package me.hero.onepop.mixins;

import me.hero.onepop.onepopclient.event.OnePopEventBus;
import me.hero.onepop.onepopclient.event.events.OnePopEventRenderName;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({RenderPlayer.class})
public class OnePopMixinRenderPlayer {
   @Inject(
      method = {"renderEntityName"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void renderLivingLabel(AbstractClientPlayer entityIn, double x, double y, double z, String name, double distanceSq, CallbackInfo info) {
      OnePopEventRenderName event_packet = new OnePopEventRenderName(entityIn, x, y, z, name, distanceSq);
      OnePopEventBus.EVENT_BUS.post(event_packet);
      if (event_packet.isCancelled()) {
         info.cancel();
      }

   }
}
