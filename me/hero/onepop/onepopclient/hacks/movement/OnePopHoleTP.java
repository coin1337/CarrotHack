package me.hero.onepop.onepopclient.hacks.movement;

import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;

public class OnePopHoleTP extends OnePopHack {
   public OnePopHoleTP() {
      super(OnePopCategory.ONEPOP_MOVEMENT);
      this.name = "Reverse Step";
      this.tag = "ReverseStep";
      this.description = "cai rapido :O";
   }

   public void update() {
      if (mc.field_71439_g.field_70122_E && !mc.field_71439_g.func_70617_f_() && !mc.field_71439_g.func_70090_H() && !mc.field_71439_g.func_180799_ab() && !mc.field_71439_g.field_71158_b.field_78901_c && !mc.field_71439_g.field_70145_X) {
         if (mc.field_71439_g.field_191988_bg != 0.0F || mc.field_71439_g.field_70702_br != 0.0F) {
            mc.field_71439_g.field_70181_x = -1.0D;
         }
      }
   }
}
