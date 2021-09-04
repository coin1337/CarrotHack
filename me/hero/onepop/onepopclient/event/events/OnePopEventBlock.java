package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class OnePopEventBlock extends OnePopEventCancellable {
   public BlockPos pos;
   public EnumFacing facing;
   private int stage;

   public OnePopEventBlock(int stage, BlockPos pos, EnumFacing facing) {
      this.pos = pos;
      this.facing = facing;
      this.stage = stage;
   }

   public void set_stage(int stage) {
      this.stage = stage;
   }

   public int get_stage() {
      return this.stage;
   }
}
