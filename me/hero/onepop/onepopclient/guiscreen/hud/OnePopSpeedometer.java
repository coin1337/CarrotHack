package me.hero.onepop.onepopclient.guiscreen.hud;

import java.text.DecimalFormat;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnable;
import net.minecraft.util.math.MathHelper;

public class OnePopSpeedometer extends OnePopPinnable {
   public OnePopSpeedometer() {
      super("Speedometer", "Speedometer", 1.0F, 0, 0);
   }

   public void render() {
      int nl_r = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
      int nl_g = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
      int nl_b = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);
      int nl_a = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorA").get_value(1);
      double x = this.mc.field_71439_g.field_70165_t - this.mc.field_71439_g.field_70169_q;
      double z = this.mc.field_71439_g.field_70161_v - this.mc.field_71439_g.field_70166_s;
      float tr = this.mc.field_71428_T.field_194149_e / 1000.0F;
      String bps = "km/h: " + (new DecimalFormat("#.#")).format((double)(MathHelper.func_76133_a(x * x + z * z) / tr) * 3.6D);
      this.create_line(bps, this.docking(1, bps), 2, nl_r, nl_g, nl_b, nl_a);
      this.set_width(this.get(bps, "width") + 2);
      this.set_height(this.get(bps, "height") + 2);
   }
}
