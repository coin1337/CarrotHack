package me.hero.onepop.onepopclient.guiscreen;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopFrame;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnableButton;
import net.minecraft.client.gui.GuiScreen;

public class OnePopHUD extends GuiScreen {
   private final OnePopFrame frame = new OnePopFrame("1pop hud", "OnePopHUD", 40, 40);
   private int frame_height;
   public boolean on_gui = false;
   public boolean back = false;

   public OnePopFrame get_frame_hud() {
      return this.frame;
   }

   public boolean func_73868_f() {
      return false;
   }

   public void func_73866_w_() {
      this.on_gui = true;
      OnePopFrame.nc_r = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameFrameR").get_value(1);
      OnePopFrame.nc_g = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameFrameG").get_value(1);
      OnePopFrame.nc_b = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameFrameB").get_value(1);
      OnePopFrame.bg_r = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundFrameR").get_value(1);
      OnePopFrame.bg_g = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundFrameG").get_value(1);
      OnePopFrame.bg_b = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundFrameB").get_value(1);
      OnePopFrame.bg_a = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundFrameA").get_value(1);
      OnePopFrame.bd_r = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderFrameR").get_value(1);
      OnePopFrame.bd_g = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderFrameG").get_value(1);
      OnePopFrame.bd_b = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderFrameB").get_value(1);
      OnePopFrame.bd_a = 0;
      OnePopFrame.bdw_r = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetR").get_value(1);
      OnePopFrame.bdw_g = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetG").get_value(1);
      OnePopFrame.bdw_b = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetB").get_value(1);
      OnePopFrame.bdw_a = 255;
      OnePopPinnableButton.nc_r = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameWidgetR").get_value(1);
      OnePopPinnableButton.nc_g = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameWidgetG").get_value(1);
      OnePopPinnableButton.nc_b = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameWidgetB").get_value(1);
      OnePopPinnableButton.bg_r = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetR").get_value(1);
      OnePopPinnableButton.bg_g = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetG").get_value(1);
      OnePopPinnableButton.bg_b = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetB").get_value(1);
      OnePopPinnableButton.bg_a = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetA").get_value(1);
      OnePopPinnableButton.bd_r = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetR").get_value(1);
      OnePopPinnableButton.bd_g = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetG").get_value(1);
      OnePopPinnableButton.bd_b = OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetB").get_value(1);
   }

   public void func_146281_b() {
      if (this.back) {
         OnePop.get_hack_manager().get_module_with_tag("GUI").set_active(true);
         OnePop.get_hack_manager().get_module_with_tag("HUD").set_active(false);
      } else {
         OnePop.get_hack_manager().get_module_with_tag("HUD").set_active(false);
         OnePop.get_hack_manager().get_module_with_tag("GUI").set_active(false);
      }

      this.on_gui = false;
      OnePop.get_config_manager().save_settings();
   }

   protected void func_73864_a(int mx, int my, int mouse) {
      this.frame.mouse(mx, my, mouse);
      if (mouse == 0 && this.frame.motion(mx, my) && this.frame.can()) {
         this.frame.set_move(true);
         this.frame.set_move_x(mx - this.frame.get_x());
         this.frame.set_move_y(my - this.frame.get_y());
      }

   }

   protected void func_146286_b(int mx, int my, int state) {
      this.frame.release(mx, my, state);
      this.frame.set_move(false);
   }

   public void func_73863_a(int mx, int my, float tick) {
      this.func_146276_q_();
      this.frame.render(mx, my, 2);
   }
}
