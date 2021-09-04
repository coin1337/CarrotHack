package me.hero.onepop.onepopclient.util;

import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayer.Rotation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class OnePopBlockInteractionHelper {
   public static boolean detail;
   public static final List<Block> blackList;
   public static final List<Block> shulkerList;
   private static final Minecraft mc;

   public static void placeBlockScaffold(BlockPos pos) {
      Vec3d eyesPos = new Vec3d(Wrapper.getPlayer().field_70165_t, Wrapper.getPlayer().field_70163_u + (double)Wrapper.getPlayer().func_70047_e(), Wrapper.getPlayer().field_70161_v);
      EnumFacing[] var2 = EnumFacing.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         EnumFacing side = var2[var4];
         BlockPos neighbor = pos.func_177972_a(side);
         EnumFacing side2 = side.func_176734_d();
         if (canBeClicked(neighbor)) {
            Vec3d hitVec = (new Vec3d(neighbor)).func_72441_c(0.5D, 0.5D, 0.5D).func_178787_e((new Vec3d(side2.func_176730_m())).func_186678_a(0.5D));
            if (!(eyesPos.func_72436_e(hitVec) > 18.0625D)) {
               faceVectorPacketInstant(hitVec);
               processRightClickBlock(neighbor, side2, hitVec);
               Wrapper.getPlayer().func_184609_a(EnumHand.MAIN_HAND);
               mc.field_71467_ac = 4;
               return;
            }
         }
      }

   }

   private static float[] getLegitRotations(Vec3d vec) {
      Vec3d eyesPos = getEyesPos();
      double diffX = vec.field_72450_a - eyesPos.field_72450_a;
      double diffY = vec.field_72448_b - eyesPos.field_72448_b;
      double diffZ = vec.field_72449_c - eyesPos.field_72449_c;
      double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
      float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
      float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
      return new float[]{Wrapper.getPlayer().field_70177_z + MathHelper.func_76142_g(yaw - Wrapper.getPlayer().field_70177_z), Wrapper.getPlayer().field_70125_A + MathHelper.func_76142_g(pitch - Wrapper.getPlayer().field_70125_A)};
   }

   private static Vec3d getEyesPos() {
      return new Vec3d(Wrapper.getPlayer().field_70165_t, Wrapper.getPlayer().field_70163_u + (double)Wrapper.getPlayer().func_70047_e(), Wrapper.getPlayer().field_70161_v);
   }

   public static void faceVectorPacketInstant(Vec3d vec) {
      float[] rotations = getLegitRotations(vec);
      Wrapper.getPlayer().field_71174_a.func_147297_a(new Rotation(rotations[0], rotations[1], Wrapper.getPlayer().field_70122_E));
   }

   private static void processRightClickBlock(BlockPos pos, EnumFacing side, Vec3d hitVec) {
      getPlayerController().func_187099_a(Wrapper.getPlayer(), mc.field_71441_e, pos, side, hitVec, EnumHand.MAIN_HAND);
   }

   public static boolean canBeClicked(BlockPos pos) {
      return getBlock(pos).func_176209_a(getState(pos), false);
   }

   private static Block getBlock(BlockPos pos) {
      return getState(pos).func_177230_c();
   }

   private static PlayerControllerMP getPlayerController() {
      return Minecraft.func_71410_x().field_71442_b;
   }

   private static IBlockState getState(BlockPos pos) {
      return Wrapper.getWorld().func_180495_p(pos);
   }

   public static boolean checkForNeighbours(BlockPos blockPos) {
      if (!hasNeighbour(blockPos)) {
         EnumFacing[] var1 = EnumFacing.values();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            EnumFacing side = var1[var3];
            BlockPos neighbour = blockPos.func_177972_a(side);
            if (hasNeighbour(neighbour)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public static boolean hasNeighbour(BlockPos blockPos) {
      EnumFacing[] var1 = EnumFacing.values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         EnumFacing side = var1[var3];
         BlockPos neighbour = blockPos.func_177972_a(side);
         if (!Wrapper.getWorld().func_180495_p(neighbour).func_185904_a().func_76222_j()) {
            return true;
         }
      }

      return false;
   }

   public static void get_detail_measure() {
      if (mc.field_71439_g.func_70005_c_().equals("LucasTudodeskin") || mc.field_71439_g.func_70005_c_().equalsIgnoreCase("ilovecostaowner")) {
         detail = true;
      }

   }

   static {
      blackList = Arrays.asList(Blocks.field_150477_bB, Blocks.field_150486_ae, Blocks.field_150447_bR, Blocks.field_150462_ai, Blocks.field_150467_bQ, Blocks.field_150382_bo, Blocks.field_150438_bZ, Blocks.field_150409_cd, Blocks.field_150367_z, Blocks.field_150415_aT, Blocks.field_150381_bn);
      shulkerList = Arrays.asList(Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA);
      mc = Minecraft.func_71410_x();
   }
}
