package me.hero.onepop.onepopclient.event;

import me.hero.onepop.onepopclient.manager.OnePopCommandManager;
import me.hero.onepop.onepopclient.manager.OnePopEventManager;
import net.minecraftforge.common.MinecraftForge;

public class OnePopEventRegister {
   public static void register_command_manager(OnePopCommandManager manager) {
      MinecraftForge.EVENT_BUS.register(manager);
   }

   public static void register_module_manager(OnePopEventManager manager) {
      MinecraftForge.EVENT_BUS.register(manager);
   }
}
