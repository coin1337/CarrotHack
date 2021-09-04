package me.hero.onepop.onepopclient.guiscreen.render.components.widgets;

import java.awt.Color;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.OnePopDraw;
import me.hero.onepop.onepopclient.guiscreen.render.components.OnePopAbstractWidget;
import me.hero.onepop.onepopclient.guiscreen.render.components.OnePopFrame;
import me.hero.onepop.onepopclient.guiscreen.render.components.OnePopModuleButton;

public class OnePopButtonBind extends OnePopAbstractWidget {
   private OnePopFrame frame;
   private OnePopModuleButton master;
   private String button_name;
   private String points;
   private int x;
   private int y;
   private int width;
   private int height;
   private int save_y;
   private float tick;
   private boolean can;
   private boolean waiting;
   private OnePopDraw font = new OnePopDraw(1.0F);
   private int border_size = 0;

   public OnePopButtonBind(OnePopFrame frame, OnePopModuleButton master, String tag, int update_postion) {
      this.frame = frame;
      this.master = master;
      this.x = master.get_x();
      this.y = update_postion;
      this.save_y = this.y;
      this.width = master.get_width();
      this.height = this.font.get_string_height();
      this.button_name = tag;
      this.can = true;
      this.points = ".";
      this.tick = 0.0F;
   }

   public void does_can(boolean value) {
      this.can = value;
   }

   public void set_x(int x) {
      this.x = x;
   }

   public void set_y(int y) {
      this.y = y;
   }

   public void set_width(int width) {
      this.width = width;
   }

   public void set_height(int height) {
      this.height = height;
   }

   public int get_x() {
      return this.x;
   }

   public int get_y() {
      return this.y;
   }

   public int get_width() {
      return this.width;
   }

   public int get_height() {
      return this.height;
   }

   public int get_save_y() {
      return this.save_y;
   }

   public boolean motion_pass(int mx, int my) {
      return this.motion(mx, my);
   }

   public boolean motion(int mx, int my) {
      return mx >= this.get_x() && my >= this.get_save_y() && mx <= this.get_x() + this.get_width() && my <= this.get_save_y() + this.get_height();
   }

   public boolean can() {
      return this.can;
   }

   public boolean is_binding() {
      return this.waiting;
   }

   public void bind(char char_, int key) {
      if (this.waiting) {
         switch(key) {
         case 1:
            this.waiting = false;
            break;
         case 211:
            this.master.get_module().set_bind(0);
            this.waiting = false;
            break;
         default:
            this.master.get_module().set_bind(key);
            this.waiting = false;
         }
      }

   }

   public void mouse(int mx, int my, int mouse) {
      if (mouse == 0 && this.motion(mx, my) && this.master.is_open() && this.can()) {
         this.frame.does_can(false);
         this.waiting = true;
      }

   }

   public void render(int master_y, int separe, int absolute_x, int absolute_y) {
      this.set_width(this.master.get_width() - separe);
      float[] tick_color = new float[]{(float)(System.currentTimeMillis() % 11520L) / 11520.0F};
      int color_a = Color.HSBtoRGB(tick_color[0], 1.0F, 1.0F);
      boolean var7;
      if (color_a <= 100) {
         var7 = true;
      } else if (color_a >= 200) {
         var7 = true;
      }

      if (this.waiting) {
         if (this.tick >= 15.0F) {
            this.points = "..";
         }

         if (this.tick >= 30.0F) {
            this.points = "...";
         }

         if (this.tick >= 45.0F) {
            this.points = ".";
            this.tick = 0.0F;
         }
      }

      boolean zbob = true;
      this.save_y = this.y + master_y;
      int ns_r = OnePop.click_gui.theme_widget_name_r;
      int ns_g = OnePop.click_gui.theme_widget_name_g;
      int ns_b = OnePop.click_gui.theme_widget_name_b;
      int ns_a = OnePop.click_gui.theme_widget_name_a;
      int bg_r = OnePop.click_gui.theme_widget_background_r;
      int bg_g = OnePop.click_gui.theme_widget_background_g;
      int bg_b = OnePop.click_gui.theme_widget_background_b;
      int bg_a = OnePop.click_gui.theme_widget_background_a;
      int bd_r = OnePop.click_gui.theme_widget_border_r;
      int bd_g = OnePop.click_gui.theme_widget_border_g;
      int bd_b = OnePop.click_gui.theme_widget_border_b;
      if (this.waiting) {
         OnePopDraw.draw_rect(this.get_x(), this.save_y, this.get_x() + this.width, this.save_y + this.height, bg_r, bg_g, bg_b, bg_a);
         this.tick += 0.5F;
         OnePopDraw.draw_string("Listening " + this.points, this.x + 2, this.save_y, ns_r, ns_g, ns_b, ns_a);
      } else {
         OnePopDraw.draw_string("Bind <" + this.master.get_module().get_bind("string") + ">", this.x + 2, this.save_y, ns_r, ns_g, ns_b, ns_a);
      }

      tick_color[0] += 5.0F;
   }
}
