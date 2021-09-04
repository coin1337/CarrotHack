package me.hero.onepop.onepopclient.guiscreen.render.components;

import java.util.ArrayList;
import java.util.Iterator;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.OnePopDraw;
import me.hero.onepop.onepopclient.guiscreen.render.components.widgets.OnePopButton;
import me.hero.onepop.onepopclient.guiscreen.render.components.widgets.OnePopButtonBind;
import me.hero.onepop.onepopclient.guiscreen.render.components.widgets.OnePopCombobox;
import me.hero.onepop.onepopclient.guiscreen.render.components.widgets.OnePopLabel;
import me.hero.onepop.onepopclient.guiscreen.render.components.widgets.OnePopSlider;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.rina.turok.hardware.mouse.TurokMouse;
import me.rina.turok.util.TurokMath;
import me.rina.turok.util.TurokRect;
import me.rina.turok.util.TurokTick;

public class OnePopModuleButton {
   public TurokTick tick_height_animation = new TurokTick();
   private OnePopHack module;
   private OnePopFrame master;
   private ArrayList<OnePopAbstractWidget> widget;
   private String module_name;
   private boolean opened;
   private int x;
   private int y;
   private int width;
   private int height;
   private int opened_height;
   private int save_y;
   private OnePopDraw font = new OnePopDraw(1.0F);
   private int border_a = 200;
   private int border_size = 1;
   private int master_height_cache;
   public int settings_height;
   private int count;

   public OnePopModuleButton(OnePopHack module, OnePopFrame master) {
      this.module = module;
      this.master = master;
      this.widget = new ArrayList();
      this.module_name = module.get_name();
      this.x = 0;
      this.y = 0;
      this.width = this.font.get_string_width(module.get_name()) + 5;
      this.height = 2 + this.font.get_string_height() + 2;
      this.opened_height = this.height;
      this.save_y = 0;
      this.opened = false;
      this.master_height_cache = master.get_height();
      this.settings_height = this.y + this.height + 1;
      this.count = 0;
      Iterator var3 = OnePop.get_setting_manager().get_settings_with_hack(module).iterator();

      while(true) {
         OnePopSetting settings;
         do {
            if (!var3.hasNext()) {
               int size = OnePop.get_setting_manager().get_settings_with_hack(module).size();
               if (this.count >= size) {
                  this.widget.add(new OnePopButtonBind(master, this, "bind", this.settings_height));
                  this.settings_height += 10;
               }

               return;
            }

            settings = (OnePopSetting)var3.next();
            if (settings.get_type().equals("button")) {
               this.widget.add(new OnePopButton(master, this, settings.get_tag(), this.settings_height));
               this.settings_height += 10;
               ++this.count;
            }

            if (settings.get_type().equals("combobox")) {
               this.widget.add(new OnePopCombobox(master, this, settings.get_tag(), this.settings_height));
               this.settings_height += 10;
               ++this.count;
            }

            if (settings.get_type().equals("label")) {
               this.widget.add(new OnePopLabel(master, this, settings.get_tag(), this.settings_height));
               this.settings_height += 10;
               ++this.count;
            }
         } while(!settings.get_type().equals("doubleslider") && !settings.get_type().equals("integerslider"));

         this.widget.add(new OnePopSlider(master, this, settings.get_tag(), this.settings_height));
         this.settings_height += 10;
         ++this.count;
      }
   }

   public OnePopHack get_module() {
      return this.module;
   }

   public OnePopFrame get_master() {
      return this.master;
   }

   public void set_pressed(boolean value) {
      this.module.set_active(value);
   }

   public void set_width(int width) {
      this.width = width;
   }

   public void set_height(int height) {
      this.height = height;
   }

   public void set_x(int x) {
      this.x = x;
   }

   public void set_y(int y) {
      this.y = y;
   }

   public void set_open(boolean value) {
      this.opened = value;
   }

   public boolean get_state() {
      return this.module.is_active();
   }

   public int get_settings_height() {
      return this.settings_height;
   }

   public int get_cache_height() {
      return this.master_height_cache;
   }

   public int get_width() {
      return this.width;
   }

   public int get_height() {
      return this.height;
   }

   public int get_x() {
      return this.x;
   }

   public int get_y() {
      return this.y;
   }

   public int get_save_y() {
      return this.save_y;
   }

   public boolean is_open() {
      return this.opened;
   }

   public boolean is_binding() {
      boolean value_requested = false;
      Iterator var2 = this.widget.iterator();

      while(var2.hasNext()) {
         OnePopAbstractWidget widgets = (OnePopAbstractWidget)var2.next();
         if (widgets.is_binding()) {
            value_requested = true;
         }
      }

      return value_requested;
   }

   public boolean motion(int mx, int my) {
      return mx >= this.get_x() && my >= this.get_save_y() && mx <= this.get_x() + this.get_width() && my <= this.get_save_y() + this.get_height();
   }

