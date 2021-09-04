package me.hero.onepop.onepopclient.command;

import me.hero.onepop.onepopclient.manager.OnePopCommandManager;

public class OnePopCommand {
   String name;
   String description;

   public OnePopCommand(String name, String description) {
      this.name = name;
      this.description = description;
   }

   public boolean get_message(String[] message) {
      return false;
   }

   public String get_name() {
      return this.name;
   }

   public String get_description() {
      return this.description;
   }

   public String current_prefix() {
      return OnePopCommandManager.get_prefix();
   }
}
