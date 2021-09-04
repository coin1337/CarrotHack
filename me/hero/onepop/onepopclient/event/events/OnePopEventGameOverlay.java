package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import net.minecraft.client.gui.ScaledResolution;

public class OnePopEventGameOverlay extends OnePopEventCancellable {
   public float partial_ticks;
   private ScaledResolution scaled_resolution;

   public OnePopEventGameOverlay(float partial_ticks, ScaledResolution scaled_resolution) {
      this.partial_ticks = partial_ticks;
      this.scaled_resolution = scaled_resolution;
   }

   public ScaledResolution get_scaled_resoltion() {
      return this.scaled_resolution;
   }
}
