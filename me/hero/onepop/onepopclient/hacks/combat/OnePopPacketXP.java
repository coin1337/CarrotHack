package me.hero.onepop.onepopclient.hacks.combat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketPlayer.Rotation;
import net.minecraft.network.play.client.CPacketPlayerDigging.Action;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class OnePopPacketXP extends OnePopHack {
   OnePopSetting armor_percent = this.create("Armor %", "PXPArmorPercent", 80, 0, 100);
   OnePopSetting auto_switch = this.create("Auto Switch", "PXPAutoSwitch", false);
   OnePopSetting disable = this.create("Disable When Full", "PXPDisable", false);

   public OnePopPacketXP() {
      super(OnePopCategory.ONEPOP_COMBAT);
      this.name = "Packet XP";
      this.tag = "PacketXP";
      this.description = "cool you don't waste xp :0";
   }

   public void update() {
      if (!mc.field_71439_g.func_70051_ag() && mc.field_71439_g.field_70122_E && mc.field_71439_g.field_71071_by.func_70448_g().func_77973_b() == Items.field_151062_by) {
         if (!this.is_damaged()) {
            if (this.disable.get_value(true)) {
               this.set_disable();
            }

            return;
         }

         mc.field_71439_g.field_71174_a.func_147297_a(new Rotation(0.0F, 90.0F, true));
         mc.field_71439_g.field_71174_a.func_147297_a(new CPacketPlayerDigging(Action.RELEASE_USE_ITEM, BlockPos.field_177992_a, mc.field_71439_g.func_174811_aO()));
         mc.field_71439_g.field_71174_a.func_147297_a(new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
      }

      if (mc.field_71439_g.func_184592_cb().func_77973_b() != Items.field_151062_by && mc.field_71439_g.func_184614_ca().func_77973_b() != Items.field_151062_by && this.auto_switch.get_value(true)) {
         if (this.find_xp_hotbar() != -1) {
            if (this.is_damaged()) {
               mc.field_71439_g.field_71071_by.field_70461_c = this.find_xp_hotbar();
            }

         }
      }
   }

   private boolean is_damaged() {
      Iterator var1 = this.get_armor().entrySet().iterator();

      while(var1.hasNext()) {
         Entry<Integer, ItemStack> armor_slot = (Entry)var1.next();
         if (!((ItemStack)armor_slot.getValue()).func_190926_b()) {
            ItemStack stack = (ItemStack)armor_slot.getValue();
            double max_dam = (double)stack.func_77958_k();
            double dam_left = (double)(stack.func_77958_k() - stack.func_77952_i());
            double percent = dam_left / max_dam * 100.0D;
            if (percent < (double)this.armor_percent.get_value(1)) {
               return true;
            }
         }
      }

      return false;
   }

   private int find_xp_hotbar() {
      for(int i = 0; i < 9; ++i) {
         if (mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b() == Items.field_151062_by) {
            return i;
         }
      }

      return -1;
   }

   private Map<Integer, ItemStack> get_armor() {
      return this.get_inv_slots(5);
   }

   private Map<Integer, ItemStack> get_inv_slots(int current) {
      HashMap full_inv_slots;
      for(full_inv_slots = new HashMap(); current <= 8; ++current) {
         full_inv_slots.put(current, mc.field_71439_g.field_71069_bz.func_75138_a().get(current));
      }

      return full_inv_slots;
   }
}
