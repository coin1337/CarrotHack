package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import net.minecraft.client.gui.GuiScreen;

public class OnePopEventGUIScreen extends OnePopEventCancellable {
   private GuiScreen guiscreen;

   public OnePopEventGUIScreen(GuiScreen screen) {
      this.guiscreen = screen;
   }

   public GuiScreen get_guiscreen() {
      return this.guiscreen;
   }

   public void set_screen(GuiScreen screen) {
      this.guiscreen = this.guiscreen;
   }

   public static class Closed extends OnePopEventGUIScreen {
      public Closed(GuiScreen screen) {
         super(screen);
      }
   }

   public static class Displayed extends OnePopEventGUIScreen {
      public Displayed(GuiScreen screen) {
         super(screen);
      }
   }
}
