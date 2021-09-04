package me.hero.onepop.onepopclient.hacks.combat;

import java.util.ArrayList;
import java.util.Iterator;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopBlockUtil;
import me.hero.onepop.onepopclient.util.OnePopOldBlockInteractHelper;
import me.hero.onepop.onepopclient.util.OnePopPlayerUtil;
import me.hero.onepop.onepopclient.util.OnePopOldBlockInteractHelper.ValidResult;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockObsidian;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class OnePopSocks extends OnePopHack {
   OnePopSetting rotate = this.create("Rotate", "SocksRotate", false);
   OnePopSetting swing = this.create("Swing", "SocksSwing", "Mainhand", this.combobox(new String[]{"Mainhand", "Offhand", "Both", "None"}));

   public OnePopSocks() {
      super(OnePopCategory.ONEPOP_COMBAT);
      this.name = "Anti City";
      this.tag = "AntiCity";
      this.description = "Protects your feet";
   }

   protected void enable() {
      if (this.find_in_hotbar() == -1) {
         this.set_disable();
      }
   }

   public void update() {
      int slot = this.find_in_hotbar();
      if (slot != -1) {
         BlockPos center_pos = OnePopPlayerUtil.GetLocalPlayerPosFloored();
         ArrayList<BlockPos> blocks_to_fill = new ArrayList();
         switch(OnePopPlayerUtil.GetFacing()) {
         case East:
            blocks_to_fill.add(center_pos.func_177974_f().func_177974_f());
            blocks_to_fill.add(center_pos.func_177974_f().func_177974_f().func_177984_a());
            blocks_to_fill.add(center_pos.func_177974_f().func_177974_f().func_177974_f());
            blocks_to_fill.add(center_pos.func_177974_f().func_177974_f().func_177974_f().func_177984_a());
            break;
         case North:
            blocks_to_fill.add(center_pos.func_177978_c().func_177978_c());
            blocks_to_fill.add(center_pos.func_177978_c().func_177978_c().func_177984_a());
            blocks_to_fill.add(center_pos.func_177978_c().func_177978_c().func_177978_c());
            blocks_to_fill.add(center_pos.func_177978_c().func_177978_c().func_177978_c().func_177984_a());
            break;
         case South:
            blocks_to_fill.add(center_pos.func_177968_d().func_177968_d());
            blocks_to_fill.add(center_pos.func_177968_d().func_177968_d().func_177984_a());
            blocks_to_fill.add(center_pos.func_177968_d().func_177968_d().func_177968_d());
            blocks_to_fill.add(center_pos.func_177968_d().func_177968_d().func_177968_d().func_177984_a());
            break;
         case West:
            blocks_to_fill.add(center_pos.func_177976_e().func_177976_e());
            blocks_to_fill.add(center_pos.func_177976_e().func_177976_e().func_177984_a());
            blocks_to_fill.add(center_pos.func_177976_e().func_177976_e().func_177976_e());
            blocks_to_fill.add(center_pos.func_177976_e().func_177976_e().func_177976_e().func_177984_a());
         }

         BlockPos pos_to_fill = null;
         Iterator var5 = blocks_to_fill.iterator();

         while(var5.hasNext()) {
            BlockPos pos = (BlockPos)var5.next();
            ValidResult result = OnePopOldBlockInteractHelper.valid(pos);
            if (result == ValidResult.Ok && pos != null) {
               pos_to_fill = pos;
               break;
            }
         }

         OnePopBlockUtil.placeBlock(pos_to_fill, this.find_in_hotbar(), this.rotate.get_value(true), this.rotate.get_value(true), this.swing);
      }
   }

   private int find_in_hotbar() {
      for(int i = 0; i < 9; ++i) {
         ItemStack stack = mc.field_71439_g.field_71071_by.func_70301_a(i);
         if (stack != ItemStack.field_190927_a && stack.func_77973_b() instanceof ItemBlock) {
            Block block = ((ItemBlock)stack.func_77973_b()).func_179223_d();
            if (block instanceof BlockEnderChest) {
               return i;
            }

            if (block instanceof BlockObsidian) {
               return i;
            }
         }
      }

      return -1;
   }
}
