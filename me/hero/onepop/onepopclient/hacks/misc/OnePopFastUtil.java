package me.hero.onepop.onepopclient.hacks.misc;

import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketPlayerDigging.Action;
import net.minecraft.util.math.BlockPos;

public class OnePopFastUtil extends OnePopHack {
   OnePopSetting everything = this.create("Everything", "FastEverything", false);
   OnePopSetting fast_place = this.create("Place", "FastPlace", false);
   OnePopSetting fast_break = this.create("Break", "FastBreak", false);
   OnePopSetting crystal = this.create("Crystal", "FastCrystal", false);
   OnePopSetting exp = this.create("EXP", "FastExp", true);
   OnePopSetting bow = this.create("Bow", "OnePopFastBow", true);

   public OnePopFastUtil() {
      super(OnePopCategory.ONEPOP_MISC);
      this.name = "Fast Util";
      this.tag = "FastUtil";
      this.description = "use things faster";
   }

   public void update() {
      Item main = mc.field_71439_g.func_184614_ca().func_77973_b();
      Item off = mc.field_71439_g.func_184592_cb().func_77973_b();
      boolean main_exp = main instanceof ItemExpBottle;
      boolean off_exp = off instanceof ItemExpBottle;
      boolean main_cry = main instanceof ItemEndCrystal;
      boolean off_cry = off instanceof ItemEndCrystal;
      boolean main_bow = main instanceof ItemBow;
      boolean off_bow = off instanceof ItemBow;
      if (this.everything.get_value(true)) {
         mc.field_71467_ac = 0;
      }

      if (main_exp | off_exp && this.exp.get_value(true)) {
         mc.field_71467_ac = 0;
      }

      if (main_bow | off_bow && this.bow.get_value(true) && mc.field_71439_g.func_184587_cr() && mc.field_71439_g.func_184612_cw() >= 3) {
         mc.field_71439_g.field_71174_a.func_147297_a(new CPacketPlayerDigging(Action.RELEASE_USE_ITEM, BlockPos.field_177992_a, mc.field_71439_g.func_174811_aO()));
         mc.field_71439_g.field_71174_a.func_147297_a(new CPacketPlayerTryUseItem(mc.field_71439_g.func_184600_cs()));
         mc.field_71439_g.func_184597_cx();
      }

      if (main_cry | off_cry && this.crystal.get_value(true)) {
         mc.field_71467_ac = 0;
      }

      if (!(main_exp | off_exp | main_cry | off_cry) && this.fast_place.get_value(true)) {
         mc.field_71467_ac = 0;
      }

      if (this.fast_break.get_value(true)) {
         mc.field_71442_b.field_78781_i = 0;
      }

   }
}
