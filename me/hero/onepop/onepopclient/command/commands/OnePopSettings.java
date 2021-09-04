package me.hero.onepop.onepopclient.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.command.OnePopCommand;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;

public class OnePopSettings extends OnePopCommand {
   public OnePopSettings() {
      super("settings", "To save/load settings.");
   }

   public boolean get_message(String[] message) {
      String msg = "null";
      if (message.length > 1) {
         msg = message[1];
      }

      if (msg.equals("null")) {
         OnePopMessageUtil.send_client_error_message(this.current_prefix() + "settings <save/load>");
         return true;
      } else {
         ChatFormatting c = ChatFormatting.GRAY;
         if (msg.equalsIgnoreCase("save")) {
            OnePop.get_config_manager().save_settings();
            OnePopMessageUtil.send_client_message(ChatFormatting.GREEN + "Successfully " + c + "saved!");
         } else {
            if (!msg.equalsIgnoreCase("load")) {
               OnePopMessageUtil.send_client_error_message(this.current_prefix() + "settings <save/load>");
               return true;
            }

            OnePop.get_config_manager().load_settings();
            OnePopMessageUtil.send_client_message(ChatFormatting.GREEN + "Successfully " + c + "loaded!");
         }

         return true;
      }
   }
}
