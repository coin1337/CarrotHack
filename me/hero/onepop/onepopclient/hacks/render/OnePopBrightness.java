package me.hero.onepop.onepopclient.hacks.render;

import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class OnePopBrightness extends OnePopHack {
   float old_gamma_value;
   OnePopSetting mode = this.create("Mode", "Mode", "Gamma", this.combobox(new String[]{"Gamma", "Potion"}));

   public OnePopBrightness() {
      super(OnePopCategory.ONEPOP_RENDER);
      this.name = "Brightness";
      this.tag = "Brightness";
      this.description = "popi braitnes";
   }

   public void enable() {
      this.old_gamma_value = mc.field_71474_y.field_74333_Y;
   }

   public void update() {
      if (this.mode.in("Gamma")) {
         mc.field_71474_y.field_74333_Y = 20.0F;
      } else {
         mc.field_71474_y.field_74333_Y = 1.0F;
         mc.field_71439_g.func_70690_d(new PotionEffect(MobEffects.field_76439_r, 5210));
      }

   }

   public void disable() {
      mc.field_71474_y.field_74333_Y = this.old_gamma_value;
      mc.field_71439_g.func_184589_d(MobEffects.field_76439_r);
   }
}
