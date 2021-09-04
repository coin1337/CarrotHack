package me.hero.onepop.onepopclient.hacks.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Predicate;
import me.hero.onepop.onepopclient.event.events.OnePopEventPacket;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketEntityStatus;

public class OnePopTotemAnnouncer extends OnePopHack {
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

            mc.field_71439_g.func_71165_d(entity.func_70005_c_() + " popped " + count + " totems. :3");
         }
      }

   }, new Predicate[0]);

   public OnePopTotemAnnouncer() {
      super(OnePopCategory.ONEPOP_CHAT);
      this.name = "Coffe Announcer";
      this.tag = "TotemAnnouncer";
      this.description = "uwu owow aura ouqe";
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
            mc.field_71439_g.func_71165_d(player.func_70005_c_() + " died after popping " + count + " totems !!femboy power!!");
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
