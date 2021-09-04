package me.hero.onepop.onepopclient.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.OnePopDraw;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnable;
import me.hero.onepop.onepopclient.util.OnePopCustomUserUtil;
import me.hero.onepop.onepopclient.util.OnePopTimeUtil;
import net.minecraft.util.math.MathHelper;

public class OnePopUser extends OnePopPinnable {
   private int scaled_width;
   private int scaled_height;
   private int scale_factor;

   public OnePopUser() {
      super("User", "User", 1.0F, 0, 0);
   }

   public void render() {
      this.updateResolution();
      int nl_r = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
      int nl_g = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
      int nl_b = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);
      int nl_a = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorA").get_value(1);
      boolean custom = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDUserCustom").get_value(true);
      boolean centered = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDUserCentered").get_value(true);
      int time = OnePopTimeUtil.get_hour();
      String line;
      if (OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClientLanguage").in("PT")) {
         if (custom) {
            line = OnePopCustomUserUtil.get_message().replace("[", "").replace("]", "");
         } else if (time >= 0 && time < 12) {
            line = "Bom dia " + ChatFormatting.AQUA + ChatFormatting.BOLD + this.mc.field_71439_g.func_70005_c_() + ChatFormatting.RESET + " já tomou cafe da manha? :)";
         } else if (time >= 12 && time < 16) {
            line = "Boa tarde, " + ChatFormatting.AQUA + ChatFormatting.BOLD + this.mc.field_71439_g.func_70005_c_() + ChatFormatting.RESET + " seu token ja ta no meu discord :)";
         } else if (time >= 16 && time < 24) {
            if (this.mc.field_71439_g.func_70005_c_().equals("Pedroperry")) {
               line = "Boa noite, " + ChatFormatting.AQUA + ChatFormatting.BOLD + this.mc.field_71439_g.func_70005_c_() + ChatFormatting.RESET + " eu já estava esperando por você.";
            }

            line = "Boa noite, " + ChatFormatting.AQUA + ChatFormatting.BOLD + this.mc.field_71439_g.func_70005_c_() + ChatFormatting.RESET + " espero que nao seja o pedroperry :)";
         } else {
            line = "Oi, " + ChatFormatting.AQUA + ChatFormatting.BOLD + this.mc.field_71439_g.func_70005_c_() + ChatFormatting.RESET + " vai matar o fllmall hoje? :)";
         }
      } else if (custom) {
         line = OnePopCustomUserUtil.get_message().replace("[", "").replace("]", "");
      } else if (time >= 0 && time < 12) {
         line = "Good morning " + ChatFormatting.AQUA + ChatFormatting.BOLD + this.mc.field_71439_g.func_70005_c_() + ChatFormatting.RESET + " did you ate breakfast? :)";
      } else if (time >= 12 && time < 16) {
         line = "Good afternoon, " + ChatFormatting.AQUA + ChatFormatting.BOLD + this.mc.field_71439_g.func_70005_c_() + ChatFormatting.RESET + " you're looking nice today :)";
      } else if (time >= 16 && time < 24) {
         if (this.mc.field_71439_g.func_70005_c_().equals("Pedroperry")) {
            line = "Fuck you, " + ChatFormatting.AQUA + ChatFormatting.BOLD + this.mc.field_71439_g.func_70005_c_() + ChatFormatting.RESET + " i knew it all along.";
         }

         line = "Good night, " + ChatFormatting.AQUA + ChatFormatting.BOLD + this.mc.field_71439_g.func_70005_c_() + ChatFormatting.RESET + " did someone already said that you're handsome? :)";
      } else {
         line = "Oi, " + ChatFormatting.AQUA + ChatFormatting.BOLD + this.mc.field_71439_g.func_70005_c_() + ChatFormatting.RESET + " vai matar o fllmall hoje? :)";
      }

      if (centered) {
         this.mc.field_71466_p.func_175063_a(line, (float)this.scaled_width / 2.0F - (float)this.mc.field_71466_p.func_78256_a(line) / 2.0F, 20.0F, (new OnePopDraw.TravisColor(nl_r, nl_g, nl_b, nl_a)).hex());
      } else {
         this.create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);
      }

      this.set_width(this.get(line, "width") + 2);
      this.set_height(this.get(line, "height") + 2);
   }

   public void updateResolution() {
      this.scaled_width = this.mc.field_71443_c;
      this.scaled_height = this.mc.field_71440_d;
      this.scale_factor = 1;
      boolean flag = this.mc.func_152349_b();
      int i = this.mc.field_71474_y.field_74335_Z;
      if (i == 0) {
         i = 1000;
      }

      while(this.scale_factor < i && this.scaled_width / (this.scale_factor + 1) >= 320 && this.scaled_height / (this.scale_factor + 1) >= 240) {
         ++this.scale_factor;
      }

      if (flag && this.scale_factor % 2 != 0 && this.scale_factor != 1) {
         --this.scale_factor;
      }

      double scaledWidthD = (double)this.scaled_width / (double)this.scale_factor;
      double scaledHeightD = (double)this.scaled_height / (double)this.scale_factor;
      this.scaled_width = MathHelper.func_76143_f(scaledWidthD);
      this.scaled_height = MathHelper.func_76143_f(scaledHeightD);
   }
}
