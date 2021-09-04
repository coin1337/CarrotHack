package me.hero.onepop.onepopclient.hacks.combat;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.event.events.OnePopEventEntityRemoved;
import me.hero.onepop.onepopclient.event.events.OnePopEventMotionUpdate;
import me.hero.onepop.onepopclient.event.events.OnePopEventPacket;
import me.hero.onepop.onepopclient.event.events.OnePopEventRender;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.hacks.chat.OnePopAutoEz;
import me.hero.onepop.onepopclient.util.OnePopBlockUtil;
import me.hero.onepop.onepopclient.util.OnePopCrystalUtil;
import me.hero.onepop.onepopclient.util.OnePopEntityUtil;
import me.hero.onepop.onepopclient.util.OnePopFriendUtil;
import me.hero.onepop.onepopclient.util.OnePopInventoryUtil;
import me.hero.onepop.onepopclient.util.OnePopMathUtil;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import me.hero.onepop.onepopclient.util.OnePopPair;
import me.hero.onepop.onepopclient.util.OnePopPosManager;
import me.hero.onepop.onepopclient.util.OnePopRenderUtil;
import me.hero.onepop.onepopclient.util.OnePopRotationUtil;
import me.hero.onepop.onepopclient.util.OnePopTimer;
import me.hero.turok.draw.OldRenderHelp;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class OnePopAutoCrystal extends OnePopHack {
   OnePopSetting place_crystal = this.create("Place", "CaPlace", true);
   OnePopSetting break_crystal = this.create("Break", "CaBreak", true);
   OnePopSetting break_tries = this.create("Break Attempts", "CaBreakAttempts", 2, 1, 6);
   OnePopSetting hit_range = this.create("Break Range", "CaHitRange", 5.199999809265137D, 1.0D, 6.0D);
   OnePopSetting place_range = this.create("Place Range", "CaPlaceRange", 5.199999809265137D, 1.0D, 6.0D);
   OnePopSetting hit_range_wall = this.create("Range Wall", "CaRangeWall", 4.0D, 1.0D, 6.0D);
   OnePopSetting place_delay = this.create("Place Delay", "CaPlaceDelay", 1, 0, 10);
   OnePopSetting break_delay = this.create("Break Delay", "CaBreakDelay", 2, 0, 10);
   OnePopSetting min_player_place = this.create("Min Enemy Place", "CaMinEnemyPlace", 8, 0, 20);
   OnePopSetting min_player_break = this.create("Min Enemy Break", "CaMinEnemyBreak", 6, 0, 20);
   OnePopSetting max_self_damage = this.create("Max Self Damage", "CaMaxSelfDamage", 6, 0, 20);
   OnePopSetting faceplace_mode = this.create("Faceplace", "CaFaceplaceMode", true);
   OnePopSetting faceplace_mode_damage = this.create("Face Health", "CaFaceplaceModeHealth", 8, 0, 36);
   OnePopSetting fuck_armor_mode = this.create("Armor Break", "CaArmorDestroy", true);
   OnePopSetting fuck_armor_mode_percent = this.create("Armor %", "CaArmorPercent", 25, 0, 100);
   OnePopSetting fast_mode = this.create("Fast", "CaSpeed", true);
   OnePopSetting auto_switch = this.create("Auto Switch", "CaAutoSwitch", false);
   OnePopSetting anti_weakness = this.create("Anti-Weakness", "CaAntiWeakness", true);
   OnePopSetting anti_suicide = this.create("Anti Suicide", "CaAntiSuicide", true);
   OnePopSetting anti_stuck = this.create("Anti Stuck", "CaAntiStuck", true);
   OnePopSetting break_all = this.create("Break All", "CaBreakAllMode", false);
   OnePopSetting packet_break = this.create("Packet Break", "CaPacketBreak", false);
   OnePopSetting stop_while_mining = this.create("Stop While Mining", "CaStopWhileMining", false);
   OnePopSetting stop_while_eating = this.create("Stop While Eating", "CaStopWhileEating", false);
   OnePopSetting stop_while_holefill = this.create("Stop While Holefill", "CaStopWhileHoleFill", false);
   OnePopSetting faceplace_check = this.create("No Sword FP", "CaNoSwordFaceMode", false);
   OnePopSetting swing = this.create("Swing", "CaSwing", "Mainhand", this.combobox(new String[]{"Mainhand", "Offhand", "Both", "None"}));
   OnePopSetting render_mode = this.create("Render", "CaRenderMode", "Pretty", this.combobox(new String[]{"Pretty", "Solid", "Outline", "None"}));
   OnePopSetting render_damage = this.create("Render Damage", "CaRenderDamage", true);
   OnePopSetting top_block = this.create("Top Block", "CaTopBlock", false);
   OnePopSetting r = this.create("R", "CaR", 255, 0, 255);
   OnePopSetting g = this.create("G", "CaG", 255, 0, 255);
   OnePopSetting b = this.create("B", "CaB", 255, 0, 255);
   OnePopSetting a = this.create("A", "CaA", 100, 0, 255);
   OnePopSetting a_out = this.create("Outline A", "CaOutlineA", 255, 0, 255);
   OnePopSetting rainbow_mode = this.create("Rainbow", "CaRainbow", false);
   OnePopSetting sat = this.create("Satiation", "CaSatiation", 0.8D, 0.0D, 1.0D);
   OnePopSetting brightness = this.create("Brightness", "CaBrightness", 0.8D, 0.0D, 1.0D);
   OnePopSetting height = this.create("Height", "CaHeight", 1.0D, 0.0D, 1.0D);
   OnePopSetting raytrace = this.create("Raytrace", "CaRaytrace", false);
   OnePopSetting client_side = this.create("Pyro Mode", "CaClientSide", false);
   OnePopSetting endcrystal = this.create("1.13 Mode", "CaThirteen", false);
   OnePopSetting rotate_mode = this.create("Rotate", "CaRotateMode", "Off", this.combobox(new String[]{"Off", "Old", "Const", "Good"}));
   OnePopSetting debug = this.create("Debug", "CaDebug", false);
   private final ConcurrentHashMap<EntityEnderCrystal, Integer> attacked_crystals = new ConcurrentHashMap();
   private final OnePopTimer remove_visual_timer = new OnePopTimer();
   private final OnePopTimer chain_timer = new OnePopTimer();
   private EntityPlayer autoez_target = null;
   private String detail_name = null;
   private BlockPos render_block_init;
   private double render_damage_value;
   private float yaw;
   private float pitch;
   private boolean already_attacking = false;
   private boolean place_timeout_flag = false;
   private boolean is_rotating;
   private boolean did_anything;
   private boolean outline;
   private boolean solid;
   private int place_timeout;
   private int break_timeout;
   private int break_delay_counter;
   private int place_delay_counter;
   @EventHandler
   private final Listener<OnePopEventEntityRemoved> on_entity_removed = new Listener((event) -> {
      if (event.get_entity() instanceof EntityEnderCrystal) {
         this.attacked_crystals.remove(event.get_entity());
      }

   }, new Predicate[0]);
   @EventHandler
   private final Listener<OnePopEventPacket.SendPacket> send_listener = new Listener((event) -> {
      if (event.get_packet() instanceof CPacketPlayer && this.is_rotating && this.rotate_mode.in("Old")) {
         if (this.debug.get_value(true)) {
            OnePopMessageUtil.send_client_message("Rotating");
         }

         CPacketPlayer px = (CPacketPlayer)event.get_packet();
         px.field_149476_e = this.yaw;
         px.field_149473_f = this.pitch;
         this.is_rotating = false;
      }

      if (event.get_packet() instanceof CPacketPlayerTryUseItemOnBlock && this.is_rotating && this.rotate_mode.in("Old")) {
         if (this.debug.get_value(true)) {
            OnePopMessageUtil.send_client_message("Rotating");
         }

         CPacketPlayerTryUseItemOnBlock p = (CPacketPlayerTryUseItemOnBlock)event.get_packet();
         p.field_149577_f = (float)this.render_block_init.func_177958_n();
         p.field_149578_g = (float)this.render_block_init.func_177956_o();
         p.field_149584_h = (float)this.render_block_init.func_177952_p();
         this.is_rotating = false;
      }

   }, new Predicate[0]);
   @EventHandler
   private final Listener<OnePopEventMotionUpdate> on_movement = new Listener((event) -> {
      if (event.stage == 0 && (this.rotate_mode.in("Good") || this.rotate_mode.in("Const"))) {
         if (this.debug.get_value(true)) {
            OnePopMessageUtil.send_client_message("updating rotation");
         }

         OnePopPosManager.updatePosition();
         OnePopRotationUtil.updateRotations();
         this.do_ca();
      }

      if (event.stage == 1 && (this.rotate_mode.in("Good") || this.rotate_mode.in("Const"))) {
         if (this.debug.get_value(true)) {
            OnePopMessageUtil.send_client_message("resetting rotation");
         }

         OnePopPosManager.restorePosition();
         OnePopRotationUtil.restoreRotations();
      }

   }, new Predicate[0]);
   @EventHandler
   private final Listener<OnePopEventPacket.ReceivePacket> receive_listener = new Listener((event) -> {
      if (event.get_packet() instanceof SPacketSoundEffect) {
         SPacketSoundEffect packet = (SPacketSoundEffect)event.get_packet();
         if (packet.func_186977_b() == SoundCategory.BLOCKS && packet.func_186978_a() == SoundEvents.field_187539_bB) {
            Iterator var2 = mc.field_71441_e.field_72996_f.iterator();

            while(var2.hasNext()) {
               Entity e = (Entity)var2.next();
               if (e instanceof EntityEnderCrystal && e.func_70011_f(packet.func_149207_d(), packet.func_149211_e(), packet.func_149210_f()) <= 6.0D) {
                  e.func_70106_y();
               }
            }
         }
      }

   }, new Predicate[0]);

   public OnePopAutoCrystal() {
      super(OnePopCategory.ONEPOP_COMBAT);
      this.name = "Auto Crystal";
      this.tag = "AutoCrystal";
      this.description = "fucking strong :muscle:";
   }

   public void do_ca() {
      this.did_anything = false;
      if (mc.field_71439_g != null && mc.field_71441_e != null) {
         if (this.rainbow_mode.get_value(true)) {
            this.cycle_rainbow();
         }

         if (this.remove_visual_timer.passed(1000L)) {
            this.remove_visual_timer.reset();
            this.attacked_crystals.clear();
         }

         if (!this.check_pause()) {
            if (this.place_crystal.get_value(true) && this.place_delay_counter > this.place_timeout) {
               this.place_crystal();
            }

            if (this.break_crystal.get_value(true) && this.break_delay_counter > this.break_timeout) {
               this.break_crystal();
            }

            if (!this.did_anything) {
               this.autoez_target = null;
               this.is_rotating = false;
            }

            if (this.autoez_target != null) {
               OnePopAutoEz.add_target(this.autoez_target.func_70005_c_());
               this.detail_name = this.autoez_target.func_70005_c_();
            }

            if (this.chain_timer.passed(1000L)) {
               this.chain_timer.reset();
            }

            ++this.break_delay_counter;
            ++this.place_delay_counter;
         }
      }
   }

   public void update() {
      if (this.rotate_mode.in("Off") || this.rotate_mode.in("Old")) {
         this.do_ca();
      }

   }

   public void cycle_rainbow() {
      float[] tick_color = new float[]{(float)(System.currentTimeMillis() % 11520L) / 11520.0F};
      int color_rgb_o = Color.HSBtoRGB(tick_color[0], (float)this.sat.get_value(1), (float)this.brightness.get_value(1));
      this.r.set_value(color_rgb_o >> 16 & 255);
      this.g.set_value(color_rgb_o >> 8 & 255);
      this.b.set_value(color_rgb_o & 255);
   }

   public EntityEnderCrystal get_best_crystal() {
      double best_damage = 0.0D;
      double maximum_damage_self = (double)this.max_self_damage.get_value(1);
      double best_distance = 0.0D;
      EntityEnderCrystal best_crystal = null;
      Iterator var10 = mc.field_71441_e.field_72996_f.iterator();

      label155:
      while(true) {
         EntityEnderCrystal crystal;
         do {
            do {
               do {
                  do {
                     Entity c;
                     do {
                        if (!var10.hasNext()) {
                           return best_crystal;
                        }

                        c = (Entity)var10.next();
                     } while(!(c instanceof EntityEnderCrystal));

                     crystal = (EntityEnderCrystal)c;
                  } while(mc.field_71439_g.func_70032_d(crystal) > (float)(!mc.field_71439_g.func_70685_l(crystal) ? this.hit_range_wall.get_value(1) : this.hit_range.get_value(1)));
               } while(!mc.field_71439_g.func_70685_l(crystal) && this.raytrace.get_value(true));
            } while(crystal.field_70128_L);
         } while(this.attacked_crystals.containsKey(crystal) && (Integer)this.attacked_crystals.get(crystal) > 5 && this.anti_stuck.get_value(true));

         Iterator var13 = mc.field_71441_e.field_73010_i.iterator();

         while(true) {
            EntityPlayer target;
            double target_damage;
            double self_damage;
            do {
               do {
                  double minimum_damage;
                  do {
                     do {
                        do {
                           Entity player;
                           do {
                              do {
                                 do {
                                    do {
                                       if (!var13.hasNext()) {
                                          if (this.break_all.get_value(true) && mc.field_71439_g.func_70068_e(crystal) > best_distance) {
                                             best_distance = mc.field_71439_g.func_70068_e(crystal);
                                             best_crystal = crystal;
                                          }
                                          continue label155;
                                       }

                                       player = (Entity)var13.next();
                                    } while(player == mc.field_71439_g);
                                 } while(!(player instanceof EntityPlayer));
                              } while(OnePopFriendUtil.isFriend(player.func_70005_c_()));
                           } while(player.func_70032_d(mc.field_71439_g) >= 11.0F);

                           target = (EntityPlayer)player;
                        } while(target.field_70128_L);
                     } while(target.func_110143_aJ() <= 0.0F);

                     boolean no_place = this.faceplace_check.get_value(true) && mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_151048_u;
                     if ((!(target.func_110143_aJ() < (float)this.faceplace_mode_damage.get_value(1)) || !this.faceplace_mode.get_value(true) || no_place) && (!this.get_armor_fucker(target) || no_place)) {
                        minimum_damage = (double)this.min_player_break.get_value(1);
                     } else {
                        minimum_damage = 2.0D;
                     }

                     target_damage = (double)OnePopCrystalUtil.calculateDamage(crystal, target);
                  } while(target_damage < minimum_damage);

                  self_damage = (double)OnePopCrystalUtil.calculateDamage(crystal, mc.field_71439_g);
               } while(self_damage > maximum_damage_self);
            } while(this.anti_suicide.get_value(true) && (double)(mc.field_71439_g.func_110143_aJ() + mc.field_71439_g.func_110139_bj()) - self_damage <= 0.5D);

            if (target_damage > best_damage && !this.break_all.get_value(true)) {
               this.autoez_target = target;
               best_damage = target_damage;
               best_crystal = crystal;
            }
         }
      }
   }

   public BlockPos get_best_block() {
      if (this.get_best_crystal() != null && !this.fast_mode.get_value(true)) {
         this.place_timeout_flag = true;
         return null;
      } else if (this.place_timeout_flag) {
         this.place_timeout_flag = false;
         return null;
      } else {
         List<OnePopPair<Double, BlockPos>> damage_blocks = new ArrayList();
         double best_damage = 0.0D;
         double maximum_damage_self = (double)this.max_self_damage.get_value(1);
         BlockPos best_block = null;
         List<BlockPos> blocks = OnePopCrystalUtil.possiblePlacePositions((float)this.place_range.get_value(1), this.endcrystal.get_value(true), true);
         Iterator var10 = mc.field_71441_e.field_73010_i.iterator();

         label128:
         while(true) {
            Entity player;
            do {
               if (!var10.hasNext()) {
                  blocks.clear();
                  this.render_damage_value = best_damage;
                  this.render_block_init = best_block;
                  this.sort_best_blocks(damage_blocks);
                  return best_block;
               }

               player = (Entity)var10.next();
            } while(OnePopFriendUtil.isFriend(player.func_70005_c_()));

            Iterator var12 = blocks.iterator();

            while(true) {
               BlockPos block;
               EntityPlayer target;
               double target_damage;
               double self_damage;
               do {
                  do {
                     double minimum_damage;
                     do {
                        do {
                           do {
                              do {
                                 do {
                                    do {
                                       do {
                                          do {
                                             if (!var12.hasNext()) {
                                                continue label128;
                                             }

                                             block = (BlockPos)var12.next();
                                          } while(player == mc.field_71439_g);
                                       } while(!(player instanceof EntityPlayer));
                                    } while(player.func_70032_d(mc.field_71439_g) >= 11.0F);
                                 } while(!OnePopBlockUtil.rayTracePlaceCheck(block, this.raytrace.get_value(true)));
                              } while(!OnePopBlockUtil.canSeeBlock(block) && mc.field_71439_g.func_70011_f((double)block.func_177958_n(), (double)block.func_177956_o(), (double)block.func_177952_p()) > (double)this.hit_range_wall.get_value(1));

                              target = (EntityPlayer)player;
                           } while(target.field_70128_L);
                        } while(target.func_110143_aJ() <= 0.0F);

                        boolean no_place = this.faceplace_check.get_value(true) && mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_151048_u;
                        if ((!(target.func_110143_aJ() < (float)this.faceplace_mode_damage.get_value(1)) || !this.faceplace_mode.get_value(true) || no_place) && (!this.get_armor_fucker(target) || no_place)) {
                           minimum_damage = (double)this.min_player_place.get_value(1);
                        } else {
                           minimum_damage = 2.0D;
                        }

                        target_damage = (double)OnePopCrystalUtil.calculateDamage((double)block.func_177958_n() + 0.5D, (double)block.func_177956_o() + 1.0D, (double)block.func_177952_p() + 0.5D, target);
                     } while(target_damage < minimum_damage);

                     self_damage = (double)OnePopCrystalUtil.calculateDamage((double)block.func_177958_n() + 0.5D, (double)block.func_177956_o() + 1.0D, (double)block.func_177952_p() + 0.5D, mc.field_71439_g);
                  } while(self_damage > maximum_damage_self);
               } while(this.anti_suicide.get_value(true) && (double)(mc.field_71439_g.func_110143_aJ() + mc.field_71439_g.func_110139_bj()) - self_damage <= 0.5D);

               if (target_damage > best_damage) {
                  best_damage = target_damage;
                  best_block = block;
                  this.autoez_target = target;
               }
            }
         }
      }
   }

   public void sort_best_blocks(List<OnePopPair<Double, BlockPos>> list) {
      List<OnePopPair<Double, BlockPos>> new_list = new ArrayList();
      double damage_cap = 1000.0D;

      for(int i = 0; i < list.size(); ++i) {
         double biggest_dam = 0.0D;
         OnePopPair<Double, BlockPos> best_pair = null;
         Iterator var9 = list.iterator();

         while(var9.hasNext()) {
            OnePopPair<Double, BlockPos> pair = (OnePopPair)var9.next();
            if ((Double)pair.getKey() > biggest_dam && (Double)pair.getKey() < damage_cap) {
               best_pair = pair;
            }
         }

         if (best_pair != null) {
            damage_cap = (Double)best_pair.getKey();
            new_list.add(best_pair);
         }
      }

   }

   public void place_crystal() {
      BlockPos target_block = this.get_best_block();
      if (target_block != null) {
         this.place_delay_counter = 0;
         this.already_attacking = false;
         boolean offhand_check = false;
         if (mc.field_71439_g.func_184592_cb().func_77973_b() != Items.field_185158_cP) {
            if (mc.field_71439_g.func_184614_ca().func_77973_b() != Items.field_185158_cP && this.auto_switch.get_value(true)) {
               OnePopInventoryUtil.switchToSlot(Items.field_185158_cP);
               return;
            }
         } else {
            offhand_check = true;
         }

         if (this.debug.get_value(true)) {
            OnePopMessageUtil.send_client_message("placing");
         }

         this.did_anything = true;
         this.rotate_to_pos(target_block);
         this.chain_timer.reset();
         OnePopBlockUtil.placeCrystalOnBlock(target_block, offhand_check ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
      }
   }

   public boolean get_armor_fucker(EntityPlayer p) {
      Iterator var2 = p.func_184193_aE().iterator();

      float armor_percent;
      do {
         if (!var2.hasNext()) {
            return false;
         }

         ItemStack stack = (ItemStack)var2.next();
         if (stack == null || stack.func_77973_b() == Items.field_190931_a) {
            return true;
         }

         armor_percent = (float)(stack.func_77958_k() - stack.func_77952_i()) / (float)stack.func_77958_k() * 100.0F;
      } while(!this.fuck_armor_mode.get_value(true) || !((float)this.fuck_armor_mode_percent.get_value(1) >= armor_percent));

      return true;
   }

   public void break_crystal() {
      EntityEnderCrystal crystal = this.get_best_crystal();
      if (crystal != null) {
         if (this.anti_weakness.get_value(true) && mc.field_71439_g.func_70644_a(MobEffects.field_76437_t)) {
            boolean should_weakness = true;
            if (mc.field_71439_g.func_70644_a(MobEffects.field_76420_g) && ((PotionEffect)Objects.requireNonNull(mc.field_71439_g.func_70660_b(MobEffects.field_76420_g))).func_76458_c() == 2) {
               should_weakness = false;
            }

            if (should_weakness) {
               if (!this.already_attacking) {
                  this.already_attacking = true;
               }

               int new_slot = -1;

               for(int i = 0; i < 9; ++i) {
                  ItemStack stack = mc.field_71439_g.field_71071_by.func_70301_a(i);
                  if (stack.func_77973_b() instanceof ItemSword || stack.func_77973_b() instanceof ItemTool) {
                     new_slot = i;
                     mc.field_71442_b.func_78765_e();
                     break;
                  }
               }

               if (new_slot != -1) {
                  mc.field_71439_g.field_71071_by.field_70461_c = new_slot;
               }
            }
         }

         if (this.debug.get_value(true)) {
            OnePopMessageUtil.send_client_message("attacking");
         }

         this.did_anything = true;
         this.rotate_to(crystal);

         for(int i = 0; i < this.break_tries.get_value(1); ++i) {
            OnePopEntityUtil.attackEntity(crystal, this.packet_break.get_value(true), this.swing);
         }

         this.add_attacked_crystal(crystal);
         if (this.client_side.get_value(true) && crystal.func_70089_S()) {
            crystal.func_70106_y();
         }

         this.break_delay_counter = 0;
      }
   }

   public boolean check_pause() {
      if (this.stop_while_mining.get_value(true) && mc.field_71474_y.field_74312_F.func_151470_d() && mc.field_71439_g.func_184614_ca().func_77973_b() instanceof ItemPickaxe) {
         return true;
      } else if (this.stop_while_eating.get_value(true) && mc.field_71474_y.field_74313_G.func_151470_d() && mc.field_71439_g.func_184614_ca().func_77973_b() instanceof ItemAppleGold) {
         return true;
      } else if (this.stop_while_holefill.get_value(true) && OnePop.get_hack_manager().get_module_with_tag("HoleFill").is_active()) {
         return true;
      } else if (OnePop.get_hack_manager().get_module_with_tag("Surround").is_active()) {
         return true;
      } else {
         if (OnePop.get_hack_manager().get_module_with_tag("AutoSwitchStop").is_active()) {
            this.auto_switch.set_value(false);
         } else if (OnePop.get_hack_manager().get_module_with_tag("AutoSwitchStop").is_disabled()) {
            this.auto_switch.set_value(true);
         }

         return OnePop.get_hack_manager().get_module_with_tag("HoleFill").is_active() ? true : OnePop.get_hack_manager().get_module_with_tag("Trap").is_active();
      }
   }

   private void add_attacked_crystal(EntityEnderCrystal crystal) {
      if (this.attacked_crystals.containsKey(crystal)) {
         int value = (Integer)this.attacked_crystals.get(crystal);
         this.attacked_crystals.put(crystal, value + 1);
      } else {
         this.attacked_crystals.put(crystal, 1);
      }

   }

   public void rotate_to_pos(BlockPos pos) {
      float[] angle;
      if (this.rotate_mode.in("Const")) {
         angle = OnePopMathUtil.calcAngle(mc.field_71439_g.func_174824_e(mc.func_184121_ak()), new Vec3d((double)((float)pos.func_177958_n() + 0.5F), (double)((float)pos.func_177956_o() + 0.5F), (double)((float)pos.func_177952_p() + 0.5F)));
      } else {
         angle = OnePopMathUtil.calcAngle(mc.field_71439_g.func_174824_e(mc.func_184121_ak()), new Vec3d((double)((float)pos.func_177958_n() + 0.5F), (double)((float)pos.func_177956_o() - 0.5F), (double)((float)pos.func_177952_p() + 0.5F)));
      }

      if (this.rotate_mode.in("Off")) {
         this.is_rotating = false;
      }

      if (this.rotate_mode.in("Good") || this.rotate_mode.in("Const")) {
         OnePopRotationUtil.setPlayerRotations(angle[0], angle[1]);
      }

      if (this.rotate_mode.in("Old")) {
         this.yaw = angle[0];
         this.pitch = angle[1];
         this.is_rotating = true;
      }

   }

   public void rotate_to(Entity entity) {
      float[] angle = OnePopMathUtil.calcAngle(mc.field_71439_g.func_174824_e(mc.func_184121_ak()), entity.func_174791_d());
      if (this.rotate_mode.in("Off")) {
         this.is_rotating = false;
      }

      if (this.rotate_mode.in("Good")) {
         OnePopRotationUtil.setPlayerRotations(angle[0], angle[1]);
      }

      if (this.rotate_mode.in("Old") || this.rotate_mode.in("Cont")) {
         this.yaw = angle[0];
         this.pitch = angle[1];
         this.is_rotating = true;
      }

   }

   public void render(OnePopEventRender event) {
      if (this.render_block_init != null) {
         if (!this.render_mode.in("None")) {
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

            this.render_block(this.render_block_init);
            if (this.render_damage.get_value(true)) {
               OnePopRenderUtil.drawText(this.render_block_init, (Math.floor(this.render_damage_value) == this.render_damage_value ? (int)this.render_damage_value : String.format("%.1f", this.render_damage_value)) + "");
            }

         }
      }
   }

   public void render_block(BlockPos pos) {
      BlockPos render_block = this.top_block.get_value(true) ? pos.func_177984_a() : pos;
      float h = (float)this.height.get_value(1.0D);
      if (this.solid) {
         OldRenderHelp.prepare("quads");
         OldRenderHelp.draw_cube(OldRenderHelp.get_buffer_build(), (float)render_block.func_177958_n(), (float)render_block.func_177956_o(), (float)render_block.func_177952_p(), 1.0F, h, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
         OldRenderHelp.release();
      }

      if (this.outline) {
         OldRenderHelp.prepare("lines");
         OldRenderHelp.draw_cube_line(OldRenderHelp.get_buffer_build(), (float)render_block.func_177958_n(), (float)render_block.func_177956_o(), (float)render_block.func_177952_p(), 1.0F, h, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a_out.get_value(1), "all");
         OldRenderHelp.release();
      }

   }

   public void enable() {
      this.place_timeout = this.place_delay.get_value(1);
      this.break_timeout = this.break_delay.get_value(1);
      this.place_timeout_flag = false;
      this.is_rotating = false;
      this.autoez_target = null;
      this.chain_timer.reset();
      this.remove_visual_timer.reset();
      this.detail_name = null;
   }

   public void disable() {
      this.render_block_init = null;
      this.autoez_target = null;
   }

   public String array_detail() {
      return this.detail_name != null ? this.detail_name : "None";
   }
}
