package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import net.minecraft.entity.Entity;

public class OnePopEventRenderEntity extends OnePopEventCancellable {
   private Entity entity;
   private float partialTicks;
   private double x;
   private double y;
   private double z;

   public OnePopEventRenderEntity(Entity entity, float partialTicks) {
      this.entity = entity;
      this.partialTicks = partialTicks;
   }

   protected void setEntity(Entity entity) {
      this.entity = entity;
   }

   public Entity getEntity() {
      return this.entity;
   }

   protected void setPartialTicks(float partialTicks) {
      this.partialTicks = partialTicks;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public void setPosition(double x, double y, double z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public double getZ() {
      return this.z;
   }
}
