package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import net.minecraft.entity.Entity;

public class OnePopEventEntityRemoved extends OnePopEventCancellable {
   private final Entity entity;

   public OnePopEventEntityRemoved(Entity entity) {
      this.entity = entity;
   }

   public Entity get_entity() {
      return this.entity;
   }
}
