package me.hero.onepop.onepopclient.hacks.misc;

import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;

public class OnePopOil extends OnePopHack {
   OnePopSetting speed = this.create("Speed", "OilSpeed", 1.0D, 0.0D, 5.0D);
   OnePopSetting kick = this.create("No Kick", "OilNoKick", true);

   public OnePopOil() {
      super(OnePopCategory.ONEPOP_MISC);
      this.name = "Oil";
      this.tag = "Oil";
      this.description = "Cover yourself in oil";
   }

   public void update() {
      if (mc.field_71441_e.func_72896_J()) {
         mc.field_71439_g.func_70016_h(0.0D, 0.0D, 0.0D);
         if (mc.field_71474_y.field_74314_A.func_151470_d()) {
            mc.field_71439_g.field_70181_x = 1.0D;
         }

         if (mc.field_71474_y.field_74311_E.func_151470_d()) {
            mc.field_71439_g.field_70181_x = -1.0D;
         }

         mc.field_71439_g.field_70747_aH = (float)this.speed.get_value(1);
         if (this.kick.get_value(true) && mc.field_71439_g.field_70173_aa % 4 == 0) {
            mc.field_71439_g.field_70181_x = -0.03999999910593033D;
         }
      }

   }
}
