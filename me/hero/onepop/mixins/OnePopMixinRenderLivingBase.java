package me.hero.onepop.mixins;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.event.OnePopEventBus;
import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import me.hero.onepop.onepopclient.event.events.OnePopEventRenderEntity;
import me.hero.onepop.onepopclient.event.events.OnePopEventRenderEntityModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({RenderLivingBase.class})
public abstract class OnePopMixinRenderLivingBase<T extends EntityLivingBase> extends Render<T> {
   protected OnePopMixinRenderLivingBase(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
      super(renderManagerIn);
   }

   @Inject(
      method = {"doRender"},
      at = {@At("HEAD")}
   )
   private void onRender(T entity, double x, double y, double z, float yaw, float partialTicks, CallbackInfo callbackInfo) {
      OnePopEventRenderEntity event = new OnePopEventRenderEntity(entity, partialTicks);
      event.setEra(OnePopEventCancellable.Era.EVENT_PRE);
      event.setPosition(x, y, z);
      OnePopEventBus.EVENT_BUS.post(event);
   }

   @Inject(
      method = {"doRender"},
      at = {@At("RETURN")}
   )
   private void onLastRender(T entity, double x, double y, double z, float yaw, float partialTicks, CallbackInfo callbackInfo) {
      OnePopEventRenderEntity event = new OnePopEventRenderEntity(entity, partialTicks);
      event.setEra(OnePopEventCancellable.Era.EVENT_POST);
      event.setPosition(x, y, z);
      OnePopEventBus.EVENT_BUS.post(event);
   }

   @Redirect(
      method = {"renderModel"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"
)
   )
   private void renderModelHook(ModelBase modelBase, Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (OnePop.get_hack_manager().get_module_with_tag("Chams").is_active()) {
         OnePopEventRenderEntityModel event = new OnePopEventRenderEntityModel(0, modelBase, entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
         OnePop.get_hack_manager().get_module_with_tag("Chams").on_render_model(event);
         if (event.isCancelled()) {
            return;
         }
      }

      modelBase.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
   }
}
