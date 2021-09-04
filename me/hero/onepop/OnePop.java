package me.hero.onepop;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.hero.onepop.onepopclient.event.OnePopEventHandler;
import me.hero.onepop.onepopclient.event.OnePopEventRegister;
import me.hero.onepop.onepopclient.guiscreen.OnePopGUI;
import me.hero.onepop.onepopclient.guiscreen.OnePopHUD;
import me.hero.onepop.onepopclient.manager.OnePopCommandManager;
import me.hero.onepop.onepopclient.manager.OnePopConfigManager;
import me.hero.onepop.onepopclient.manager.OnePopEventManager;
import me.hero.onepop.onepopclient.manager.OnePopHUDManager;
import me.hero.onepop.onepopclient.manager.OnePopModuleManager;
import me.hero.onepop.onepopclient.manager.OnePopSettingManager;
import me.hero.turok.task.FontOld;
import me.rina.turok.Turok;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(
   modid = "1pop",
   version = "0.9.7"
)
public class OnePop {
   @Instance
   private static OnePop MASTER;
   public static final String ONEPOP_NAME = "1pop";
   public static final String ONEPOP_VERSION = "0.9.7";
   public static final String ONEPOP_SIGN = " ";
   public static final int ONEPOP_KEY_GUI = 54;
   public static final int ONEPOP_KEY_DELETE = 211;
   public static final int ONEPOP_KEY_GUI_ESCAPE = 1;
   public static Logger onepop_register_log;
   private static OnePopSettingManager setting_manager;
   private static OnePopConfigManager config_manager;
   private static OnePopModuleManager module_manager;
   private static OnePopHUDManager hud_manager;
   public static OnePopDiscordPresence discord_rpc;
   public static OnePopGUI click_gui;
   public static OnePopHUD click_hud;
   public static Turok turok;
   public static ChatFormatting g;
   public static ChatFormatting r;

   @EventHandler
   public void OnePopStarting(FMLInitializationEvent event) {
      this.init_log("1pop");
      Display.setTitle("1pop 0.9.7");
      this.load();
   }

   public void load() {
      OnePopEventHandler.INSTANCE = new OnePopEventHandler();
      send_minecraft_log("initialising managers");
      setting_manager = new OnePopSettingManager();
      config_manager = new OnePopConfigManager();
      module_manager = new OnePopModuleManager();
      hud_manager = new OnePopHUDManager();
      OnePopEventManager event_manager = new OnePopEventManager();
      OnePopCommandManager command_manager = new OnePopCommandManager();
      send_minecraft_log("done");
      send_minecraft_log("initialising guis");
      click_gui = new OnePopGUI();
      click_hud = new OnePopHUD();
      send_minecraft_log("done");
      send_minecraft_log("initialising skidded framework");
      turok = new Turok();
      send_minecraft_log("done");
      send_minecraft_log("initialising commands and events");
      OnePopEventRegister.register_command_manager(command_manager);
      OnePopEventRegister.register_module_manager(event_manager);
      send_minecraft_log("done");
      send_minecraft_log("loading settings");
      config_manager.load_settings();
      send_minecraft_log("done");
      send_minecraft_log("starting discord rpc");
      discord_rpc = new OnePopDiscordPresence();
      if (module_manager.get_module_with_tag("RPC").is_active()) {
         discord_rpc.run();
      }

      send_minecraft_log("done, sleeping on da 1pop.club zzzz");
      if (module_manager.get_module_with_tag("GUI").is_active()) {
         module_manager.get_module_with_tag("GUI").set_active(false);
      }

      if (module_manager.get_module_with_tag("HUD").is_active()) {
         module_manager.get_module_with_tag("HUD").set_active(false);
      }

      send_minecraft_log("client started");
      send_minecraft_log("oi jumveer");
      send_minecraft_log(Turok.NAME + " by " + Turok.AUTHOR);
      send_minecraft_log(Turok.NAME + " " + Turok.VERSION + " initiated");
   }

   public void init_log(String name) {
      onepop_register_log = LogManager.getLogger(name);
      send_minecraft_log("starting 1pop");
   }

   public static void send_minecraft_log(String log) {
      onepop_register_log.info(log);
   }

   public static String get_name() {
      return "1pop";
   }

   public static String get_version() {
      return "0.9.7";
   }

   public static String get_actual_user() {
      return Minecraft.func_71410_x().func_110432_I().func_111285_a();
   }

   public static OnePopConfigManager get_config_manager() {
      return config_manager;
   }

   public static OnePopModuleManager get_hack_manager() {
      return module_manager;
   }

   public static OnePopSettingManager get_setting_manager() {
      return setting_manager;
   }

   public static OnePopHUDManager get_hud_manager() {
      return hud_manager;
   }

   public static OnePopModuleManager get_module_manager() {
      return module_manager;
   }

   public static OnePopEventHandler get_event_handler() {
      return OnePopEventHandler.INSTANCE;
   }

   public static String smoth(String base) {
      return FontOld.smoth(base);
   }

   public static OnePopDiscordPresence get_rpc() {
      return discord_rpc;
   }

   static {
      g = ChatFormatting.DARK_GRAY;
      r = ChatFormatting.RESET;
   }
}
