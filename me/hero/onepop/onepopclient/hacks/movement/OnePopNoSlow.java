package me.hero.onepop.onepopclient.hacks.movement;

import java.util.function.Predicate;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.InputUpdateEvent;

public class OnePopNoSlow extends OnePopHack {
   @EventHandler
   private Listener<InputUpdateEvent> eventListener = new Listener((event) -> {
      if (mc.field_71439_g.func_184587_cr() && !mc.field_71439_g.func_184218_aH()) {
         MovementInput var10000 = event.getMovementInput();
         var10000.field_78902_a *= 5.0F;
         var10000 = event.getMovementInput();
         var10000.field_192832_b *= 5.0F;
      }

   }, new Predicate[0]);

   public OnePopNoSlow() {
      super(OnePopCategory.ONEPOP_MOVEMENT);
      this.name = "No Slow";
      this.tag = "NoSlow";
      this.description = "OI CHEROSO";
   }
}
