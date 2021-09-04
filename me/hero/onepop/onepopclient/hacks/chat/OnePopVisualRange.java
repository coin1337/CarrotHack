package me.hero.onepop.onepopclient.hacks.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopFriendUtil;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class OnePopVisualRange extends OnePopHack {
   private List<String> people;

   public OnePopVisualRange() {
      super(OnePopCategory.ONEPOP_CHAT);
      this.name = "Visual Range";
      this.tag = "VisualRange";
      this.description = "bc using ur eyes is overrated";
   }

   public void enable() {
      this.people = new ArrayList();
   }

   public void update() {
      if (!(mc.field_71441_e == null | mc.field_71439_g == null)) {
         List<String> peoplenew = new ArrayList();
         List<EntityPlayer> playerEntities = mc.field_71441_e.field_73010_i;
         Iterator var3 = playerEntities.iterator();

         while(var3.hasNext()) {
            Entity e = (Entity)var3.next();
            if (!e.func_70005_c_().equals(mc.field_71439_g.func_70005_c_())) {
               peoplenew.add(e.func_70005_c_());
            }
         }

         if (peoplenew.size() > 0) {
            var3 = peoplenew.iterator();

            while(var3.hasNext()) {
               String name = (String)var3.next();
               if (!this.people.contains(name)) {
                  if (OnePopFriendUtil.isFriend(name)) {
                     if (OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClientLanguage").in("PT")) {
                        OnePopMessageUtil.send_client_message("to vendo um cara chamado " + ChatFormatting.RESET + ChatFormatting.GREEN + name + ChatFormatting.RESET + " :D");
                     } else {
                        OnePopMessageUtil.send_client_message(ChatFormatting.GREEN + name + ChatFormatting.RESET + " is entering your visual range :D");
                     }
                  } else if (OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClientLanguage").in("PT")) {
                     OnePopMessageUtil.send_client_message("to vendo um cara chamado " + ChatFormatting.RESET + ChatFormatting.RED + name + ChatFormatting.RESET + ". negrinhokkk");
                  } else {
                     OnePopMessageUtil.send_client_message(ChatFormatting.RED + name + ChatFormatting.RESET + " is entering your visual range :(");
                  }

                  this.people.add(name);
               }
            }
         }

      }
   }
}
