package me.hero.onepop.onepopclient.hacks.combat;

import java.util.Iterator;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopEntityUtil;
import me.hero.onepop.onepopclient.util.OnePopFriendUtil;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import me.hero.onepop.onepopclient.util.OnePopOldBlockInteractHelper;
import me.hero.onepop.onepopclient.util.OnePopPlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction.Action;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class OnePopAutoWeb extends OnePopHack {
   OnePopSetting always_on = this.create("Always On", "AlwaysOn", true);
   OnePopSetting rotate = this.create("Rotate", "AutoWebRotate", true);
   OnePopSetting range = this.create("Enemy Range", "AutoWebRange", 4, 0, 8);
   int new_slot = -1;
   boolean sneak = false;

   public OnePopAutoWeb() {
      super(OnePopCategory.ONEPOP_COMBAT);
      this.name = "Auto Self Web";
      this.tag = "AutoSelfWeb";
      this.description = "places fuckin webs at ur feet";
   }

   public void enable() {
      if (mc.field_71439_g != null) {
         this.new_slot = this.find_in_hotbar();
         if (this.new_slot == -1) {
            OnePopMessageUtil.send_client_error_message("cannot find webs in hotbar");
            this.set_active(false);
         }
      }

   }

   public void disable() {
      if (mc.field_71439_g != null && this.sneak) {
         mc.field_71439_g.field_71174_a.func_147297_a(new CPacketEntityAction(mc.field_71439_g, Action.STOP_SNEAKING));
         this.sneak = false;
      }

   }

   public void update() {
      if (mc.field_71439_g != null) {
         if (this.always_on.get_value(true)) {
            EntityPlayer target = this.find_closest_target();
            if (target == null) {
               return;
            }

            if (mc.field_71439_g.func_70032_d(target) < (float)this.range.get_value(1) && this.is_surround()) {
               int last_slot = mc.field_71439_g.field_71071_by.field_70461_c;
               mc.field_71439_g.field_71071_by.field_70461_c = this.new_slot;
               mc.field_71442_b.func_78765_e();
               this.place_blocks(OnePopPlayerUtil.GetLocalPlayerPosFloored());
               mc.field_71439_g.field_71071_by.field_70461_c = last_slot;
            }
         } else {
            int last_slot = mc.field_71439_g.field_71071_by.field_70461_c;
            mc.field_71439_g.field_71071_by.field_70461_c = this.new_slot;
            mc.field_71442_b.func_78765_e();
            this.place_blocks(OnePopPlayerUtil.GetLocalPlayerPosFloored());
            mc.field_71439_g.field_71071_by.field_70461_c = last_slot;
            this.set_disable();
         }

      }
   }

   public EntityPlayer find_closest_target() {
      if (mc.field_71441_e.field_73010_i.isEmpty()) {
         return null;
      } else {
         EntityPlayer closestTarget = null;
         Iterator var2 = mc.field_71441_e.field_73010_i.iterator();

         while(true) {
            EntityPlayer target;
            do {
               do {
                  do {
                     do {
                        do {
                           if (!var2.hasNext()) {
                              return closestTarget;
                           }

                           target = (EntityPlayer)var2.next();
                        } while(target == mc.field_71439_g);
                     } while(OnePopFriendUtil.isFriend(target.func_70005_c_()));
                  } while(!OnePopEntityUtil.isLiving(target));
               } while(target.func_110143_aJ() <= 0.0F);
            } while(closestTarget != null && mc.field_71439_g.func_70032_d(target) > mc.field_71439_g.func_70032_d(closestTarget));

            closestTarget = target;
         }
      }
   }

   private int find_in_hotbar() {
      for(int i = 0; i < 9; ++i) {
         ItemStack stack = mc.field_71439_g.field_71071_by.func_70301_a(i);
         if (stack.func_77973_b() == Item.func_150899_d(30)) {
            return i;
         }
      }

      return -1;
   }

   private boolean is_surround() {
      BlockPos player_block = OnePopPlayerUtil.GetLocalPlayerPosFloored();
      return mc.field_71441_e.func_180495_p(player_block.func_177974_f()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(player_block.func_177976_e()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(player_block.func_177978_c()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(player_block.func_177968_d()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(player_block).func_177230_c() == Blocks.field_150350_a;
   }

   private void place_blocks(BlockPos pos) {
      if (mc.field_71441_e.func_180495_p(pos).func_185904_a().func_76222_j()) {
         if (OnePopOldBlockInteractHelper.checkForNeighbours(pos)) {
            EnumFacing[] var2 = EnumFacing.values();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
               EnumFacing side = var2[var4];
               BlockPos neighbor = pos.func_177972_a(side);
               EnumFacing side2 = side.func_176734_d();
               if (OnePopOldBlockInteractHelper.canBeClicked(neighbor)) {
                  if (OnePopOldBlockInteractHelper.blackList.contains(mc.field_71441_e.func_180495_p(neighbor).func_177230_c())) {
                     mc.field_71439_g.field_71174_a.func_147297_a(new CPacketEntityAction(mc.field_71439_g, Action.START_SNEAKING));
                     this.sneak = true;
                  }

                  Vec3d hitVec = (new Vec3d(neighbor)).func_72441_c(0.5D, 0.5D, 0.5D).func_178787_e((new Vec3d(side2.func_176730_m())).func_186678_a(0.5D));
                  if (this.rotate.get_value(true)) {
                     OnePopOldBlockInteractHelper.faceVectorPacketInstant(hitVec);
                  }

                  mc.field_71442_b.func_187099_a(mc.field_71439_g, mc.field_71441_e, neighbor, side2, hitVec, EnumHand.MAIN_HAND);
                  mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
                  return;
               }
            }

         }
      }
   }
}
