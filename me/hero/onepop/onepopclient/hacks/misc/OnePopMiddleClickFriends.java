package me.hero.onepop.onepopclient.hacks.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopFriendUtil;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import me.hero.onepop.onepopclient.util.OnePopFriendUtil.Friend;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import org.lwjgl.input.Mouse;

public class OnePopMiddleClickFriends extends OnePopHack {
   private boolean clicked = false;
   public static ChatFormatting red;
   public static ChatFormatting green;
   public static ChatFormatting bold;
   public static ChatFormatting reset;

   public OnePopMiddleClickFriends() {
      super(OnePopCategory.ONEPOP_MISC);
      this.name = "Middleclick Friends";
      this.tag = "MiddleclickFriends";
      this.description = "you press button and the world becomes a better place :D";
   }

   public void update() {
      if (mc.field_71462_r == null) {
         if (!Mouse.isButtonDown(2)) {
            this.clicked = false;
         } else {
            if (!this.clicked) {
               this.clicked = true;
               RayTraceResult result = mc.field_71476_x;
               if (result == null || result.field_72313_a != Type.ENTITY) {
                  return;
               }

               if (!(result.field_72308_g instanceof EntityPlayer)) {
                  return;
               }

               Entity player = result.field_72308_g;
               Friend f;
               if (OnePopFriendUtil.isFriend(player.func_70005_c_())) {
                  f = (Friend)OnePopFriendUtil.friends.stream().filter((friend) -> {
                     return friend.getUsername().equalsIgnoreCase(player.func_70005_c_());
                  }).findFirst().get();
                  OnePopFriendUtil.friends.remove(f);
                  if (OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClientLanguage").in("PT")) {
                     OnePopMessageUtil.send_client_message(red + player.func_70005_c_() + reset + " virou negro :(");
                  } else {
                     OnePopMessageUtil.send_client_message(red + player.func_70005_c_() + reset + " is now black :(");
                  }
               } else {
                  f = OnePopFriendUtil.get_friend_object(player.func_70005_c_());
                  OnePopFriendUtil.friends.add(f);
                  if (OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClientLanguage").in("PT")) {
                     OnePopMessageUtil.send_client_message(green + player.func_70005_c_() + reset + " virou branco :D");
                  } else {
                     OnePopMessageUtil.send_client_message(green + player.func_70005_c_() + reset + " is now white :D");
                  }
               }
            }

         }
      }
   }

   static {
      red = ChatFormatting.RED;
      green = ChatFormatting.GREEN;
      bold = ChatFormatting.BOLD;
      reset = ChatFormatting.RESET;
   }
}
