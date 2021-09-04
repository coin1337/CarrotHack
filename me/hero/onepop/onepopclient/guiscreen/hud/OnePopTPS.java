package me.hero.onepop.onepopclient.guiscreen.hud;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.event.OnePopEventHandler;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnable;

public class OnePopTPS extends OnePopPinnable {
   public OnePopTPS() {
      super("TPS", "TPS", 1.0F, 0, 0);
   }

   public void render() {
      int nl_r = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
      int nl_g = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
      int nl_b = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);
      int nl_a = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorA").get_value(1);
      String line = "TPS: " + this.getTPS();
      this.create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);
      this.set_width(this.get(line, "width") + 2);
      this.set_height(this.get(line, "height") + 2);
   }

   public String getTPS() {
      try {
         int tps = Math.round(OnePopEventHandler.INSTANCE.get_tick_rate());
         if (tps >= 16) {
            return "§a" + Integer.toString(tps);
         } else {
            return tps >= 10 ? "§3" + Integer.toString(tps) : "§4" + Integer.toString(tps);
         }
      } catch (Exception var2) {
         return "oh no " + var2;
      }
   }
}
