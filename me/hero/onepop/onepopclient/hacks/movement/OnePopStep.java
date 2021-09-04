package me.hero.onepop.onepopclient.hacks.movement;

import java.util.Iterator;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import net.minecraft.network.play.client.CPacketPlayer.Position;
import net.minecraft.util.math.AxisAlignedBB;

public class OnePopStep extends OnePopHack {
   OnePopSetting mode = this.create("Mode", "StepMode", "Normal", this.combobox(new String[]{"Normal", "Reverse"}));

   public OnePopStep() {
      super(OnePopCategory.ONEPOP_MOVEMENT);
      this.name = "Step";
      this.tag = "Step";
      this.description = "Move up / down block big";
   }

   public void update() {
      if (mc.field_71439_g.field_70123_F || !this.mode.in("Normal")) {
         if (mc.field_71439_g.field_70122_E && !mc.field_71439_g.func_70617_f_() && !mc.field_71439_g.func_70090_H() && !mc.field_71439_g.func_180799_ab() && !mc.field_71439_g.field_71158_b.field_78901_c && !mc.field_71439_g.field_70145_X) {
            if (mc.field_71439_g.field_191988_bg != 0.0F || mc.field_71439_g.field_70702_br != 0.0F) {
               double n = this.get_n_normal();
               if (this.mode.in("Normal")) {
                  if (n < 0.0D || n > 2.0D) {
                     return;
                  }

                  if (n == 2.0D) {
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.42D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.78D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.63D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.51D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.9D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.21D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.45D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.43D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.func_70107_b(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 2.0D, mc.field_71439_g.field_70161_v);
                  }

                  if (n == 1.5D) {
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.41999998688698D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.7531999805212D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.00133597911214D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.16610926093821D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.24918707874468D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.1707870772188D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.func_70107_b(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.0D, mc.field_71439_g.field_70161_v);
                  }

                  if (n == 1.0D) {
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.41999998688698D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.7531999805212D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
                     mc.field_71439_g.func_70107_b(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.0D, mc.field_71439_g.field_70161_v);
                  }
               }

               if (this.mode.in("Reverse")) {
                  mc.field_71439_g.field_70181_x = -1.0D;
               }

            }
         }
      }
   }

   public double get_n_normal() {
      mc.field_71439_g.field_70138_W = 0.5F;
      double max_y = -1.0D;
      AxisAlignedBB grow = mc.field_71439_g.func_174813_aQ().func_72317_d(0.0D, 0.05D, 0.0D).func_186662_g(0.05D);
      if (!mc.field_71441_e.func_184144_a(mc.field_71439_g, grow.func_72317_d(0.0D, 2.0D, 0.0D)).isEmpty()) {
         return 100.0D;
      } else {
         Iterator var4 = mc.field_71441_e.func_184144_a(mc.field_71439_g, grow).iterator();

         while(var4.hasNext()) {
            AxisAlignedBB aabb = (AxisAlignedBB)var4.next();
            if (aabb.field_72337_e > max_y) {
               max_y = aabb.field_72337_e;
            }
         }

         return max_y - mc.field_71439_g.field_70163_u;
      }
   }
}
