package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class OnePopEventDamageBlock extends OnePopEventCancellable {
   private BlockPos BlockPos;
   private EnumFacing Direction;

   public OnePopEventDamageBlock(BlockPos posBlock, EnumFacing directionFacing) {
      this.BlockPos = posBlock;
      this.setDirection(directionFacing);
   }

   public BlockPos getPos() {
      return this.BlockPos;
   }

   public EnumFacing getDirection() {
      return this.Direction;
   }

   public void setDirection(EnumFacing direction) {
      this.Direction = direction;
   }
}
