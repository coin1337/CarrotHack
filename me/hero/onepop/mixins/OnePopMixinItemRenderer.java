package me.hero.onepop.mixins;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.event.OnePopEventBus;
import me.hero.onepop.onepopclient.event.events.TransformSideFirstPersonEvent;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ItemRenderer.class})
public abstract class OnePopMixinItemRenderer {
   private boolean injection = true;

   @Shadow
   public abstract void func_187457_a(AbstractClientPlayer var1, float var2, float var3, EnumHand var4, float var5, ItemStack var6, float var7);

   @Inject(
      method = {"transformSideFirstPerson"},
      at = {@At("HEAD")}
   )
   public void transformSideFirstPerson(EnumHandSide hand, float p_187459_2_, CallbackInfo ci) {
      TransformSideFirstPersonEvent event = new TransformSideFirstPersonEvent(hand);
      OnePopEventBus.EVENT_BUS.post(event);
   }

   @Inject(
      method = {"transformEatFirstPerson"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void transformEatFirstPerson(float p_187454_1_, EnumHandSide hand, ItemStack stack, CallbackInfo ci) {
      TransformSideFirstPersonEvent event = new TransformSideFirstPersonEvent(hand);
      OnePopEventBus.EVENT_BUS.post(event);
      if (OnePop.get_hack_manager().get_module_with_tag("CustomViewmodel").is_active() && OnePop.get_setting_manager().get_setting_with_tag("CustomViewmodel", "NoEat").get_value(true)) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"transformFirstPerson"},
      at = {@At("HEAD")}
   )
   public void transformFirstPerson(EnumHandSide hand, float p_187453_2_, CallbackInfo ci) {
      TransformSideFirstPersonEvent event = new TransformSideFirstPersonEvent(hand);
      OnePopEventBus.EVENT_BUS.post(event);
   }
}
