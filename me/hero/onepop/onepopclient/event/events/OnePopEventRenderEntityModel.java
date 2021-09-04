package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public class OnePopEventRenderEntityModel extends OnePopEventCancellable {
   public ModelBase modelBase;
   public Entity entity;
   public float limbSwing;
   public float limbSwingAmount;
   public float age;
   public float headYaw;
   public float headPitch;
   public float scale;
   public int stage;

   public OnePopEventRenderEntityModel(int stage, ModelBase modelBase, Entity entity, float limbSwing, float limbSwingAmount, float age, float headYaw, float headPitch, float scale) {
      this.stage = stage;
      this.modelBase = modelBase;
      this.entity = entity;
      this.limbSwing = limbSwing;
      this.limbSwingAmount = limbSwingAmount;
      this.age = age;
      this.headYaw = headYaw;
      this.headPitch = headPitch;
      this.scale = scale;
   }
}
