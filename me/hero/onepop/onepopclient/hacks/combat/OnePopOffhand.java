package me.hero.onepop.onepopclient.hacks.combat;

import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopPlayerUtil;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.BlockPos;

public class OnePopOffhand extends OnePopHack {
   OnePopSetting switch_mode = this.create("Offhand", "OffhandOffhand", "Totem", this.combobox(new String[]{"Totem", "Crystal", "Gapple", "Shield", "Bed", "Sword", "Potion"}));
   OnePopSetting totem_switch = this.create("Totem HP", "OffhandTotemHP", 16, 0, 36);
   OnePopSetting gapple_in_hole = this.create("Gapple In Hole", "OffhandGapple", false);
   OnePopSetting gapple_hole_hp = this.create("Gapple Hole HP", "OffhandGappleHP", 8, 0, 36);
   OnePopSetting sword_gap = this.create("Sword Gap", "OffhandSwordGap", false);
   OnePopSetting gap_crystal = this.create("Gap Crystal", "OffhandGapCrystal", false);
   OnePopSetting crystal_gap = this.create("Crystal Gap", "OffhandCrystalGap", false);
   OnePopSetting delay = this.create("Delay", "OffhandDelay", false);
   private boolean switching = false;
   private int last_slot;

   public OnePopOffhand() {
      super(OnePopCategory.ONEPOP_COMBAT);
      this.name = "Offhand";
      this.tag = "Offhand";
      this.description = "Switches shit to ur offhand";
   }

   public void update() {
      if (mc.field_71462_r == null || mc.field_71462_r instanceof GuiInventory) {
         if (this.switching) {
            this.swap_items(this.last_slot, 2);
            return;
         }

         float hp = mc.field_71439_g.func_110143_aJ() + mc.field_71439_g.func_110139_bj();
         if (!(hp > (float)this.totem_switch.get_value(1))) {
            this.swap_items(this.get_item_slot(Items.field_190929_cY), this.delay.get_value(true) ? 1 : 0);
            return;
         }

         if (this.switch_mode.in("Crystal")) {
            this.swap_items(this.get_item_slot(Items.field_185158_cP), 0);
            return;
         }

         if (this.gapple_in_hole.get_value(true) && hp > (float)this.gapple_hole_hp.get_value(1) && this.is_in_hole()) {
            this.swap_items(this.get_item_slot(Items.field_151153_ao), this.delay.get_value(true) ? 1 : 0);
            return;
         }

         if (this.sword_gap.get_value(true) && hp > (float)this.totem_switch.get_value(1) && mc.field_71439_g.func_184614_ca().func_77973_b() instanceof ItemSword) {
            this.swap_items(this.get_item_slot(Items.field_151153_ao), this.delay.get_value(true) ? 1 : 0);
            return;
         }

         if (this.crystal_gap.get_value(true) && hp > (float)this.totem_switch.get_value(1) && mc.field_71439_g.func_184614_ca().func_77973_b() instanceof ItemAppleGold) {
            this.swap_items(this.get_item_slot(Items.field_185158_cP), this.delay.get_value(true) ? 1 : 0);
            return;
         }

         if (this.gap_crystal.get_value(true) && hp > (float)this.totem_switch.get_value(1) && mc.field_71439_g.func_184614_ca().func_77973_b() instanceof ItemEndCrystal) {
            this.swap_items(this.get_item_slot(Items.field_151153_ao), this.delay.get_value(true) ? 1 : 0);
            return;
         }

         if (this.switch_mode.in("Totem")) {
            this.swap_items(this.get_item_slot(Items.field_190929_cY), this.delay.get_value(true) ? 1 : 0);
            return;
         }

         if (this.switch_mode.in("Gapple")) {
            this.swap_items(this.get_item_slot(Items.field_151153_ao), this.delay.get_value(true) ? 1 : 0);
            return;
         }

         if (this.switch_mode.in("Shield")) {
            this.swap_items(this.get_item_slot(Items.field_185159_cQ), this.delay.get_value(true) ? 1 : 0);
            return;
         }

         if (this.switch_mode.in("Bed")) {
            this.swap_items(this.get_item_slot(Items.field_151104_aV), this.delay.get_value(true) ? 1 : 0);
            return;
         }

         if (this.switch_mode.in("Sword")) {
            this.swap_items(this.get_item_slot(Items.field_151048_u), this.delay.get_value(true) ? 1 : 0);
            return;
         }

         if (this.switch_mode.in("Potion")) {
            this.swap_items(this.get_item_slot(Items.field_151068_bn), this.delay.get_value(true) ? 1 : 0);
            return;
         }

         if (mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_190931_a) {
            this.swap_items(this.get_item_slot(Items.field_190929_cY), this.delay.get_value(true) ? 1 : 0);
         }
      }

   }

   public void swap_items(int slot, int step) {
      if (slot != -1) {
         if (step == 0) {
            mc.field_71442_b.func_187098_a(0, slot, 0, ClickType.PICKUP, mc.field_71439_g);
            mc.field_71442_b.func_187098_a(0, 45, 0, ClickType.PICKUP, mc.field_71439_g);
            mc.field_71442_b.func_187098_a(0, slot, 0, ClickType.PICKUP, mc.field_71439_g);
         }

         if (step == 1) {
            mc.field_71442_b.func_187098_a(0, slot, 0, ClickType.PICKUP, mc.field_71439_g);
            this.switching = true;
            this.last_slot = slot;
         }

         if (step == 2) {
            mc.field_71442_b.func_187098_a(0, 45, 0, ClickType.PICKUP, mc.field_71439_g);
            mc.field_71442_b.func_187098_a(0, slot, 0, ClickType.PICKUP, mc.field_71439_g);
            this.switching = false;
         }

         mc.field_71442_b.func_78765_e();
      }
   }

   private boolean is_in_hole() {
      BlockPos player_block = OnePopPlayerUtil.GetLocalPlayerPosFloored();
      return mc.field_71441_e.func_180495_p(player_block.func_177974_f()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(player_block.func_177976_e()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(player_block.func_177978_c()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(player_block.func_177968_d()).func_177230_c() != Blocks.field_150350_a;
   }

   private int get_item_slot(Item input) {
      if (input == mc.field_71439_g.func_184592_cb().func_77973_b()) {
         return -1;
      } else {
         for(int i = 36; i >= 0; --i) {
            Item item = mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b();
            if (item == input) {
               if (i < 9) {
                  if (input == Items.field_151153_ao) {
                     return -1;
                  }

                  i += 36;
               }

               return i;
            }
         }

         return -1;
      }
   }
}
