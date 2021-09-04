package me.hero.onepop.onepopclient.hacks.movement;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.function.Predicate;
import me.hero.onepop.onepopclient.event.events.OnePopEventPacket;
import me.hero.onepop.onepopclient.event.events.OnePopEventPlayerTravel;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopMathUtil;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import me.hero.onepop.onepopclient.util.OnePopTimer;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketEntityAction.Action;
import net.minecraft.network.play.client.CPacketPlayer.Position;
import net.minecraft.network.play.client.CPacketPlayer.PositionRotation;
import net.minecraft.network.play.client.CPacketPlayer.Rotation;

public class OnePopElytraFly extends OnePopHack {
   OnePopSetting mode = this.create("Mode", "Mode", "Normal", this.combobox(new String[]{"Normal", "Tarzan", "Superior", "Packet", "Control"}));
   OnePopSetting speed = this.create("Speed", "Speed", 1.8200000524520874D, 0.0D, 10.0D);
   OnePopSetting glide_speed = this.create("Glide Speed", "GlideSpeed", 1.0D, 0.0D, 10.0D);
   OnePopSetting up_speed = this.create("Up Speed", "UpSpeed", 2.0D, 0.0D, 10.0D);
   OnePopSetting down_speed = this.create("Down Speed", "DownSpeed", 1.8200000524520874D, 0.0D, 10.0D);
   OnePopSetting accelerate = this.create("Accelerate", "Accelerate", true);
   OnePopSetting v_acceleration_timer = this.create("Timer", "AccelerationTimer", 1000, 0, 10000);
   OnePopSetting rotation_pitch = this.create("Rotation Pitch", "RotationPitch", 0.0D, -90.0D, 90.0D);
   OnePopSetting cancel_in_water = this.create("Cancel In Water", "CancelWater", true);
   OnePopSetting cancel_at_height = this.create("Cancel At Height", "CancelHeight", 5, 0, 10);
   OnePopSetting instant_fly = this.create("Instant Fly", "InstaFly", true);
   OnePopSetting equip_elytra = this.create("Equip Elytra", "EquipElytra", false);
   OnePopSetting pitch_spoof = this.create("Pitch Spoof", "PitchSpoof", false);
   private OnePopTimer packet_timer = new OnePopTimer();
   private OnePopTimer acceleration_timer = new OnePopTimer();
   private OnePopTimer acceleration_reset_timer = new OnePopTimer();
   private OnePopTimer instant_fly_timer = new OnePopTimer();
   private boolean send_message = false;
   private int elytra_slot = -1;
   @EventHandler
   private Listener<OnePopEventPlayerTravel> on_travel = new Listener((event) -> {
      if (mc.field_71439_g != null) {
         if (mc.field_71439_g.func_184582_a(EntityEquipmentSlot.CHEST).func_77973_b() == Items.field_185160_cR) {
            if (!mc.field_71439_g.func_184613_cA()) {
               if (!mc.field_71439_g.field_70122_E && this.instant_fly.get_value(true)) {
                  if (!this.instant_fly_timer.passed(1000L)) {
                     return;
                  }

                  this.instant_fly_timer.reset();
                  mc.field_71439_g.field_71174_a.func_147297_a(new CPacketEntityAction(mc.field_71439_g, Action.START_FALL_FLYING));
               }

            } else {
               if (!this.mode.in("Normal") && !this.mode.in("Tarzan") && !this.mode.in("Packet")) {
                  if (this.mode.in("Superior")) {
                     this.handle_immediate_mode_elytra(event);
                  } else if (this.mode.in("Control")) {
                     this.handle_control_mode(event);
                  }
               } else {
                  this.handle_normal_mode_elytra(event);
               }

            }
         }
      }
   }, new Predicate[0]);
   @EventHandler
   private Listener<OnePopEventPacket> packet_event = new Listener((event) -> {
      if (event.get_packet() instanceof CPacketPlayer && this.pitch_spoof.get_value(true)) {
         if (!mc.field_71439_g.func_184613_cA()) {
            return;
         }

         if (event.get_packet() instanceof PositionRotation && this.pitch_spoof.get_value(true)) {
            PositionRotation rotation = (PositionRotation)event.get_packet();
            mc.func_147114_u().func_147297_a(new Position(rotation.field_149479_a, rotation.field_149477_b, rotation.field_149478_c, rotation.field_149474_g));
            event.cancel();
         } else if (event.get_packet() instanceof Rotation && this.pitch_spoof.get_value(true)) {
            event.cancel();
         }
      }

   }, new Predicate[0]);

