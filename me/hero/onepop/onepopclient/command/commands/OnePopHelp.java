package me.hero.onepop.onepopclient.command.commands;

import java.util.Iterator;
import me.hero.onepop.onepopclient.command.OnePopCommand;
import me.hero.onepop.onepopclient.command.OnePopCommands;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;

public class OnePopHelp extends OnePopCommand {
   public OnePopHelp() {
      super("help", "helps people");
   }

   public boolean get_message(String[] message) {
      String type = "null";
      if (message.length == 1) {
         type = "list";
      }

      if (message.length > 1) {
         type = message[1];
      }

      if (message.length > 2) {
         OnePopMessageUtil.send_client_error_message(this.current_prefix() + "help <List/NameCommand>");
         return true;
      } else if (type.equals("null")) {
         OnePopMessageUtil.send_client_error_message(this.current_prefix() + "help <List/NameCommand>");
         return true;
      } else if (!type.equalsIgnoreCase("list")) {
         OnePopCommand command_requested = OnePopCommands.get_command_with_name(type);
         if (command_requested == null) {
            OnePopMessageUtil.send_client_error_message("This command does not exist.");
            return true;
         } else {
            OnePopMessageUtil.send_client_message(command_requested.get_name() + " - " + command_requested.get_description());
            return true;
         }
      } else {
         Iterator var3 = OnePopCommands.get_pure_command_list().iterator();

         while(var3.hasNext()) {
            OnePopCommand commands = (OnePopCommand)var3.next();
            OnePopMessageUtil.send_client_message(commands.get_name());
         }

         return true;
      }
   }
}
