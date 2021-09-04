package me.hero.onepop.onepopclient.guiscreen.hud;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnable;

public class OnePopPing extends OnePopPinnable {
   public OnePopPing() {
      super("Ping", "Ping", 1.0F, 0, 0);
   }

   public void render() {
      int nl_r = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
      int nl_g = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
      int nl_b = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);
      int nl_a = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorA").get_value(1);
      String line = "Ping: " + this.get_ping();
      this.create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);
      this.set_width(this.get(line, "width") + 2);
      this.set_height(this.get(line, "height") + 2);
   }

   public String get_ping() {
      try {
         int ping = this.mc.func_147114_u().func_175102_a(this.mc.field_71439_g.func_110124_au()).func_178853_c();
         if (ping <= 50) {
            return "§a" + Integer.toString(ping);
         } else {
            return ping <= 150 ? "§3" + Integer.toString(ping) : "§4" + Integer.toString(ping);
         }
      } catch (Exception var2) {
         return "oh no";
      }
   }
}
