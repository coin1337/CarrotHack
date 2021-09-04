package me.hero.onepop.mixins;

import me.hero.onepop.onepopclient.event.OnePopEventBus;
import me.hero.onepop.onepopclient.event.events.OnePopEventEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({Entity.class})
public class OnePopMixinEntity {
   @Shadow
   public double field_70159_w;
   @Shadow
   public double field_70181_x;
   @Shadow
   public double field_70179_y;

   @Redirect(
      method = {"applyEntityCollision"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"
)
   )
   public void velocity(Entity entity, double x, double y, double z) {
      OnePopEventEntity.OnePopEventColision event = new OnePopEventEntity.OnePopEventColision(entity, x, y, z);
      OnePopEventBus.EVENT_BUS.post(event);
      if (!event.isCancelled()) {
         entity.field_70159_w += x;
         entity.field_70181_x += y;
         entity.field_70179_y += z;
         entity.field_70160_al = true;
      }
   }

   @Shadow
   public void func_70091_d(MoverType type, double x, double y, double z) {
   }
}
