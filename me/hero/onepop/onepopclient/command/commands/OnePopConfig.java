package me.hero.onepop.onepopclient.command.commands;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.command.OnePopCommand;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;

public class OnePopConfig extends OnePopCommand {
   public OnePopConfig() {
      super("config", "changes which config is loaded");
   }

   public boolean get_message(String[] message) {
      if (message.length == 1) {
         OnePopMessageUtil.send_client_error_message("config needed");
         return true;
      } else if (message.length == 2) {
         String config = message[1];
         if (OnePop.get_config_manager().set_active_config_folder(config + "/")) {
            OnePopMessageUtil.send_client_message("new config folder set as " + config);
         } else {
            OnePopMessageUtil.send_client_error_message("cannot set folder to " + config);
         }

         return true;
      } else {
         OnePopMessageUtil.send_client_error_message("config path may only be one word");
         return true;
      }
   }
}
