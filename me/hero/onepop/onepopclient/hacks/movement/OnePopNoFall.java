package me.hero.onepop.onepopclient.hacks.movement;

import java.util.function.Predicate;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.event.events.OnePopEventPacket;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopEntityUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceResult.Type;

public class OnePopNoFall extends OnePopHack {
   OnePopSetting packet = this.create("Packet", "NoFallPacket", true);
   OnePopSetting bucket = this.create("Bucket", "NoFallBucket", true);
   OnePopSetting distance = this.create("Distance", "NoFallDistance", 15, 0, 100);
   private long last = 0L;
   @EventHandler
   public Listener<OnePopEventPacket.SendPacket> sendListener = new Listener((event) -> {
      if (!OnePop.get_module_manager().get_module_with_tag("ElytraFly").is_active()) {
         if (event.get_packet() instanceof CPacketPlayer && this.packet.get_value(true)) {
            ((CPacketPlayer)event.get_packet()).field_149474_g = true;
         }

      }
   }, new Predicate[0]);

   public OnePopNoFall() {
      super(OnePopCategory.ONEPOP_MOVEMENT);
      this.name = "No Fall";
      this.tag = "NoFall";
      this.description = "MrCoffee404";
   }

   public void update() {
      if (!OnePop.get_module_manager().get_module_with_tag("ElytraFly").is_active()) {
         if (this.bucket.get_value(true) && mc.field_71439_g.field_70143_R >= (float)this.distance.get_value(15) && !OnePopEntityUtil.isLiving(mc.field_71439_g) && System.currentTimeMillis() - this.last > 100L) {
            Vec3d posVec = mc.field_71439_g.func_174791_d();
            RayTraceResult result = mc.field_71441_e.func_147447_a(posVec, posVec.func_72441_c(0.0D, -5.329999923706055D, 0.0D), true, true, false);
            if (result != null && result.field_72313_a == Type.BLOCK) {
               EnumHand hand = EnumHand.MAIN_HAND;
               if (mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_151131_as) {
                  hand = EnumHand.OFF_HAND;
               } else if (mc.field_71439_g.func_184614_ca().func_77973_b() != Items.field_151131_as) {
                  for(int i = 0; i < 9; ++i) {
                     if (mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b() == Items.field_151131_as) {
                        mc.field_71439_g.field_71071_by.field_70461_c = i;
                        mc.field_71439_g.field_70125_A = 90.0F;
                        this.last = System.currentTimeMillis();
                        return;
                     }
                  }

                  return;
               }

               mc.field_71439_g.field_70125_A = 90.0F;
               mc.field_71442_b.func_187101_a(mc.field_71439_g, mc.field_71441_e, hand);
               this.last = System.currentTimeMillis();
            }
         }

      }
   }
}
