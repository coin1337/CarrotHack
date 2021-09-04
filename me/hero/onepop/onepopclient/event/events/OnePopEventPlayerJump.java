package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;

public class OnePopEventPlayerJump extends OnePopEventCancellable {
   public double motion_x;
   public double motion_y;

   public OnePopEventPlayerJump(double motion_x, double motion_y) {
      this.motion_x = motion_x;
      this.motion_y = motion_y;
   }
}
