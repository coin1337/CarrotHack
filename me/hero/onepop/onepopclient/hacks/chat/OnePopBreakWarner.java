package me.hero.onepop.onepopclient.hacks.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.event.events.OnePopEventPacket;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopFriendUtil;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.server.SPacketBlockBreakAnim;
import net.minecraft.util.math.BlockPos;

public class OnePopBreakWarner extends OnePopHack {
   OnePopSetting distanceToDetect = this.create("Max Break Distance", "WarnerMaxDistance", 2, 1, 5);
   OnePopSetting announce = this.create("Announce in chat", "WarnerAnnounce", false);
   OnePopSetting chatDelay = this.create("Chat Delay", "WarnerChatDelay", 18, 14, 25);
   private int delay;
   @EventHandler
   public Listener<OnePopEventPacket.ReceivePacket> packetReceiveListener = new Listener((event) -> {
      EntityPlayerSP player = mc.field_71439_g;
      WorldClient world = mc.field_71441_e;
      if (!Objects.isNull(player) && !Objects.isNull(world)) {
         if (event.get_packet() instanceof SPacketBlockBreakAnim) {
            SPacketBlockBreakAnim packet = (SPacketBlockBreakAnim)event.get_packet();
            BlockPos pos = packet.func_179821_b();
            if (this.pastDistance(player, pos, (double)this.distanceToDetect.get_value(1))) {
               this.sendChat();
            }
         }

      }
   }, new Predicate[0]);

   public OnePopBreakWarner() {
      super(OnePopCategory.ONEPOP_CHAT);
      this.name = "Break Warner";
      this.tag = "BreakWarner";
      this.description = "OI MINATO";
   }

   private boolean pastDistance(EntityPlayer player, BlockPos pos, double dist) {
      return player.func_174831_c(pos) <= Math.pow(dist, 2.0D);
   }

   public void sendChat() {
      if (this.delay > this.chatDelay.get_value(1) && this.announce.get_value(true)) {
         this.delay = 0;
         if (OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClientLanguage").in("PT")) {
            mc.field_71439_g.field_71174_a.func_147297_a(new CPacketChatMessage(this.getPlayer() + " para de quebrar meu pé porrakkk"));
         } else {
            mc.field_71439_g.field_71174_a.func_147297_a(new CPacketChatMessage(this.getPlayer() + " STOP BREAKING MY FUCKING FEET"));
         }
      }

      if (OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClientLanguage").in("PT")) {
         OnePopMessageUtil.send_client_message(ChatFormatting.AQUA + "BreakWarner" + ChatFormatting.RESET + " > " + ChatFormatting.RED + " tem um negrinho quebrando seu pékkkk");
      } else {
         OnePopMessageUtil.send_client_message(ChatFormatting.AQUA + "BreakWarner" + ChatFormatting.RESET + " > " + ChatFormatting.RED + " someone's breaking ur feet");
      }

      ++this.delay;
   }

   public String getPlayer() {
      List<EntityPlayer> entities = (List)mc.field_71441_e.field_73010_i.stream().filter((entityPlayer) -> {
         return !OnePopFriendUtil.isFriend(entityPlayer.func_70005_c_());
      }).collect(Collectors.toList());
      Iterator var2 = entities.iterator();

      EntityPlayer e;
      do {
         if (!var2.hasNext()) {
            return "";
         }

         e = (EntityPlayer)var2.next();
      } while(e.field_70128_L || e.func_110143_aJ() <= 0.0F || e.func_70005_c_().equals(mc.field_71439_g.func_70005_c_()) || !(e.func_184614_ca().func_77973_b() instanceof ItemTool));

      return e.func_70005_c_();
   }
}
