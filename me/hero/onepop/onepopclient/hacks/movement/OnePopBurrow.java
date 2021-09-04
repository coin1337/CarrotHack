package me.hero.onepop.onepopclient.hacks.movement;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopBlockInteractionHelper;
import me.hero.onepop.onepopclient.util.OnePopEntityUtil;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import me.hero.onepop.onepopclient.util.Wrapper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFalling;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class OnePopBurrow extends OnePopHack {
   OnePopSetting ticks = this.create("Ticks", "Ticks", 0, 0, 60);
   OnePopSetting jump = this.create("Jump", "Jump", true);

   public OnePopBurrow() {
      super(OnePopCategory.ONEPOP_MOVEMENT);
      this.name = "Burrow";
      this.tag = "Burrow";
      this.description = "glitch you in a block";
   }

   public void update() {
      OnePopMessageUtil.send_client_message("" + ChatFormatting.GRAY + "> " + ChatFormatting.RESET + "glitching down.");
      if (mc.field_71439_g != null) {
         Vec3d vec3d = OnePopEntityUtil.getInterpolatedPos(mc.field_71439_g, (float)this.ticks.get_value(1));
         BlockPos blockPos = (new BlockPos(vec3d)).func_177977_b();
         BlockPos belowBlockPos = blockPos.func_177977_b();
         if (mc.field_71439_g.field_70122_E && this.jump.get_value(true)) {
            mc.field_71439_g.func_70664_aZ();
         }

         if (Wrapper.getWorld().func_180495_p(blockPos).func_185904_a().func_76222_j()) {
            int newSlot = -1;

            int oldSlot;
            for(oldSlot = 0; oldSlot < 9; ++oldSlot) {
               ItemStack stack = Wrapper.getPlayer().field_71071_by.func_70301_a(oldSlot);
               if (stack != ItemStack.field_190927_a && stack.func_77973_b() instanceof ItemBlock) {
                  Block block = ((ItemBlock)stack.func_77973_b()).func_179223_d();
                  if (!OnePopBlockInteractionHelper.blackList.contains(block) && !(block instanceof BlockContainer) && Block.func_149634_a(stack.func_77973_b()).func_176223_P().func_185913_b() && (!(((ItemBlock)stack.func_77973_b()).func_179223_d() instanceof BlockFalling) || !Wrapper.getWorld().func_180495_p(belowBlockPos).func_185904_a().func_76222_j())) {
                     newSlot = oldSlot;
                     break;
                  }
               }
            }

            if (newSlot != -1) {
               oldSlot = Wrapper.getPlayer().field_71071_by.field_70461_c;
               Wrapper.getPlayer().field_71071_by.field_70461_c = newSlot;
               if (OnePopBlockInteractionHelper.checkForNeighbours(blockPos)) {
                  OnePopBlockInteractionHelper.placeBlockScaffold(blockPos);
                  Wrapper.getPlayer().field_71071_by.field_70461_c = oldSlot;
                  OnePopMessageUtil.send_client_message("" + ChatFormatting.GRAY + "> " + ChatFormatting.RESET + "finished glitching, disabling.");
                  this.set_disable();
               }
            }
         }
      }
   }
}
