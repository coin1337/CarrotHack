package me.hero.onepop.onepopclient.hacks.movement;

import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;

public class OnePopSprint extends OnePopHack {
   OnePopSetting rage = this.create("Rage", "SprintRage", true);

   public OnePopSprint() {
      super(OnePopCategory.ONEPOP_MOVEMENT);
      this.name = "Sprint";
      this.tag = "Sprint";
      this.description = "ZOOOOOOOOM";
   }

   public void update() {
      if (mc.field_71439_g != null) {
         if (this.rage.get_value(true) && (mc.field_71439_g.field_191988_bg != 0.0F || mc.field_71439_g.field_70702_br != 0.0F)) {
            mc.field_71439_g.func_70031_b(true);
         } else {
            mc.field_71439_g.func_70031_b(mc.field_71439_g.field_191988_bg > 0.0F || mc.field_71439_g.field_70702_br > 0.0F);
         }

      }
   }
}
