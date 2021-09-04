package me.hero.onepop.onepopclient.guiscreen;

import java.util.ArrayList;
import java.util.Iterator;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.components.OnePopFrame;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class OnePopGUI extends GuiScreen {
   private ArrayList<OnePopFrame> frame = new ArrayList();
   private int frame_x = 10;
   private OnePopFrame current;
   private boolean event_start = true;
   private boolean event_finish = false;
   public int theme_frame_name_r = 0;
   public int theme_frame_name_g = 0;
   public int theme_frame_name_b = 0;
   public int theme_frame_name_a = 0;
   public int theme_frame_background_r = 0;
   public int theme_frame_background_g = 0;
   public int theme_frame_background_b = 0;
   public int theme_frame_background_a = 0;
   public int theme_frame_border_r = 0;
   public int theme_frame_border_g = 0;
   public int theme_frame_border_b = 0;
   public int theme_widget_name_r = 0;
   public int theme_widget_name_g = 0;
   public int theme_widget_name_b = 0;
   public int theme_widget_name_a = 0;
   public int theme_widget_background_r = 0;
   public int theme_widget_background_g = 0;
   public int theme_widget_background_b = 0;
   public int theme_widget_background_a = 0;
   public int theme_widget_border_r = 0;
   public int theme_widget_border_g = 0;
   public int theme_widget_border_b = 0;
   private final Minecraft mc = Minecraft.func_71410_x();

   public OnePopGUI() {
      OnePopCategory[] var1 = OnePopCategory.values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         OnePopCategory categorys = var1[var3];
         if (!categorys.is_hidden()) {
            OnePopFrame frames = new OnePopFrame(categorys);
            frames.set_x(this.frame_x);
            this.frame.add(frames);
            this.frame_x += frames.get_width() + 5;
            this.current = frames;
         }
      }

   }

   public boolean func_73868_f() {
      return false;
   }

   public void func_146281_b() {
      OnePop.get_hack_manager().get_module_with_tag("GUI").set_active(false);
      OnePop.get_config_manager().save_settings();
   }

   protected void func_73869_a(char char_, int key) {
      Iterator var3 = this.frame.iterator();

      while(true) {
         OnePopFrame frame;
         do {
            if (!var3.hasNext()) {
               return;
            }

            frame = (OnePopFrame)var3.next();
            frame.bind(char_, key);
            if (key == 1 && !frame.is_binding()) {
               this.mc.func_147108_a((GuiScreen)null);
            }

            if (key == 208 || key == 200) {
               frame.set_y(frame.get_y() - 1);
            }
         } while(key != 200 && key != 208);

         frame.set_y(frame.get_y() + 1);
      }
   }

   protected void func_73864_a(int mx, int my, int mouse) {
      Iterator var4 = this.frame.iterator();

      while(var4.hasNext()) {
         OnePopFrame frames = (OnePopFrame)var4.next();
         frames.mouse(mx, my, mouse);
         if (mouse == 0 && frames.motion(mx, my) && frames.can()) {
            frames.does_button_for_do_widgets_can(false);
            this.current = frames;
            this.current.set_move(true);
            this.current.set_move_x(mx - this.current.get_x());
            this.current.set_move_y(my - this.current.get_y());
         }
      }

   }

   protected void func_146286_b(int mx, int my, int state) {
      Iterator var4 = this.frame.iterator();

      while(var4.hasNext()) {
         OnePopFrame frames = (OnePopFrame)var4.next();
         frames.does_button_for_do_widgets_can(true);
         frames.mouse_release(mx, my, state);
         frames.set_move(false);
      }

      this.set_current(this.current);
   }

   public void func_73863_a(int mx, int my, float tick) {
      this.func_146276_q_();
      Iterator var4 = this.frame.iterator();

      while(var4.hasNext()) {
         OnePopFrame frames = (OnePopFrame)var4.next();
         frames.render(mx, my);
      }

   }

   public void set_current(OnePopFrame current) {
      this.frame.remove(current);
      this.frame.add(current);
   }

   public OnePopFrame get_current() {
      return this.current;
   }

   public ArrayList<OnePopFrame> get_array_frames() {
      return this.frame;
   }

   public OnePopFrame get_frame_with_tag(String tag) {
      OnePopFrame frame_requested = null;
      Iterator var3 = this.get_array_frames().iterator();

      while(var3.hasNext()) {
         OnePopFrame frames = (OnePopFrame)var3.next();
         if (frames.get_tag().equals(tag)) {
            frame_requested = frames;
         }
      }

      return frame_requested;
   }
}
