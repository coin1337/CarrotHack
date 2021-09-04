package me.hero.onepop.onepopclient.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Iterator;
import me.hero.onepop.onepopclient.command.OnePopCommand;
import me.hero.onepop.onepopclient.util.OnePopFriendUtil;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import me.hero.onepop.onepopclient.util.OnePopFriendUtil.Friend;

public class OnePopFriend extends OnePopCommand {
   public static ChatFormatting red;
   public static ChatFormatting green;
   public static ChatFormatting bold;
   public static ChatFormatting reset;

   public OnePopFriend() {
      super("friend", "To add friends");
   }

   public boolean get_message(String[] message) {
      if (message.length == 1) {
         OnePopMessageUtil.send_client_message("Add - add friend");
         OnePopMessageUtil.send_client_message("Del - delete friend");
         OnePopMessageUtil.send_client_message("List - list friends");
         return true;
      } else if (message.length == 2) {
         if (!message[1].equalsIgnoreCase("list")) {
            if (OnePopFriendUtil.isFriend(message[1])) {
               OnePopMessageUtil.send_client_message("Player " + green + bold + message[1] + reset + " is your friend :D");
               return true;
            } else {
               OnePopMessageUtil.send_client_error_message("Player " + red + bold + message[1] + reset + " is not your friend :(");
               return true;
            }
         } else {
            if (OnePopFriendUtil.friends.isEmpty()) {
               OnePopMessageUtil.send_client_message("You appear to have " + red + bold + "no" + reset + " friends :(");
            } else {
               Iterator var4 = OnePopFriendUtil.friends.iterator();

               while(var4.hasNext()) {
                  Friend friend = (Friend)var4.next();
                  OnePopMessageUtil.send_client_message("" + green + bold + friend.getUsername());
               }
            }

            return true;
         }
      } else {
         if (message.length >= 3) {
            Friend f;
            if (message[1].equalsIgnoreCase("add")) {
               if (OnePopFriendUtil.isFriend(message[2])) {
                  OnePopMessageUtil.send_client_message("Player " + green + bold + message[2] + reset + " is already your friend :D");
                  return true;
               }

               f = OnePopFriendUtil.get_friend_object(message[2]);
               if (f == null) {
                  OnePopMessageUtil.send_client_error_message("Cannot find " + red + bold + "UUID" + reset + " for that player :(");
                  return true;
               }

               OnePopFriendUtil.friends.add(f);
               OnePopMessageUtil.send_client_message("Player " + green + bold + message[2] + reset + " is now your friend :D");
               return true;
            }

            if (message[1].equalsIgnoreCase("del") || message[1].equalsIgnoreCase("remove") || message[1].equalsIgnoreCase("delete")) {
               if (!OnePopFriendUtil.isFriend(message[2])) {
                  OnePopMessageUtil.send_client_message("Player " + red + bold + message[2] + reset + " is already not your friend :/");
                  return true;
               } else {
                  f = (Friend)OnePopFriendUtil.friends.stream().filter((friendx) -> {
                     return friendx.getUsername().equalsIgnoreCase(message[2]);
                  }).findFirst().get();
                  OnePopFriendUtil.friends.remove(f);
                  OnePopMessageUtil.send_client_message("Player " + red + bold + message[2] + reset + " is now not your friend :(");
                  return true;
               }
            }
         }

         return true;
      }
   }

   static {
      red = ChatFormatting.RED;
      green = ChatFormatting.GREEN;
      bold = ChatFormatting.BOLD;
      reset = ChatFormatting.RESET;
   }
}