   public OnePopElytraFly() {
      super(OnePopCategory.ONEPOP_MOVEMENT);
      this.name = "Elytra Fly";
      this.tag = "ElytraFly";
      this.description = "yes this is skided from salhack fuck you i don't care";
   }

   public void enable() {
      this.elytra_slot = -1;
      if (this.equip_elytra.get_value(true) && mc.field_71439_g != null && mc.field_71439_g.func_184582_a(EntityEquipmentSlot.CHEST).func_77973_b() != Items.field_185160_cR) {
         for(int l_I = 0; l_I < 44; ++l_I) {
            ItemStack l_Stack = mc.field_71439_g.field_71071_by.func_70301_a(l_I);
            if (!l_Stack.func_190926_b() && l_Stack.func_77973_b() == Items.field_185160_cR) {
               ItemElytra l_Elytra = (ItemElytra)l_Stack.func_77973_b();
               this.elytra_slot = l_I;
               break;
            }
         }

         if (this.elytra_slot != -1) {
            boolean l_HasArmorAtChest = mc.field_71439_g.func_184582_a(EntityEquipmentSlot.CHEST).func_77973_b() != Items.field_190931_a;
            mc.field_71442_b.func_187098_a(mc.field_71439_g.field_71069_bz.field_75152_c, this.elytra_slot, 0, ClickType.PICKUP, mc.field_71439_g);
            mc.field_71442_b.func_187098_a(mc.field_71439_g.field_71069_bz.field_75152_c, 6, 0, ClickType.PICKUP, mc.field_71439_g);
            if (l_HasArmorAtChest) {
               mc.field_71442_b.func_187098_a(mc.field_71439_g.field_71069_bz.field_75152_c, this.elytra_slot, 0, ClickType.PICKUP, mc.field_71439_g);
            }
         }
      }

   }

   public void disable() {
      if (mc.field_71439_g != null) {
         if (this.elytra_slot != -1) {
            boolean l_HasItem = !mc.field_71439_g.field_71071_by.func_70301_a(this.elytra_slot).func_190926_b() || mc.field_71439_g.field_71071_by.func_70301_a(this.elytra_slot).func_77973_b() != Items.field_190931_a;
            mc.field_71442_b.func_187098_a(mc.field_71439_g.field_71069_bz.field_75152_c, 6, 0, ClickType.PICKUP, mc.field_71439_g);
            mc.field_71442_b.func_187098_a(mc.field_71439_g.field_71069_bz.field_75152_c, this.elytra_slot, 0, ClickType.PICKUP, mc.field_71439_g);
            if (l_HasItem) {
               mc.field_71442_b.func_187098_a(mc.field_71439_g.field_71069_bz.field_75152_c, 6, 0, ClickType.PICKUP, mc.field_71439_g);
            }
         }

      }
   }

   public void handle_normal_mode_elytra(OnePopEventPlayerTravel p_travel) {
      double l_YHeight = mc.field_71439_g.field_70163_u;
      if (l_YHeight <= (double)this.cancel_at_height.get_value(1)) {
         if (!this.send_message) {
            OnePopMessageUtil.send_client_message(ChatFormatting.RED + "WARNING, you must scaffold up or use fireworks, as YHeight <= CancelAtHeight!");
            this.send_message = true;
         }

      } else {
         boolean l_IsMoveKeyDown = mc.field_71439_g.field_71158_b.field_192832_b > 0.0F || mc.field_71439_g.field_71158_b.field_78902_a > 0.0F;
         boolean l_CancelInWater = !mc.field_71439_g.func_70090_H() && !mc.field_71439_g.func_180799_ab() && this.cancel_in_water.get_value(true);
         if (mc.field_71439_g.field_71158_b.field_78901_c) {
            p_travel.cancel();
            this.Accelerate();
         } else {
            if (!l_IsMoveKeyDown) {
               this.acceleration_timer.resetTimeSkipTo((long)(-this.v_acceleration_timer.get_value(1)));
            } else if ((mc.field_71439_g.field_70125_A <= (float)this.rotation_pitch.get_value(1) || this.mode.in("Tarzan")) && l_CancelInWater) {
               if (this.accelerate.get_value(true) && this.acceleration_timer.passed((long)this.v_acceleration_timer.get_value(1))) {
                  this.Accelerate();
                  return;
               }

               return;
            }

            p_travel.cancel();
            this.Accelerate();
         }
      }
   }

