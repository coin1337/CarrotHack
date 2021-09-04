package me.hero.onepop.onepopclient.command.commands;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.command.OnePopCommand;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import org.lwjgl.input.Keyboard;

public class OnePopBind extends OnePopCommand {
   public OnePopBind() {
      super("bind", "bind module to key");
   }

   public boolean get_message(String[] message) {
      String module = "null;";
      String key = "null";
      if (message.length == 3) {
         module = message[1].toUpperCase();
         key = message[2].toUpperCase();
      } else if (message.length > 1) {
         OnePopMessageUtil.send_client_error_message(this.current_prefix() + "bind <ModuleTag> <key>");
         return true;
      }

      if (!module.equals("null") && !key.equals("null")) {
         OnePopHack module_requested = OnePop.get_hack_manager().get_module_with_tag(module);
         if (module_requested == null) {
            OnePopMessageUtil.send_client_error_message("Module does not exist.");
            return true;
         } else if (key.equalsIgnoreCase("NONE")) {
            module_requested.set_bind(0);
            OnePopMessageUtil.send_client_message(module_requested.get_tag() + " is bound to None.");
            return true;
         } else {
            int new_bind = Keyboard.getKeyIndex(key.toUpperCase());
            if (new_bind == 0) {
               OnePopMessageUtil.send_client_error_message("Key does not exist.");
               return true;
            } else if (new_bind == module_requested.get_bind(0)) {
               OnePopMessageUtil.send_client_error_message(module_requested.get_tag() + " is already bound to this key");
               return true;
            } else {
               module_requested.set_bind(new_bind);
               OnePopMessageUtil.send_client_message(module_requested.get_tag() + " is bound to " + module_requested.get_bind(""));
               return true;
            }
         }
      } else {
         OnePopMessageUtil.send_client_error_message(this.current_prefix() + "bind <ModuleTag> <key>");
         return true;
      }
   }
}
