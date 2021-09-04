package me.hero.onepop.onepopclient.guiscreen.hud;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.OnePopDraw;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnable;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopDrawnUtil;
import net.minecraft.util.math.MathHelper;

public class OnePopArrayList extends OnePopPinnable {
   boolean flag = true;
   private int scaled_width;
   private int scaled_height;

   public OnePopArrayList() {
      super("Array List", "ArrayList", 1.0F, 0, 0);
   }

   public void cycle_rainbow() {
      float[] tick_color = new float[]{(float)(System.currentTimeMillis() % 11520L) / 11520.0F};
      int color_rgb_o = Color.HSBtoRGB(tick_color[0], 1.0F, 1.0F);
      OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDArraylistColorR").set_value(color_rgb_o >> 16 & 255);
      OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDArraylistColorG").set_value(color_rgb_o >> 8 & 255);
      OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDArraylistColorB").set_value(color_rgb_o & 255);
   }

   public void render() {
      this.updateResolution();
      int position_update_y = 2;
      int nl_r = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDArraylistColorR").get_value(1);
      int nl_g = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDArraylistColorG").get_value(1);
      int nl_b = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDArraylistColorB").get_value(1);
      int nl_a = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDArraylistColorA").get_value(1);
      if (OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDArraylistRGB").get_value(true)) {
         this.cycle_rainbow();
      }

      List<OnePopHack> pretty_modules = (List)OnePop.get_hack_manager().get_array_active_hacks().stream().sorted(Comparator.comparing((modulesx) -> {
         return this.get(modulesx.array_detail() == null ? modulesx.get_tag() : modulesx.get_tag() + OnePop.g + " [" + OnePop.r + modulesx.array_detail() + OnePop.g + "]" + OnePop.r, "width");
      })).collect(Collectors.toList());
      int count = 0;
      if (OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Top R") || OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Top L")) {
         pretty_modules = Lists.reverse(pretty_modules);
      }

      Iterator var8 = pretty_modules.iterator();

      while(true) {
         OnePopHack modules;
         do {
            if (!var8.hasNext()) {
               return;
            }

            modules = (OnePopHack)var8.next();
            this.flag = true;
         } while(modules.get_category().get_tag().equals("GUI"));

         Iterator var10 = OnePopDrawnUtil.hidden_tags.iterator();

         while(var10.hasNext()) {
            String s = (String)var10.next();
            if (modules.get_tag().equalsIgnoreCase(s)) {
               this.flag = false;
               break;
            }

            if (!this.flag) {
               break;
            }
         }

         if (this.flag) {
            String module_name = modules.array_detail() == null ? modules.get_tag() : modules.get_tag() + OnePop.g + " [" + OnePop.r + modules.array_detail() + OnePop.g + "]" + OnePop.r;
            if (OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Free")) {
               this.create_line(module_name, this.docking(2, module_name), position_update_y, nl_r, nl_g, nl_b, nl_a);
               position_update_y += this.get(module_name, "height") + 2;
               if (this.get(module_name, "width") > this.get_width()) {
                  this.set_width(this.get(module_name, "width") + 2);
               }

               this.set_height(position_update_y);
            } else {
               if (OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Top R")) {
                  this.mc.field_71466_p.func_175063_a(module_name, (float)(this.scaled_width - 2 - this.mc.field_71466_p.func_78256_a(module_name)), (float)(3 + count * 10), (new OnePopDraw.TravisColor(nl_r, nl_g, nl_b, nl_a)).hex());
                  ++count;
               }

               if (OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Top L")) {
                  this.mc.field_71466_p.func_175063_a(module_name, 2.0F, (float)(3 + count * 10), (new OnePopDraw.TravisColor(nl_r, nl_g, nl_b, nl_a)).hex());
                  ++count;
               }

               if (OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Bottom R")) {
                  this.mc.field_71466_p.func_175063_a(module_name, (float)(this.scaled_width - 2 - this.mc.field_71466_p.func_78256_a(module_name)), (float)(this.scaled_height - count * 10), (new OnePopDraw.TravisColor(nl_r, nl_g, nl_b, nl_a)).hex());
                  ++count;
               }

               if (OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Bottom L")) {
                  this.mc.field_71466_p.func_175063_a(module_name, 2.0F, (float)(this.scaled_height - count * 10), (new OnePopDraw.TravisColor(nl_r, nl_g, nl_b, nl_a)).hex());
                  ++count;
               }
            }
         }
      }
   }

   public void updateResolution() {
      this.scaled_width = this.mc.field_71443_c;
      this.scaled_height = this.mc.field_71440_d;
      int scale_factor = 1;
      boolean flag = this.mc.func_152349_b();
      int i = this.mc.field_71474_y.field_74335_Z;
      if (i == 0) {
         i = 1000;
      }

      while(scale_factor < i && this.scaled_width / (scale_factor + 1) >= 320 && this.scaled_height / (scale_factor + 1) >= 240) {
         ++scale_factor;
      }

      if (flag && scale_factor % 2 != 0 && scale_factor != 1) {
         --scale_factor;
      }

      double scaledWidthD = (double)this.scaled_width / (double)scale_factor;
      double scaledHeightD = (double)this.scaled_height / (double)scale_factor;
      this.scaled_width = MathHelper.func_76143_f(scaledWidthD);
      this.scaled_height = MathHelper.func_76143_f(scaledHeightD);
   }
}
