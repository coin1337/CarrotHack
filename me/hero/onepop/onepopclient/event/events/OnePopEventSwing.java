package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import net.minecraft.util.EnumHand;

public class OnePopEventSwing extends OnePopEventCancellable {
   public EnumHand hand;

   public OnePopEventSwing(EnumHand hand) {
      this.hand = hand;
   }
}
