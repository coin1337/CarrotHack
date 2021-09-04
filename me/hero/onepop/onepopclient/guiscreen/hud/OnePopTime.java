package me.hero.onepop.onepopclient.guiscreen.hud;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnable;
import me.hero.onepop.onepopclient.util.OnePopTimeUtil;

public class OnePopTime extends OnePopPinnable {
   public OnePopTime() {
      super("Time", "Time", 1.0F, 0, 0);
   }

   public void render() {
      int nl_r = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
      int nl_g = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
      int nl_b = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);
      int nl_a = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorA").get_value(1);
      String line = "";
      line = line + (OnePopTimeUtil.get_hour() < 10 ? "0" + OnePopTimeUtil.get_hour() : OnePopTimeUtil.get_hour());
      line = line + ":";
      line = line + (OnePopTimeUtil.get_minuite() < 10 ? "0" + OnePopTimeUtil.get_minuite() : OnePopTimeUtil.get_minuite());
      line = line + ":";
      line = line + (OnePopTimeUtil.get_second() < 10 ? "0" + OnePopTimeUtil.get_second() : OnePopTimeUtil.get_second());
      this.create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);
      this.set_width(this.get(line, "width") + 2);
      this.set_height(this.get(line, "height") + 2);
   }
}
