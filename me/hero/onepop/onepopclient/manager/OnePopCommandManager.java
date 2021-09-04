package me.hero.onepop.onepopclient.manager;

import me.hero.onepop.onepopclient.command.OnePopCommands;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;

public class OnePopCommandManager {
   public static OnePopCommands command_list;

   public OnePopCommandManager() {
      command_list = new OnePopCommands((new Style()).func_150238_a(TextFormatting.BLUE));
   }

   public static void set_prefix(String new_prefix) {
      command_list.set_prefix(new_prefix);
   }

   public static String get_prefix() {
      return command_list.get_prefix();
   }
}
