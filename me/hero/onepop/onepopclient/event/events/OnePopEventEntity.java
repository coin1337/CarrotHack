package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import net.minecraft.entity.Entity;

public class OnePopEventEntity extends OnePopEventCancellable {
   private Entity entity;

   public OnePopEventEntity(Entity entity) {
      this.entity = entity;
   }

   public Entity get_entity() {
      return this.entity;
   }

   public static class OnePopEventColision extends OnePopEventEntity {
      private double x;
      private double y;
      private double z;

      public OnePopEventColision(Entity entity, double x, double y, double z) {
         super(entity);
         this.x = x;
         this.y = y;
         this.z = z;
      }

      public void set_x(double x) {
         this.x = x;
      }

      public void set_y(double y) {
         this.y = y;
      }

      public void set_z(double x) {
         this.z = this.z;
      }

      public double get_x() {
         return this.x;
      }

      public double get_y() {
         return this.y;
      }

      public double get_z() {
         return this.z;
      }
   }
}
