package me.hero.onepop.onepopclient.hacks.movement;

import java.util.function.Predicate;
import me.hero.onepop.onepopclient.event.events.OnePopEventGUIScreen;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.input.Keyboard;

public class OnePopInventoryWalk extends OnePopHack {
   private static final KeyBinding[] KEYS;
   @EventHandler
   private final Listener<OnePopEventGUIScreen> state_gui = new Listener((event) -> {
      if (mc.field_71439_g != null || mc.field_71441_e != null) {
         if (!(event.get_guiscreen() instanceof GuiChat) && event.get_guiscreen() != null) {
            this.walk();
         }
      }
   }, new Predicate[0]);

   public OnePopInventoryWalk() {
      super(OnePopCategory.ONEPOP_MOVEMENT);
      this.name = "Inventory Walk";
      this.tag = "InvWalk";
      this.description = "anda";
   }

   public void update() {
      if (mc.field_71439_g != null || mc.field_71441_e != null) {
         if (!(mc.field_71462_r instanceof GuiChat) && mc.field_71462_r != null) {
            this.walk();
         }
      }
   }

   public void walk() {
      KeyBinding[] keys = KEYS;
      int keys_n = keys.length;

      for(int keys_n_2 = 0; keys_n_2 < keys_n; ++keys_n_2) {
         KeyBinding key_binding = keys[keys_n_2];
         if (Keyboard.isKeyDown(key_binding.func_151463_i())) {
            if (key_binding.getKeyConflictContext() != KeyConflictContext.UNIVERSAL) {
               key_binding.setKeyConflictContext(KeyConflictContext.UNIVERSAL);
            }

            KeyBinding.func_74510_a(key_binding.func_151463_i(), true);
         } else {
            KeyBinding.func_74510_a(key_binding.func_151463_i(), false);
         }
      }

   }

   static {
      KEYS = new KeyBinding[]{mc.field_71474_y.field_74351_w, mc.field_71474_y.field_74366_z, mc.field_71474_y.field_74368_y, mc.field_71474_y.field_74370_x, mc.field_71474_y.field_74314_A, mc.field_71474_y.field_151444_V};
   }
}
