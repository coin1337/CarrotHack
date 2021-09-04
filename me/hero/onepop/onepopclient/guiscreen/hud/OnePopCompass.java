package me.hero.onepop.onepopclient.guiscreen.hud;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.OnePopDraw;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnable;
import me.hero.onepop.onepopclient.util.OnePopMathUtil;

public class OnePopCompass extends OnePopPinnable {
   public OnePopDraw font = new OnePopDraw(1.0F);
   private static final double half_pi = 1.5707963267948966D;

   public OnePopCompass() {
      super("Compass", "Compass", 1.0F, 0, 0);
   }

   public void render() {
      int r = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
      int g = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
      int b = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);
      int a = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorA").get_value(1);
      OnePopCompass.Direction[] var5 = OnePopCompass.Direction.values();
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         OnePopCompass.Direction dir = var5[var7];
         double rad = this.get_post_on_compass(dir);
         if (dir.name().equals("N")) {
            this.create_line(dir.name(), (int)((double)this.docking(1, dir.name()) + this.get_x(rad)), (int)this.get_y(rad), r, g, b, a);
         } else {
            this.create_line(dir.name(), (int)((double)this.docking(1, dir.name()) + this.get_x(rad)), (int)this.get_y(rad), 225, 225, 225, 225);
         }
      }

      this.set_width(50);
      this.set_height(50);
   }

   private double get_post_on_compass(OnePopCompass.Direction dir) {
      double yaw = Math.toRadians((double)OnePopMathUtil.wrap(this.mc.func_175606_aa().field_70177_z));
      int index = dir.ordinal();
      return yaw + (double)index * 1.5707963267948966D;
   }

   private double get_x(double rad) {
      return Math.sin(rad) * (double)OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDCompassScale").get_value(1);
   }

   private double get_y(double rad) {
      double epic_pitch = (double)OnePopMathUtil.clamp2(this.mc.func_175606_aa().field_70125_A + 30.0F, -90.0F, 90.0F);
      double pitch_radians = Math.toRadians(epic_pitch);
      return Math.cos(rad) * Math.sin(pitch_radians) * (double)OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDCompassScale").get_value(1);
   }

   private static enum Direction {
      N,
      W,
      S,
      E;
   }
}
