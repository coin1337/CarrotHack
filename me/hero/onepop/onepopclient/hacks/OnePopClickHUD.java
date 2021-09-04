package me.hero.onepop.onepopclient.hacks;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;

public class OnePopClickHUD extends OnePopHack {
   OnePopSetting frame_view = this.create("info", "HUDStringsList", "Strings");
   OnePopSetting strings_r = this.create("Color R", "HUDStringsColorR", 255, 0, 255);
   OnePopSetting strings_g = this.create("Color G", "HUDStringsColorG", 255, 0, 255);
   OnePopSetting strings_b = this.create("Color B", "HUDStringsColorB", 255, 0, 255);
   OnePopSetting strings_a = this.create("Alpha", "HUDStringsColorA", 230, 0, 255);
   OnePopSetting arraylist_rainbow = this.create("Arraylist Rainbow", "HUDArraylistRGB", false);
   OnePopSetting arraylist_r = this.create("Arraylist R", "HUDArraylistColorR", 255, 0, 255);
   OnePopSetting arraylist_g = this.create("Arraylist G", "HUDArraylistColorG", 255, 0, 255);
   OnePopSetting arraylist_b = this.create("Arraylist B", "HUDArraylistColorB", 255, 0, 255);
   OnePopSetting arraylist_a = this.create("Arraylist A", "HUDArraylistColorA", 230, 0, 255);
   OnePopSetting user_centered = this.create("User Centered", "HUDUserCentered", true);
   OnePopSetting user_custom = this.create("User Custom", "HUDUserCustom", false);
   OnePopSetting arraylist_mode = this.create("Arraylist", "HUDArrayList", "Bottom R", this.combobox(new String[]{"Free", "Top R", "Top L", "Bottom R", "Bottom L"}));
   OnePopSetting show_all_pots = this.create("All Potions", "HUDAllPotions", false);
   OnePopSetting max_player_list = this.create("Max Players", "HUDMaxPlayers", 24, 1, 64);

   public OnePopClickHUD() {
      super(OnePopCategory.ONEPOP_GUI);
      this.name = "HUD";
      this.tag = "HUD";
      this.description = "gui for pinnables";
   }

   public void enable() {
      if (mc.field_71441_e != null && mc.field_71439_g != null) {
         OnePop.get_hack_manager().get_module_with_tag("GUI").set_active(false);
         OnePop.click_hud.back = false;
         mc.func_147108_a(OnePop.click_hud);
      }

   }
}
