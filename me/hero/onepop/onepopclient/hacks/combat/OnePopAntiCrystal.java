package me.hero.onepop.onepopclient.hacks.combat;

import java.util.ArrayList;
import java.util.Iterator;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopBlockUtil;
import me.hero.onepop.onepopclient.util.OnePopInventoryUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;

public class OnePopAntiCrystal extends OnePopHack {
   OnePopSetting delay = this.create("Delay", "Delay", 2, 1, 20);
   OnePopSetting range = this.create("Range", "Range", 4, 0, 10);
   OnePopSetting rotate = this.create("Rotate", "Rotate", true);
   OnePopSetting switch_mode = this.create("Switch Mode", "SwitchMode", "Normal", this.combobox(new String[]{"Ghost", "Normal", "None"}));
   OnePopSetting disable = this.create("Toggle", "Toggle", false);
   int index = 0;

   public OnePopAntiCrystal() {
      super(OnePopCategory.ONEPOP_COMBAT);
      this.name = "Anti Crystal";
      this.tag = "AntiCrystal";
      this.description = "max please don't get mad at me";
   }

   public void update() {
      if (this.index > 2000) {
         this.index = 0;
      }

      Iterator var1 = this.getNonPlaced().iterator();

      while(var1.hasNext()) {
         Object o = var1.next();
         EntityEnderCrystal cry = (EntityEnderCrystal)o;
         if (this.index % this.delay.get_value(1) == 0) {
            if (this.switch_mode.in("Normal")) {
               OnePopInventoryUtil.switchToSlot(Item.func_150898_a(Blocks.field_150452_aw));
            } else if (this.switch_mode.in("Ghost")) {
               OnePopInventoryUtil.switchToSlotGhost(Item.func_150898_a(Blocks.field_150452_aw));
            } else {
               this.switch_mode.in("None");
            }

            if (mc.field_71439_g.func_184586_b(EnumHand.MAIN_HAND).func_77973_b() == Item.func_150898_a(Blocks.field_150452_aw)) {
               OnePopBlockUtil.betterPlaceBlock(cry.func_180425_c(), this.rotate.get_value(true));
               if (this.disable.get_value(true)) {
                  this.set_disable();
               }

               return;
            }
         }
      }

   }

   public ArrayList getCrystals() {
      ArrayList crystals = new ArrayList();
      Iterator var2 = mc.field_71441_e.func_72910_y().iterator();

      while(var2.hasNext()) {
         Entity ent = (Entity)var2.next();
         if (ent instanceof EntityEnderCrystal) {
            crystals.add(ent);
         }
      }

      return crystals;
   }

   public ArrayList getInRange() {
      ArrayList inRange = new ArrayList();
      Iterator var2 = this.getCrystals().iterator();

      while(var2.hasNext()) {
         Object o = var2.next();
         EntityEnderCrystal crystal = (EntityEnderCrystal)o;
         if (mc.field_71439_g.func_70032_d(crystal) <= (float)Integer.valueOf(this.range.get_value(1))) {
            inRange.add(crystal);
         }
      }

      return inRange;
   }

   public ArrayList getNonPlaced() {
      ArrayList returnOutput = new ArrayList();
      Iterator var2 = this.getInRange().iterator();

      while(var2.hasNext()) {
         Object o = var2.next();
         EntityEnderCrystal crystal = (EntityEnderCrystal)o;
         if (mc.field_71441_e.func_180495_p(crystal.func_180425_c()).func_177230_c() != Blocks.field_150452_aw) {
            returnOutput.add(crystal);
         }
      }

      return returnOutput;
   }
}
