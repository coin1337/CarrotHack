package me.hero.onepop.onepopclient.hacks.combat;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import me.hero.onepop.onepopclient.event.events.OnePopEventRender;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopBlockUtil;
import me.hero.onepop.onepopclient.util.OnePopFriendUtil;
import me.hero.onepop.onepopclient.util.OnePopOldBlockInteractHelper;
import me.hero.turok.draw.OldRenderHelp;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction.Action;
import net.minecraft.network.play.client.CPacketPlayer.Rotation;
import net.minecraft.util.math.BlockPos;

public class OnePopBedAura extends OnePopHack {
   OnePopSetting delay = this.create("Delay", "BedAuraDelay", 6, 0, 20);
   OnePopSetting range = this.create("Range", "BedAuraRange", 5, 0, 6);
   OnePopSetting hard = this.create("Hard Rotate", "BedAuraRotate", false);
   OnePopSetting swing = this.create("Swing", "BedAuraSwing", "Mainhand", this.combobox(new String[]{"Mainhand", "Offhand", "Both", "None"}));
   OnePopSetting render_mode = this.create("Render Mode", "RenderMode", "Outline", this.combobox(new String[]{"Pretty", "Outline", "Solid"}));
   OnePopSetting height = this.create("Height", "Height", 0.2D, 0.0D, 1.0D);
   OnePopSetting r = this.create("Red", "Red", 255, 0, 255);
   OnePopSetting g = this.create("Green", "Green", 255, 0, 255);
   OnePopSetting b = this.create("Blue", "Blue", 255, 0, 255);
   OnePopSetting a = this.create("Alpha", "Alpha", 180, 0, 255);
   private BlockPos render_pos;
   private int counter;
   private OnePopBedAura.spoof_face spoof_looking;
   private boolean outline;
   private boolean solid;

   public OnePopBedAura() {
      super(OnePopCategory.ONEPOP_COMBAT);
      this.name = "ipk aura";
      this.tag = "BedAura";
      this.description = "ipk,,..,,,";
   }

   protected void enable() {
      this.render_pos = null;
      this.counter = 0;
   }

   protected void disable() {
      this.render_pos = null;
   }

   public void update() {
      if (mc.field_71439_g != null) {
         if (this.counter > this.delay.get_value(1)) {
            this.counter = 0;
            this.place_bed();
            this.break_bed();
            this.refill_bed();
         }

         ++this.counter;
      }
   }

   public void refill_bed() {
      if (!(mc.field_71462_r instanceof GuiContainer) && this.is_space()) {
         for(int i = 9; i < 35; ++i) {
            if (mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b() == Items.field_151104_aV) {
               mc.field_71442_b.func_187098_a(mc.field_71439_g.field_71069_bz.field_75152_c, i, 0, ClickType.QUICK_MOVE, mc.field_71439_g);
               break;
            }
         }
      }

   }

   private boolean is_space() {
      for(int i = 0; i < 9; ++i) {
         if (mc.field_71439_g.field_71069_bz.func_75139_a(i).func_75216_d()) {
            return true;
         }
      }

      return false;
   }

   public void place_bed() {
      if (this.find_bed() != -1) {
         int bed_slot = this.find_bed();
         BlockPos best_pos = null;
         EntityPlayer best_target = null;
         float best_distance = (float)this.range.get_value(1);
         Iterator var5 = ((List)mc.field_71441_e.field_73010_i.stream().filter((entityPlayer) -> {
            return !OnePopFriendUtil.isFriend(entityPlayer.func_70005_c_());
         }).collect(Collectors.toList())).iterator();

         while(var5.hasNext()) {
            EntityPlayer player = (EntityPlayer)var5.next();
            if (player != mc.field_71439_g && !(best_distance < mc.field_71439_g.func_70032_d(player))) {
               boolean face_place = true;
               BlockPos pos = get_pos_floor(player).func_177977_b();
               BlockPos pos2 = this.check_side_block(pos);
               if (pos2 != null) {
                  best_pos = pos2.func_177984_a();
                  best_target = player;
                  best_distance = mc.field_71439_g.func_70032_d(player);
                  face_place = false;
               }

               if (face_place) {
                  BlockPos upos = get_pos_floor(player);
                  BlockPos upos2 = this.check_side_block(upos);
                  if (upos2 != null) {
                     best_pos = upos2.func_177984_a();
                     best_target = player;
                     best_distance = mc.field_71439_g.func_70032_d(player);
                  }
               }
            }
         }

         if (best_target != null) {
            this.render_pos = best_pos;
            if (this.spoof_looking == OnePopBedAura.spoof_face.NORTH) {
               if (this.hard.get_value(true)) {
                  mc.field_71439_g.field_70177_z = 180.0F;
               }

               mc.field_71439_g.field_71174_a.func_147297_a(new Rotation(180.0F, 0.0F, mc.field_71439_g.field_70122_E));
            } else if (this.spoof_looking == OnePopBedAura.spoof_face.SOUTH) {
               if (this.hard.get_value(true)) {
                  mc.field_71439_g.field_70177_z = 0.0F;
               }

               mc.field_71439_g.field_71174_a.func_147297_a(new Rotation(0.0F, 0.0F, mc.field_71439_g.field_70122_E));
            } else if (this.spoof_looking == OnePopBedAura.spoof_face.WEST) {
               if (this.hard.get_value(true)) {
                  mc.field_71439_g.field_70177_z = 90.0F;
               }

               mc.field_71439_g.field_71174_a.func_147297_a(new Rotation(90.0F, 0.0F, mc.field_71439_g.field_70122_E));
            } else if (this.spoof_looking == OnePopBedAura.spoof_face.EAST) {
               if (this.hard.get_value(true)) {
                  mc.field_71439_g.field_70177_z = -90.0F;
               }

               mc.field_71439_g.field_71174_a.func_147297_a(new Rotation(-90.0F, 0.0F, mc.field_71439_g.field_70122_E));
            }

            OnePopBlockUtil.placeBlock(best_pos, bed_slot, false, false, this.swing);
         }
      }
   }

