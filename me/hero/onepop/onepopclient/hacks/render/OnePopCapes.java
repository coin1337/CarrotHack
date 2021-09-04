package me.hero.onepop.onepopclient.hacks.render;

import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;

public class OnePopCapes extends OnePopHack {
   OnePopSetting cape = this.create("Cape", "CapeCape", "Trans", this.combobox(new String[]{"Trans", "Astolfo"}));

   public OnePopCapes() {
      super(OnePopCategory.ONEPOP_RENDER);
      this.name = "Capes";
      this.tag = "Capes";
      this.description = "see epic capes behind epic dudes";
   }
}
