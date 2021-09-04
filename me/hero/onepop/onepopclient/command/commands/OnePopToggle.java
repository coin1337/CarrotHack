package me.hero.onepop.onepopclient.command.commands;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.command.OnePopCommand;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.manager.OnePopCommandManager;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;

public class OnePopToggle extends OnePopCommand {
   public OnePopToggle() {
      super("t", "turn on and off stuffs");
   }

   public boolean get_message(String[] message) {
      String module = "null";
      if (message.length > 1) {
         module = message[1];
      }

      if (message.length > 2) {
         OnePopMessageUtil.send_client_error_message(this.current_prefix() + "t <ModuleNameNoSpace>");
         return true;
      } else if (module.equals("null")) {
         OnePopMessageUtil.send_client_error_message(OnePopCommandManager.get_prefix() + "t <ModuleNameNoSpace>");
         return true;
      } else {
         OnePopHack module_requested = OnePop.get_module_manager().get_module_with_tag(module);
         if (module_requested != null) {
            module_requested.toggle();
            OnePopMessageUtil.send_client_message("[" + module_requested.get_tag() + "] - Toggled to " + module_requested.is_active() + ".");
         } else {
            OnePopMessageUtil.send_client_error_message("Module does not exist.");
         }

         return true;
      }
   }
}
