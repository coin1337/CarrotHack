package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import net.minecraft.entity.EntityLivingBase;

public class OnePopEventRenderArmorOverlay extends OnePopEventCancellable {
   public EntityLivingBase entity;

   public OnePopEventRenderArmorOverlay(EntityLivingBase entity) {
      this.entity = entity;
   }
}
