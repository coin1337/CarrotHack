package me.hero.onepop.onepopclient.hacks.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Predicate;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.event.events.OnePopEventPacket;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopFriendUtil;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketEntityStatus;

public class OnePopTotempop extends OnePopHack {
   public static final HashMap<String, Integer> totem_pop_counter = new HashMap();
   public static ChatFormatting red;
   public static ChatFormatting green;
   public static ChatFormatting gold;
   public static ChatFormatting grey;
   public static ChatFormatting bold;
   public static ChatFormatting reset;
   @EventHandler
   private final Listener<OnePopEventPacket.ReceivePacket> packet_event = new Listener((event) -> {
      if (event.get_packet() instanceof SPacketEntityStatus) {
         SPacketEntityStatus packet = (SPacketEntityStatus)event.get_packet();
         if (packet.func_149160_c() == 35) {
            Entity entity = packet.func_149161_a(mc.field_71441_e);
            int count = 1;
            if (totem_pop_counter.containsKey(entity.func_70005_c_())) {
               count = (Integer)totem_pop_counter.get(entity.func_70005_c_());
               HashMap var10000 = totem_pop_counter;
               String var10001 = entity.func_70005_c_();
               ++count;
               var10000.put(var10001, count);
            } else {
               totem_pop_counter.put(entity.func_70005_c_(), count);
            }

            if (entity == mc.field_71439_g) {
               return;
            }

            if (OnePopFriendUtil.isFriend(entity.func_70005_c_())) {
               if (OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClientLanguage").in("PT")) {
                  OnePopMessageUtil.send_client_message(red + "" + bold + " TotemPop " + reset + grey + " > " + reset + bold + green + entity.func_70005_c_() + reset + " popou " + bold + count + reset + " totems. vai ajudar elekkkk");
               } else {
                  OnePopMessageUtil.send_client_message(red + "" + bold + " TotemPop " + reset + grey + " > " + reset + bold + green + entity.func_70005_c_() + reset + " just popped " + bold + count + reset + " you gotta help him");
               }
            } else if (OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClientLanguage").in("PT")) {
               OnePopMessageUtil.send_client_message(red + "" + bold + " TotemPop " + reset + grey + " > " + reset + bold + red + entity.func_70005_c_() + reset + " popou " + bold + count + reset + " totems. cara fracassadokkk");
            } else {
               OnePopMessageUtil.send_client_message(red + "" + bold + " TotemPop " + reset + grey + " > " + reset + bold + red + entity.func_70005_c_() + reset + " just popped " + bold + count + reset + " totems. what a loser");
            }
         }
      }

   }, new Predicate[0]);

   public OnePopTotempop() {
      super(OnePopCategory.ONEPOP_CHAT);
      this.name = "Totem Pop";
      this.tag = "TotemPopCounter";
      this.description = "k,t,e.. .fdfasdf";
   }

   public void update() {
      Iterator var1 = mc.field_71441_e.field_73010_i.iterator();

      while(true) {
         EntityPlayer player;
         do {
            do {
               if (!var1.hasNext()) {
                  return;
               }

               player = (EntityPlayer)var1.next();
            } while(!totem_pop_counter.containsKey(player.func_70005_c_()));
         } while(!player.field_70128_L && !(player.func_110143_aJ() <= 0.0F));

         int count = (Integer)totem_pop_counter.get(player.func_70005_c_());
         totem_pop_counter.remove(player.func_70005_c_());
         if (player != mc.field_71439_g) {
            if (OnePopFriendUtil.isFriend(player.func_70005_c_())) {
               OnePopMessageUtil.send_client_message(red + "" + bold + " TotemPop " + reset + grey + " > " + reset + bold + green + player.func_70005_c_() + reset + " morreu depois de popar " + bold + count + reset + " totems. :(((( culpa do pedroperry");
            } else {
               OnePopMessageUtil.send_client_message(red + "" + bold + " TotemPop " + reset + grey + " > " + reset + bold + red + player.func_70005_c_() + reset + " morreu depois de popar " + bold + count + reset + " totems. fracassadokkk");
            }
         }
      }
   }

   static {
      red = ChatFormatting.RED;
      green = ChatFormatting.GREEN;
      gold = ChatFormatting.GOLD;
      grey = ChatFormatting.GRAY;
      bold = ChatFormatting.BOLD;
      reset = ChatFormatting.RESET;
   }
}
