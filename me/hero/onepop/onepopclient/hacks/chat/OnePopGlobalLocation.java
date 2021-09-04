package me.hero.onepop.onepopclient.hacks.chat;

import java.util.function.Predicate;
import me.hero.onepop.onepopclient.event.events.OnePopEventPacket;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketEffect;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.network.play.server.SPacketSpawnMob;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class OnePopGlobalLocation extends OnePopHack {
   OnePopSetting thunder = this.create("Thunder", "Thunder", true);
   OnePopSetting slimes = this.create("Slimes", "Slimes", false);
   OnePopSetting wither = this.create("Wither", "Wither", false);
   OnePopSetting end_portal = this.create("End Portal", "EndPortal", true);
   OnePopSetting ender_dragon = this.create("Ender Dragon", "EnderDragon", false);
   OnePopSetting donkey = this.create("Donkey", "Donkey", false);
   @EventHandler
   private Listener<OnePopEventPacket> packet_event = new Listener((event) -> {
      if (event.get_packet() instanceof SPacketSpawnMob) {
         SPacketSpawnMob packet = (SPacketSpawnMob)event.get_packet();
         if (this.slimes.get_value(true)) {
            Minecraft mc = Minecraft.func_71410_x();
            if (packet.func_149025_e() == 55 && packet.func_186892_f() <= 40.0D && !mc.field_71441_e.func_180494_b(mc.field_71439_g.func_180425_c()).func_185359_l().toLowerCase().contains("swamp")) {
               BlockPos pos = new BlockPos(packet.func_186891_e(), packet.func_186892_f(), packet.func_186893_g());
               OnePopMessageUtil.send_client_message("Slime Spawned in chunk X:" + mc.field_71441_e.func_175726_f(pos).field_76635_g + " Z:" + mc.field_71441_e.func_175726_f(pos).field_76647_h);
            }
         }

         if (this.donkey.get_value(true) && packet.func_149025_e() == 31) {
            OnePopMessageUtil.send_client_message(String.format("Donkey spawned at %s %s %s", packet.func_186891_e(), packet.func_186892_f(), packet.func_186893_g()));
         }
      } else if (event.get_packet() instanceof SPacketSoundEffect) {
         SPacketSoundEffect packetx = (SPacketSoundEffect)event.get_packet();
         if (this.thunder.get_value(true) && packetx.func_186977_b() == SoundCategory.WEATHER && packetx.func_186978_a() == SoundEvents.field_187754_de) {
            float yaw = 0.0F;
            double difX = packetx.func_149207_d() - Minecraft.func_71410_x().field_71439_g.field_70165_t;
            double difZ = packetx.func_149210_f() - Minecraft.func_71410_x().field_71439_g.field_70161_v;
            yaw = (float)((double)yaw + MathHelper.func_76138_g(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0D - (double)yaw));
            OnePopMessageUtil.send_client_message("Lightning spawned X:" + Minecraft.func_71410_x().field_71439_g.field_70165_t + " Z:" + Minecraft.func_71410_x().field_71439_g.field_70161_v + " Angle:" + yaw);
         }
      } else if (event.get_packet() instanceof SPacketEffect) {
         SPacketEffect packetxx = (SPacketEffect)event.get_packet();
         if (packetxx.func_149242_d() == 1023 && this.wither.get_value(true)) {
            double theta = Math.atan2((double)packetxx.func_179746_d().func_177952_p() - Minecraft.func_71410_x().field_71439_g.field_70161_v, (double)packetxx.func_179746_d().func_177958_n() - Minecraft.func_71410_x().field_71439_g.field_70165_t);
            ++theta;
            double angle = Math.toDegrees(theta);
            if (angle < 0.0D) {
               angle += 360.0D;
            }

            angle -= 180.0D;
            OnePopMessageUtil.send_client_message("Wither spawned in direction " + angle + " with y position: " + packetxx.func_179746_d().func_177956_o());
         } else if (packetxx.func_149242_d() == 1038 && this.end_portal.get_value(true)) {
            OnePopMessageUtil.send_client_message("End portal spawned at " + packetxx.func_179746_d().toString());
         } else if (packetxx.func_149242_d() == 1028 && this.ender_dragon.get_value(true)) {
            OnePopMessageUtil.send_client_message("Ender dragon died at " + packetxx.func_179746_d().toString());
         }
      }

   }, new Predicate[0]);

   public OnePopGlobalLocation() {
      super(OnePopCategory.ONEPOP_CHAT);
      this.name = "Global Location";
      this.tag = "GlobalLocation";
      this.description = "i may or not have skided this from salhack";
   }
}
