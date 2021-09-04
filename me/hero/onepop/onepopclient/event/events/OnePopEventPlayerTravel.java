package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;

public class OnePopEventPlayerTravel extends OnePopEventCancellable {
   public float Strafe;
   public float Vertical;
   public float Forward;

   public OnePopEventPlayerTravel(float p_Strafe, float p_Vertical, float p_Forward) {
      this.Strafe = p_Strafe;
      this.Vertical = p_Vertical;
      this.Forward = p_Forward;
   }
}
