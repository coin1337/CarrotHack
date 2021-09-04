package me.hero.onepop.onepopclient.event;

import me.zero.alpine.fork.event.type.Cancellable;
import net.minecraft.client.Minecraft;

public class OnePopEventCancellable extends Cancellable {
   private OnePopEventCancellable.Era era_switch;
   private float partial_ticks;

   public OnePopEventCancellable() {
      this.era_switch = OnePopEventCancellable.Era.EVENT_PRE;
      this.partial_ticks = Minecraft.func_71410_x().func_184121_ak();
   }

   public void setEra(OnePopEventCancellable.Era era) {
      this.era_switch = era;
   }

   public OnePopEventCancellable.Era get_era() {
      return this.era_switch;
   }

   public float get_partial_ticks() {
      return this.partial_ticks;
   }

   public static enum Era {
      EVENT_PRE,
      EVENT_PERI,
      EVENT_POST;
   }
}
