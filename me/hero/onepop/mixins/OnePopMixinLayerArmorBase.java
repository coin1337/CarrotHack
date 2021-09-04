package me.hero.onepop.mixins;

import me.hero.onepop.onepopclient.event.OnePopEventBus;
import me.hero.onepop.onepopclient.event.events.OnePopEventRenderArmorOverlay;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LayerArmorBase.class})
public class OnePopMixinLayerArmorBase {
   @Inject(
      method = {"renderArmorLayer"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void renderArmorLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, EntityEquipmentSlot slotIn, CallbackInfo info) {
      OnePopEventRenderArmorOverlay event = new OnePopEventRenderArmorOverlay(entity);
      OnePopEventBus.EVENT_BUS.post(event);
      if (event.isCancelled()) {
         info.cancel();
      }

   }
}