   public void break_bed() {
      BlockPos pos;
      for(Iterator var1 = ((List)OnePopOldBlockInteractHelper.getSphere(get_pos_floor(mc.field_71439_g), (float)this.range.get_value(1), this.range.get_value(1), false, true, 0).stream().filter(OnePopBedAura::is_bed).collect(Collectors.toList())).iterator(); var1.hasNext(); OnePopBlockUtil.openBlock(pos)) {
         pos = (BlockPos)var1.next();
         if (mc.field_71439_g.func_70093_af()) {
            mc.field_71439_g.field_71174_a.func_147297_a(new CPacketEntityAction(mc.field_71439_g, Action.STOP_SNEAKING));
         }
      }

   }

   public int find_bed() {
      for(int i = 0; i < 9; ++i) {
         if (mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b() == Items.field_151104_aV) {
            return i;
         }
      }

      return -1;
   }

   public BlockPos check_side_block(BlockPos pos) {
      if (mc.field_71441_e.func_180495_p(pos.func_177974_f()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(pos.func_177974_f().func_177984_a()).func_177230_c() == Blocks.field_150350_a) {
         this.spoof_looking = OnePopBedAura.spoof_face.WEST;
         return pos.func_177974_f();
      } else if (mc.field_71441_e.func_180495_p(pos.func_177978_c()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(pos.func_177978_c().func_177984_a()).func_177230_c() == Blocks.field_150350_a) {
         this.spoof_looking = OnePopBedAura.spoof_face.SOUTH;
         return pos.func_177978_c();
      } else if (mc.field_71441_e.func_180495_p(pos.func_177976_e()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(pos.func_177976_e().func_177984_a()).func_177230_c() == Blocks.field_150350_a) {
         this.spoof_looking = OnePopBedAura.spoof_face.EAST;
         return pos.func_177976_e();
      } else if (mc.field_71441_e.func_180495_p(pos.func_177968_d()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(pos.func_177968_d().func_177984_a()).func_177230_c() == Blocks.field_150350_a) {
         this.spoof_looking = OnePopBedAura.spoof_face.NORTH;
         return pos.func_177968_d();
      } else {
         return null;
      }
   }

   public static BlockPos get_pos_floor(EntityPlayer player) {
      return new BlockPos(Math.floor(player.field_70165_t), Math.floor(player.field_70163_u), Math.floor(player.field_70161_v));
   }

   public static boolean is_bed(BlockPos pos) {
      Block block = mc.field_71441_e.func_180495_p(pos).func_177230_c();
      return block == Blocks.field_150324_C;
   }

   public void render(OnePopEventRender event) {
      if (this.render_mode.in("Pretty")) {
         this.outline = true;
         this.solid = true;
      }

      if (this.render_mode.in("Solid")) {
         this.outline = false;
         this.solid = true;
      }

      if (this.render_mode.in("Outline")) {
         this.outline = true;
         this.solid = false;
      }

      this.render_block(this.render_pos);
   }

   public void render_block(BlockPos pos) {
      float h = (float)this.height.get_value(1.0D);
      if (this.render_pos != null) {
         if (this.solid) {
            OldRenderHelp.prepare("quads");
            OldRenderHelp.draw_cube(OldRenderHelp.get_buffer_build(), (float)this.render_pos.func_177958_n(), (float)this.render_pos.func_177956_o(), (float)this.render_pos.func_177952_p(), 1.0F, h, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
            OldRenderHelp.release();
         }

         if (this.outline) {
            OldRenderHelp.prepare("lines");
            OldRenderHelp.draw_cube_line(OldRenderHelp.get_buffer_build(), (float)this.render_pos.func_177958_n(), (float)this.render_pos.func_177956_o(), (float)this.render_pos.func_177952_p(), 1.0F, h, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
            OldRenderHelp.release();
         }

      }
   }

   static enum spoof_face {
      EAST,
      WEST,
      NORTH,
      SOUTH;
   }
}