   public void does_widgets_can(boolean can) {
      Iterator var2 = this.widget.iterator();

      while(var2.hasNext()) {
         OnePopAbstractWidget widgets = (OnePopAbstractWidget)var2.next();
         widgets.does_can(can);
      }

   }

   public void bind(char char_, int key) {
      Iterator var3 = this.widget.iterator();

      while(var3.hasNext()) {
         OnePopAbstractWidget widgets = (OnePopAbstractWidget)var3.next();
         widgets.bind(char_, key);
      }

   }

   public void mouse(int mx, int my, int mouse) {
      Iterator var4 = this.widget.iterator();

      while(var4.hasNext()) {
         OnePopAbstractWidget widgets = (OnePopAbstractWidget)var4.next();
         widgets.mouse(mx, my, mouse);
      }

      if (mouse == 0 && this.motion(mx, my)) {
         this.master.does_can(false);
         this.set_pressed(!this.get_state());
      }

      if (mouse == 1 && this.motion(mx, my)) {
         this.master.does_can(false);
         this.set_open(!this.is_open());
         this.master.refresh_frame(this, 0);
      }

   }

   public void button_release(int mx, int my, int mouse) {
      Iterator var4 = this.widget.iterator();

      while(var4.hasNext()) {
         OnePopAbstractWidget widgets = (OnePopAbstractWidget)var4.next();
         widgets.release(mx, my, mouse);
      }

      this.master.does_can(true);
   }

   public void render(int mx, int my, int separe) {
      this.set_width(this.master.get_width() - separe);
      this.save_y = this.master.get_y() + this.y;
      int nm_r = OnePop.click_gui.theme_widget_name_r;
      int nm_g = OnePop.click_gui.theme_widget_name_g;
      int nm_b = OnePop.click_gui.theme_widget_name_b;
      int nm_a = OnePop.click_gui.theme_widget_name_a;
      int bg_r = OnePop.click_gui.theme_widget_background_r;
      int bg_g = OnePop.click_gui.theme_widget_background_g;
      int bg_b = OnePop.click_gui.theme_widget_background_b;
      int bg_a = OnePop.click_gui.theme_widget_background_a;
      int bd_r = OnePop.click_gui.theme_widget_border_r;
      int bd_g = OnePop.click_gui.theme_widget_border_g;
      int bd_b = OnePop.click_gui.theme_widget_border_b;
      OnePopDraw var10000;
      if (this.module.is_active()) {
         OnePopDraw.draw_rect(this.x, this.save_y, this.x + this.width - separe, this.save_y + this.height, bg_r, bg_g, bg_b, bg_a);
         var10000 = this.font;
         OnePopDraw.draw_string(this.module_name, this.x + separe, this.save_y + 2, nm_r, nm_g, nm_b, nm_a);
         if (this.is_open()) {
            var10000 = this.font;
            OnePopDraw.draw_string("-", this.x + separe + this.width - 4 - this.font.get_string_width("-") - 1, this.save_y + 2, nm_r, nm_g, nm_b, nm_a);
         } else {
            var10000 = this.font;
            OnePopDraw.draw_string("+", this.x + separe + this.width - 4 - this.font.get_string_width("+") - 1, this.save_y + 2, nm_r, nm_g, nm_b, nm_a);
         }
      } else {
         var10000 = this.font;
         OnePopDraw.draw_string(this.module_name, this.x + separe, this.save_y + 2, nm_r, nm_g, nm_b, nm_a);
         if (this.is_open()) {
            var10000 = this.font;
            OnePopDraw.draw_string("-", this.x + separe + this.width - 4 - this.font.get_string_width("-") - 1, this.save_y + 2, nm_r, nm_g, nm_b, nm_a);
         } else {
            var10000 = this.font;
            OnePopDraw.draw_string("+", this.x + separe + this.width - 4 - this.font.get_string_width("+") - 1, this.save_y + 2, nm_r, nm_g, nm_b, nm_a);
         }
      }

      if ((new TurokRect(this.x + separe, this.save_y, this.width - separe * 2, this.opened_height)).collideWithMouse(new TurokMouse(mx, my))) {
         int cherosin = TurokMath.clamp(this.tick_height_animation.getCurrentTicksCount(5.0D), 0, this.opened_height);
         OnePopDraw.draw_rect(this.master.get_x() - 1, this.save_y, this.master.get_width() + 1, cherosin, bd_r, bd_g, bd_b, this.border_a, this.border_size, "right-left");
         var10000 = this.font;
         OnePopDraw.draw_string(this.module.get_description(), 1, 1, 255, 255, 255, 255);
      } else {
         this.tick_height_animation.reset();
      }

      Iterator var17 = this.widget.iterator();

      while(var17.hasNext()) {
         OnePopAbstractWidget widgets = (OnePopAbstractWidget)var17.next();
         widgets.set_x(this.get_x());
         if (this.opened) {
            this.opened_height = this.height + this.settings_height - this.height;
            widgets.render(this.get_save_y(), separe, mx, my);
         } else {
            this.opened_height = this.height;
         }
      }

   }
}
