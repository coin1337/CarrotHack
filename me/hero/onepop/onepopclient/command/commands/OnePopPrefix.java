package me.hero.onepop.onepopclient.command.commands;

import me.hero.onepop.onepopclient.command.OnePopCommand;
import me.hero.onepop.onepopclient.manager.OnePopCommandManager;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;

public class OnePopPrefix extends OnePopCommand {
   public OnePopPrefix() {
      super("prefix", "Change prefix.");
   }

   public boolean get_message(String[] message) {
      String prefix = "null";
      if (message.length > 1) {
         prefix = message[1];
      }

      if (message.length > 2) {
         OnePopMessageUtil.send_client_error_message(this.current_prefix() + "prefix <character>");
         return true;
      } else if (prefix.equals("null")) {
         OnePopMessageUtil.send_client_error_message(this.current_prefix() + "prefix <character>");
         return true;
      } else {
         OnePopCommandManager.set_prefix(prefix);
         OnePopMessageUtil.send_client_message("The new prefix is " + prefix);
         return true;
      }
   }
}
