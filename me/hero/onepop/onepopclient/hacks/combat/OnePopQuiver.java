package me.hero.onepop.onepopclient.hacks.combat;

import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketPlayer.Rotation;
import net.minecraft.network.play.client.CPacketPlayerDigging.Action;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class OnePopQuiver extends OnePopHack {
   OnePopSetting disable = this.create("Toggle", "Toggle", true);
   private int random_variation;

   public OnePopQuiver() {
      super(OnePopCategory.ONEPOP_COMBAT);
      this.name = "Quiver";
      this.tag = "Quiver";
      this.description = "shoots good arrows at you";
   }

   public void update() {
      if (mc.field_71439_g.field_71071_by.func_70448_g().func_77973_b() == Items.field_151031_f) {
         mc.field_71439_g.field_71174_a.func_147297_a(new Rotation(0.0F, -90.0F, true));
         if (mc.field_71439_g.func_184612_cw() >= this.getBowCharge()) {
            mc.field_71439_g.field_71174_a.func_147297_a(new CPacketPlayerDigging(Action.RELEASE_USE_ITEM, BlockPos.field_177992_a, mc.field_71439_g.func_174811_aO()));
            mc.field_71439_g.field_71174_a.func_147297_a(new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
         }
      }

      if (this.disable.get_value(true)) {
         this.set_disable();
      }

   }

   private int getBowCharge() {
      if (this.random_variation == 0) {
         this.random_variation = 2;
      }

      return 3 + this.random_variation;
   }
}
