package me.hero.onepop.onepopclient.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Iterator;
import me.hero.onepop.onepopclient.command.OnePopCommand;
import me.hero.onepop.onepopclient.util.OnePopEnemyUtil;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import me.hero.onepop.onepopclient.util.OnePopEnemyUtil.Enemy;

public class OnePopEnemy extends OnePopCommand {
   public static ChatFormatting red;
   public static ChatFormatting green;
   public static ChatFormatting bold;
   public static ChatFormatting reset;

   public OnePopEnemy() {
      super("enemy", "To add enemy");
   }

   public boolean get_message(String[] message) {
      if (message.length == 1) {
         OnePopMessageUtil.send_client_message("Add - add enemy");
         OnePopMessageUtil.send_client_message("Del - delete enemy");
         OnePopMessageUtil.send_client_message("List - list enemies");
         return true;
      } else if (message.length == 2) {
         if (!message[1].equalsIgnoreCase("list")) {
            if (OnePopEnemyUtil.isEnemy(message[1])) {
               OnePopMessageUtil.send_client_message("Player " + green + bold + message[1] + reset + " is your Enemy D:");
               return true;
            } else {
               OnePopMessageUtil.send_client_error_message("Player " + red + bold + message[1] + reset + " is not your Enemy :)");
               return true;
            }
         } else {
            if (OnePopEnemyUtil.enemies.isEmpty()) {
               OnePopMessageUtil.send_client_message("You appear to have " + red + bold + "no" + reset + " enemies :)");
            } else {
               Iterator var4 = OnePopEnemyUtil.enemies.iterator();

               while(var4.hasNext()) {
                  Enemy Enemy = (Enemy)var4.next();
                  OnePopMessageUtil.send_client_message("" + green + bold + Enemy.getUsername());
               }
            }

            return true;
         }
      } else {
         if (message.length >= 3) {
            Enemy f;
            if (message[1].equalsIgnoreCase("add")) {
               if (OnePopEnemyUtil.isEnemy(message[2])) {
                  OnePopMessageUtil.send_client_message("Player " + green + bold + message[2] + reset + " is already your Enemy D:");
                  return true;
               }

               f = OnePopEnemyUtil.get_enemy_object(message[2]);
               if (f == null) {
                  OnePopMessageUtil.send_client_error_message("Cannot find " + red + bold + "UUID" + reset + " for that player :(");
                  return true;
               }

               OnePopEnemyUtil.enemies.add(f);
               OnePopMessageUtil.send_client_message("Player " + green + bold + message[2] + reset + " is now your Enemy D:");
               return true;
            }

            if (message[1].equalsIgnoreCase("del") || message[1].equalsIgnoreCase("remove") || message[1].equalsIgnoreCase("delete")) {
               if (!OnePopEnemyUtil.isEnemy(message[2])) {
                  OnePopMessageUtil.send_client_message("Player " + red + bold + message[2] + reset + " is already not your Enemy :/");
                  return true;
               } else {
                  f = (Enemy)OnePopEnemyUtil.enemies.stream().filter((Enemyx) -> {
                     return Enemyx.getUsername().equalsIgnoreCase(message[2]);
                  }).findFirst().get();
                  OnePopEnemyUtil.enemies.remove(f);
                  OnePopMessageUtil.send_client_message("Player " + red + bold + message[2] + reset + " is now not your Enemy :)");
                  return true;
               }
            }
         }

         return true;
      }
   }

   static {
      red = ChatFormatting.GREEN;
      green = ChatFormatting.RED;
      bold = ChatFormatting.BOLD;
      reset = ChatFormatting.RESET;
   }
}