   public void handle_immediate_mode_elytra(OnePopEventPlayerTravel p_travel) {
      if (mc.field_71439_g.field_71158_b.field_78901_c) {
         double l_MotionSq = Math.sqrt(mc.field_71439_g.field_70159_w * mc.field_71439_g.field_70159_w + mc.field_71439_g.field_70179_y * mc.field_71439_g.field_70179_y);
         if (!(l_MotionSq > 1.0D)) {
            double[] dir = OnePopMathUtil.directionSpeedNoForward(this.speed.get_value(1.0D));
            mc.field_71439_g.field_70159_w = dir[0];
            mc.field_71439_g.field_70181_x = -(this.glide_speed.get_value(1.0D) / 10000.0D);
            mc.field_71439_g.field_70179_y = dir[1];
            p_travel.cancel();
         }
      } else {
         mc.field_71439_g.func_70016_h(0.0D, 0.0D, 0.0D);
         p_travel.cancel();
         double[] dir = OnePopMathUtil.directionSpeed(this.speed.get_value(1.0D));
         if (mc.field_71439_g.field_71158_b.field_78902_a != 0.0F || mc.field_71439_g.field_71158_b.field_192832_b != 0.0F) {
            mc.field_71439_g.field_70159_w = dir[0];
            mc.field_71439_g.field_70181_x = -(this.glide_speed.get_value(1.0D) / 10000.0D);
            mc.field_71439_g.field_70179_y = dir[1];
         }

         if (mc.field_71439_g.field_71158_b.field_78899_d) {
            mc.field_71439_g.field_70181_x = -this.down_speed.get_value(1.0D);
         }

         mc.field_71439_g.field_184618_aE = 0.0F;
         mc.field_71439_g.field_70721_aZ = 0.0F;
         mc.field_71439_g.field_184619_aG = 0.0F;
      }
   }

   public void Accelerate() {
      if (this.acceleration_reset_timer.passed((long)this.v_acceleration_timer.get_value(1))) {
         this.acceleration_reset_timer.reset();
         this.acceleration_timer.reset();
         this.send_message = false;
      }

      float l_Speed = (float)this.speed.get_value(1.0D);
      double[] dir = OnePopMathUtil.directionSpeed((double)l_Speed);
      mc.field_71439_g.field_70181_x = -(this.glide_speed.get_value(1.0D) / 10000.0D);
      if (mc.field_71439_g.field_71158_b.field_78902_a == 0.0F && mc.field_71439_g.field_71158_b.field_192832_b == 0.0F) {
         mc.field_71439_g.field_70159_w = 0.0D;
         mc.field_71439_g.field_70179_y = 0.0D;
      } else {
         mc.field_71439_g.field_70159_w = dir[0];
         mc.field_71439_g.field_70179_y = dir[1];
      }

      if (mc.field_71439_g.field_71158_b.field_78899_d) {
         mc.field_71439_g.field_70181_x = -this.down_speed.get_value(1.0D);
      }

      mc.field_71439_g.field_184618_aE = 0.0F;
      mc.field_71439_g.field_70721_aZ = 0.0F;
      mc.field_71439_g.field_184619_aG = 0.0F;
   }

   private void handle_control_mode(OnePopEventPlayerTravel p_Event) {
      double[] dir = OnePopMathUtil.directionSpeed(this.speed.get_value(1.0D));
      if (mc.field_71439_g.field_71158_b.field_78902_a == 0.0F && mc.field_71439_g.field_71158_b.field_192832_b == 0.0F) {
         mc.field_71439_g.field_70159_w = 0.0D;
         mc.field_71439_g.field_70179_y = 0.0D;
      } else {
         mc.field_71439_g.field_70159_w = dir[0];
         mc.field_71439_g.field_70179_y = dir[1];
         EntityPlayerSP var10000 = mc.field_71439_g;
         var10000.field_70159_w -= mc.field_71439_g.field_70159_w * (double)(Math.abs(mc.field_71439_g.field_70125_A) + 90.0F) / 90.0D - mc.field_71439_g.field_70159_w;
         var10000 = mc.field_71439_g;
         var10000.field_70179_y -= mc.field_71439_g.field_70179_y * (double)(Math.abs(mc.field_71439_g.field_70125_A) + 90.0F) / 90.0D - mc.field_71439_g.field_70179_y;
      }

      mc.field_71439_g.field_70181_x = -OnePopMathUtil.degToRad((double)mc.field_71439_g.field_70125_A) * (double)mc.field_71439_g.field_71158_b.field_192832_b;
      mc.field_71439_g.field_184618_aE = 0.0F;
      mc.field_71439_g.field_70721_aZ = 0.0F;
      mc.field_71439_g.field_184619_aG = 0.0F;
      p_Event.cancel();
   }
}
