package me.hero.onepop.onepopclient.command;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import me.hero.onepop.onepopclient.command.commands.OnePopAlert;
import me.hero.onepop.onepopclient.command.commands.OnePopBind;
import me.hero.onepop.onepopclient.command.commands.OnePopClip;
import me.hero.onepop.onepopclient.command.commands.OnePopConfig;
import me.hero.onepop.onepopclient.command.commands.OnePopCustom;
import me.hero.onepop.onepopclient.command.commands.OnePopDrawn;
import me.hero.onepop.onepopclient.command.commands.OnePopEnemy;
import me.hero.onepop.onepopclient.command.commands.OnePopEzMessage;
import me.hero.onepop.onepopclient.command.commands.OnePopFriend;
import me.hero.onepop.onepopclient.command.commands.OnePopHelp;
import me.hero.onepop.onepopclient.command.commands.OnePopPrefix;
import me.hero.onepop.onepopclient.command.commands.OnePopSettings;
import me.hero.onepop.onepopclient.command.commands.OnePopToggle;
import me.hero.turok.values.TurokOldString;
import net.minecraft.util.text.Style;

public class OnePopCommands {
   public static ArrayList<OnePopCommand> command_list = new ArrayList();
   static HashMap<String, OnePopCommand> list_command = new HashMap();
   public static final TurokOldString prefix = new TurokOldString("Prefix", "Prefix", ".");
   public final Style style;

   public OnePopCommands(Style style_) {
      this.style = style_;
      add_command(new OnePopClip());
      add_command(new OnePopCustom());
      add_command(new OnePopBind());
      add_command(new OnePopPrefix());
      add_command(new OnePopSettings());
      add_command(new OnePopToggle());
      add_command(new OnePopAlert());
      add_command(new OnePopHelp());
      add_command(new OnePopFriend());
      add_command(new OnePopDrawn());
      add_command(new OnePopEzMessage());
      add_command(new OnePopEnemy());
      add_command(new OnePopConfig());
      command_list.sort(Comparator.comparing(OnePopCommand::get_name));
   }

   public static void add_command(OnePopCommand command) {
      command_list.add(command);
      list_command.put(command.get_name().toLowerCase(), command);
   }

   public String[] get_message(String message) {
      String[] arguments = new String[0];
      if (this.has_prefix(message)) {
         arguments = message.replaceFirst(prefix.get_value(), "").split(" ");
      }

      return arguments;
   }

   public boolean has_prefix(String message) {
      return message.startsWith(prefix.get_value());
   }

   public void set_prefix(String new_prefix) {
      prefix.set_value(new_prefix);
   }

   public String get_prefix() {
      return prefix.get_value();
   }

   public static ArrayList<OnePopCommand> get_pure_command_list() {
      return command_list;
   }

   public static OnePopCommand get_command_with_name(String name) {
      return (OnePopCommand)list_command.get(name.toLowerCase());
   }
}
