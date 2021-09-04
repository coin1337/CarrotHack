package me.hero.onepop.onepopclient.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.command.OnePopCommand;
import me.hero.onepop.onepopclient.util.OnePopEzMessageUtil;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;

public class OnePopEzMessage extends OnePopCommand {
   public OnePopEzMessage() {
      super("ezmessage", "Set ez mode");
   }

   public boolean get_message(String[] message) {
      if (message.length == 1) {
         OnePopMessageUtil.send_client_error_message("message needed");
         return true;
      } else if (message.length >= 2) {
         StringBuilder ez = new StringBuilder();
         boolean flag = true;
         String[] var4 = message;
         int var5 = message.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            String word = var4[var6];
            if (flag) {
               flag = false;
            } else {
               ez.append(word).append(" ");
            }
         }

         OnePopEzMessageUtil.set_message(ez.toString());
         OnePopMessageUtil.send_client_message("ez message changed to " + ChatFormatting.BOLD + ez.toString());
         OnePop.get_config_manager().save_settings();
         return true;
      } else {
         return false;
      }
   }
}
