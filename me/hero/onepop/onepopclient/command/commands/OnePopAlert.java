package me.hero.onepop.onepopclient.command.commands;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.command.OnePopCommand;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;

public class OnePopAlert extends OnePopCommand {
   public OnePopAlert() {
      super("alert", "if the module should spam chat or not");
   }

   public boolean get_message(String[] message) {
      String module = "null";
      String state = "null";
      if (message.length > 1) {
         module = message[1];
      }

      if (message.length > 2) {
         state = message[2];
      }

      if (message.length > 3) {
         OnePopMessageUtil.send_client_error_message(this.current_prefix() + "t <ModuleName> <True/On/False/Off>");
         return true;
      } else if (module.equals("null")) {
         OnePopMessageUtil.send_client_error_message(this.current_prefix() + "t <ModuleName> <True/On/False/Off>");
         return true;
      } else if (state.equals("null")) {
         OnePopMessageUtil.send_client_error_message(this.current_prefix() + "t <ModuleName> <True/On/False/Off>");
         return true;
      } else {
         module = module.toLowerCase();
         state = state.toLowerCase();
         OnePopHack module_requested = OnePop.get_hack_manager().get_module_with_tag(module);
         if (module_requested == null) {
            OnePopMessageUtil.send_client_error_message("This module does not exist.");
            return true;
         } else {
            boolean value = true;
            if (!state.equals("true") && !state.equals("on")) {
               if (!state.equals("false") && !state.equals("off")) {
                  OnePopMessageUtil.send_client_error_message("This value does not exist. <True/On/False/Off>");
                  return true;
               }

               value = false;
            } else {
               value = true;
            }

            module_requested.set_if_can_send_message_toggle(value);
            OnePopMessageUtil.send_client_message("The actual value of " + module_requested.get_name() + " is " + Boolean.toString(module_requested.can_send_message_when_toggle()) + ".");
            return true;
         }
      }
   }
}
