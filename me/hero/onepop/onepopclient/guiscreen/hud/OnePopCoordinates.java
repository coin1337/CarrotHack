package me.hero.onepop.onepopclient.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnable;

public class OnePopCoordinates extends OnePopPinnable {
   ChatFormatting dg;
   ChatFormatting db;
   ChatFormatting dr;

   public OnePopCoordinates() {
      super("Coordinates", "Coordinates", 1.0F, 0, 0);
      this.dg = ChatFormatting.DARK_GRAY;
      this.db = ChatFormatting.DARK_BLUE;
      this.dr = ChatFormatting.DARK_RED;
   }

   public void render() {
      int nl_r = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
      int nl_g = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
      int nl_b = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);
      int nl_a = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorA").get_value(1);
      String x = OnePop.g + "[" + OnePop.r + Integer.toString((int)this.mc.field_71439_g.field_70165_t) + OnePop.g + "]" + OnePop.r;
      String y = OnePop.g + "[" + OnePop.r + Integer.toString((int)this.mc.field_71439_g.field_70163_u) + OnePop.g + "]" + OnePop.r;
      String z = OnePop.g + "[" + OnePop.r + Integer.toString((int)this.mc.field_71439_g.field_70161_v) + OnePop.g + "]" + OnePop.r;
      String x_nether = OnePop.g + "[" + OnePop.r + ChatFormatting.RED + Long.toString(Math.round(this.mc.field_71439_g.field_71093_bK != -1 ? this.mc.field_71439_g.field_70165_t / 8.0D : this.mc.field_71439_g.field_70165_t * 8.0D)) + OnePop.g + "]" + OnePop.r;
      String z_nether = OnePop.g + "[" + OnePop.r + ChatFormatting.RED + Long.toString(Math.round(this.mc.field_71439_g.field_71093_bK != -1 ? this.mc.field_71439_g.field_70161_v / 8.0D : this.mc.field_71439_g.field_70161_v * 8.0D)) + OnePop.g + "]" + OnePop.r;
      String line = "XYZ " + x + y + z + ChatFormatting.RED + " XZ " + x_nether + z_nether;
      this.create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);
      this.set_width(this.get(line, "width"));
      this.set_height(this.get(line, "height") + 2);
   }
}
