package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import net.minecraft.util.EnumHandSide;

public class TransformSideFirstPersonEvent extends OnePopEventCancellable {
   private final EnumHandSide handSide;

   public TransformSideFirstPersonEvent(EnumHandSide handSide) {
      this.handSide = handSide;
   }

   public EnumHandSide getHandSide() {
      return this.handSide;
   }
}
