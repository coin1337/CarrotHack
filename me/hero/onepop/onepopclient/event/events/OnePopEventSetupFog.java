package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;

public class OnePopEventSetupFog extends OnePopEventCancellable {
   public int start_coords;
   public float partial_ticks;

   public OnePopEventSetupFog(int coords, float ticks) {
      this.start_coords = coords;
      this.partial_ticks = ticks;
   }
}
