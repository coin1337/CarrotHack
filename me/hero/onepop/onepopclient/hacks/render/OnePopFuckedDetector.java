package me.hero.onepop.onepopclient.hacks.render;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import me.hero.onepop.onepopclient.event.events.OnePopEventRender;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopCrystalUtil;
import me.hero.onepop.onepopclient.util.OnePopEntityUtil;
import me.hero.onepop.onepopclient.util.OnePopFriendUtil;
import me.hero.turok.draw.OldRenderHelp;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class OnePopFuckedDetector extends OnePopHack {
   OnePopSetting draw_own = this.create("Draw Own", "FuckedDrawOwn", false);
   OnePopSetting draw_friends = this.create("Draw Friends", "FuckedDrawFriends", false);
   OnePopSetting render_mode = this.create("Render Mode", "FuckedRenderMode", "Pretty", this.combobox(new String[]{"Pretty", "Solid", "Outline"}));
   OnePopSetting r = this.create("R", "FuckedR", 255, 0, 255);
   OnePopSetting g = this.create("G", "FuckedG", 255, 0, 255);
   OnePopSetting b = this.create("B", "FuckedB", 255, 0, 255);
   OnePopSetting a = this.create("A", "FuckedA", 100, 0, 255);
   private boolean solid;
   private boolean outline;
   public Set<BlockPos> fucked_players = new HashSet();

   public OnePopFuckedDetector() {
      super(OnePopCategory.ONEPOP_RENDER);
      this.name = "Fucked Detector";
      this.tag = "FuckedDetector";
      this.description = "see if people are hecked";
   }

   protected void enable() {
      this.fucked_players.clear();
   }

   public void update() {
      if (mc.field_71441_e != null) {
         this.set_fucked_players();
      }
   }

   public void set_fucked_players() {
      this.fucked_players.clear();
      Iterator var1 = mc.field_71441_e.field_73010_i.iterator();

      while(true) {
         EntityPlayer player;
         do {
            do {
               do {
                  do {
                     do {
                        if (!var1.hasNext()) {
                           return;
                        }

                        player = (EntityPlayer)var1.next();
                     } while(!OnePopEntityUtil.isLiving(player));
                  } while(player.func_110143_aJ() <= 0.0F);
               } while(!this.is_fucked(player));
            } while(OnePopFriendUtil.isFriend(player.func_70005_c_()) && !this.draw_friends.get_value(true));
         } while(player == mc.field_71439_g && !this.draw_own.get_value(true));

         this.fucked_players.add(new BlockPos(player.field_70165_t, player.field_70163_u, player.field_70161_v));
      }
   }

   public boolean is_fucked(EntityPlayer player) {
      BlockPos pos = new BlockPos(player.field_70165_t, player.field_70163_u - 1.0D, player.field_70161_v);
      if (!OnePopCrystalUtil.canPlaceCrystal(pos.func_177968_d()) && (!OnePopCrystalUtil.canPlaceCrystal(pos.func_177968_d().func_177968_d()) || mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 1, 1)).func_177230_c() != Blocks.field_150350_a)) {
         if (OnePopCrystalUtil.canPlaceCrystal(pos.func_177974_f()) || OnePopCrystalUtil.canPlaceCrystal(pos.func_177974_f().func_177974_f()) && mc.field_71441_e.func_180495_p(pos.func_177982_a(1, 1, 0)).func_177230_c() == Blocks.field_150350_a) {
            return true;
         } else if (!OnePopCrystalUtil.canPlaceCrystal(pos.func_177976_e()) && (!OnePopCrystalUtil.canPlaceCrystal(pos.func_177976_e().func_177976_e()) || mc.field_71441_e.func_180495_p(pos.func_177982_a(-1, 1, 0)).func_177230_c() != Blocks.field_150350_a)) {
            return OnePopCrystalUtil.canPlaceCrystal(pos.func_177978_c()) || OnePopCrystalUtil.canPlaceCrystal(pos.func_177978_c().func_177978_c()) && mc.field_71441_e.func_180495_p(pos.func_177982_a(0, 1, -1)).func_177230_c() == Blocks.field_150350_a;
         } else {
            return true;
         }
      } else {
         return true;
      }
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

      Iterator var2 = this.fucked_players.iterator();

      while(var2.hasNext()) {
         BlockPos render_block = (BlockPos)var2.next();
         if (render_block == null) {
            return;
         }

         if (this.solid) {
            OldRenderHelp.prepare("quads");
            OldRenderHelp.draw_cube(OldRenderHelp.get_buffer_build(), (float)render_block.func_177958_n(), (float)render_block.func_177956_o(), (float)render_block.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
            OldRenderHelp.release();
         }

         if (this.outline) {
            OldRenderHelp.prepare("lines");
            OldRenderHelp.draw_cube_line(OldRenderHelp.get_buffer_build(), (float)render_block.func_177958_n(), (float)render_block.func_177956_o(), (float)render_block.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
            OldRenderHelp.release();
         }
      }

   }
}
