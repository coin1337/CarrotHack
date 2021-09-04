package me.hero.onepop.onepopclient.command.commands;

import java.util.Iterator;
import java.util.List;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.command.OnePopCommand;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopDrawnUtil;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;

public class OnePopDrawn extends OnePopCommand {
   public OnePopDrawn() {
      super("drawn", "Hide elements of the array list");
   }

   public boolean get_message(String[] message) {
      if (message.length == 1) {
         OnePopMessageUtil.send_client_error_message("module name needed");
         return true;
      } else if (message.length == 2) {
         if (this.is_module(message[1])) {
            OnePopDrawnUtil.add_remove_item(message[1]);
            OnePop.get_config_manager().save_settings();
         } else {
            OnePopMessageUtil.send_client_error_message("cannot find module by name: " + message[1]);
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean is_module(String s) {
      List<OnePopHack> modules = OnePop.get_hack_manager().get_array_hacks();
      Iterator var3 = modules.iterator();

      OnePopHack module;
      do {
         if (!var3.hasNext()) {
            return false;
         }

         module = (OnePopHack)var3.next();
      } while(!module.get_tag().equalsIgnoreCase(s));

      return true;
   }
}
