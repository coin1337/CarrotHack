package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;

public class OnePopEventMotionUpdate extends OnePopEventCancellable {
   public int stage;

   public OnePopEventMotionUpdate(int stage) {
      this.stage = stage;
   }
}
