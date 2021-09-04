package me.hero.onepop.onepopclient.hacks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.event.OnePopEventBus;
import me.hero.onepop.onepopclient.event.events.OnePopEventRender;
import me.hero.onepop.onepopclient.event.events.OnePopEventRenderEntityModel;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import me.zero.alpine.fork.listener.Listenable;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class OnePopHack implements Listenable {
   public OnePopCategory category;
   public String name = "";
   public String tag = "";
   public String description = "";
   public int bind = -1;
   public boolean state_module;
   public boolean toggle_message = true;
   public boolean widget_usage = false;
   public static final Minecraft mc = Minecraft.func_71410_x();

   public OnePopHack(OnePopCategory category) {
      this.category = category;
   }

   public void set_bind(int key) {
      this.bind = key;
   }

   public void set_if_can_send_message_toggle(boolean value) {
      this.toggle_message = value;
   }

   public boolean is_active() {
      return this.state_module;
   }

   public boolean is_disabled() {
      return !this.is_active();
   }

   public boolean using_widget() {
      return this.widget_usage;
   }

   public String get_name() {
      return this.name;
   }

   public String get_tag() {
      return this.tag;
   }

   public String get_description() {
      return this.description;
   }

   public int get_bind(int type) {
      return this.bind;
   }

   public String get_bind(String type) {
      String converted_bind = "null";
      if (this.get_bind(0) < 0) {
         converted_bind = "NONE";
      }

      if (!converted_bind.equals("NONE")) {
         String key = Keyboard.getKeyName(this.get_bind(0));
         converted_bind = Character.toUpperCase(key.charAt(0)) + (key.length() != 1 ? key.substring(1).toLowerCase() : "");
      } else {
         converted_bind = "None";
      }

      return converted_bind;
   }

   public OnePopCategory get_category() {
      return this.category;
   }

   public boolean can_send_message_when_toggle() {
      return this.toggle_message;
   }

   public void set_disable() {
      this.state_module = false;
      this.disable();
      OnePopEventBus.EVENT_BUS.unsubscribe(this);
   }

   public void set_enable() {
      this.state_module = true;
      this.enable();
      OnePopEventBus.EVENT_BUS.subscribe(this);
   }

   public void set_active(boolean value) {
      if (this.state_module != value) {
         if (value) {
            this.set_enable();
         } else {
            this.set_disable();
         }
      }

      if (!this.tag.equals("GUI") && !this.tag.equals("HUD") && this.toggle_message) {
         OnePopMessageUtil.toggle_message(this);
      }

   }

   public void toggle() {
      this.set_active(!this.is_active());
   }

   protected OnePopSetting create(String name, String tag, int value, int min, int max) {
      OnePop.get_setting_manager().register(new OnePopSetting(this, name, tag, value, min, max));
      return OnePop.get_setting_manager().get_setting_with_tag(this, tag);
   }

   protected OnePopSetting create(String name, String tag, double value, double min, double max) {
      OnePop.get_setting_manager().register(new OnePopSetting(this, name, tag, value, min, max));
      return OnePop.get_setting_manager().get_setting_with_tag(this, tag);
   }

   protected OnePopSetting create(String name, String tag, boolean value) {
      OnePop.get_setting_manager().register(new OnePopSetting(this, name, tag, value));
      return OnePop.get_setting_manager().get_setting_with_tag(this, tag);
   }

   protected OnePopSetting create(String name, String tag, String value) {
      OnePop.get_setting_manager().register(new OnePopSetting(this, name, tag, value));
      return OnePop.get_setting_manager().get_setting_with_tag(this, tag);
   }

   protected OnePopSetting create(String name, String tag, String value, List<String> values) {
      OnePop.get_setting_manager().register(new OnePopSetting(this, name, tag, values, value));
      return OnePop.get_setting_manager().get_setting_with_tag(this, tag);
   }

   protected List<String> combobox(String... item) {
      return new ArrayList(Arrays.asList(item));
   }

   public void render(OnePopEventRender event) {
   }

   public void render() {
   }

   public void update() {
   }

   public void event_widget() {
   }

   protected void disable() {
   }

   protected void enable() {
   }

   public String array_detail() {
      return null;
   }

   public void on_render_model(OnePopEventRenderEntityModel event) {
   }
}
