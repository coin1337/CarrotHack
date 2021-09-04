package me.hero.onepop.onepopclient.hacks.render;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.hero.onepop.onepopclient.event.events.OnePopEventRender;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopEntityUtil;
import me.hero.turok.draw.OldRenderHelp;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class OnePopCityEsp extends OnePopHack {
   OnePopSetting endcrystal_mode = this.create("EndCrystal", "CityEndCrystal", false);
   OnePopSetting mode = this.create("Mode", "CityMode", "Pretty", this.combobox(new String[]{"Pretty", "Solid", "Outline"}));
   OnePopSetting off_set = this.create("Height", "CityOffSetSide", 0.0D, 0.0D, 1.0D);
   OnePopSetting range = this.create("Range", "CityRange", 6, 1, 12);
   OnePopSetting r = this.create("R", "CityR", 255, 0, 255);
   OnePopSetting g = this.create("G", "CityG", 0, 0, 255);
   OnePopSetting b = this.create("B", "CityB", 0, 0, 255);
   OnePopSetting a = this.create("A", "CityA", 255, 0, 255);
   List<BlockPos> blocks = new ArrayList();
   boolean outline = false;
   boolean solid = false;

   public OnePopCityEsp() {
      super(OnePopCategory.ONEPOP_RENDER);
      this.name = "CityESP";
      this.tag = "CityESP";
      this.description = "@FLLMALL";
   }

   public void update() {
      this.blocks.clear();
      Iterator var1 = mc.field_71441_e.field_73010_i.iterator();

      while(var1.hasNext()) {
         EntityPlayer player = (EntityPlayer)var1.next();
         if (!(mc.field_71439_g.func_70032_d(player) > (float)this.range.get_value(1)) && mc.field_71439_g != player) {
            BlockPos p = OnePopEntityUtil.is_cityable(player, this.endcrystal_mode.get_value(true));
            if (p != null) {
               this.blocks.add(p);
            }
         }
      }

   }

   public void render(OnePopEventRender event) {
      float off_set_h = (float)this.off_set.get_value(1.0D);
      Iterator var3 = this.blocks.iterator();

      while(var3.hasNext()) {
         BlockPos pos = (BlockPos)var3.next();
         if (this.mode.in("Pretty")) {
            this.outline = true;
            this.solid = true;
         }

         if (this.mode.in("Solid")) {
            this.outline = false;
            this.solid = true;
         }

         if (this.mode.in("Outline")) {
            this.outline = true;
            this.solid = false;
         }

         if (this.solid) {
            OldRenderHelp.prepare("quads");
            OldRenderHelp.draw_cube(OldRenderHelp.get_buffer_build(), (float)pos.func_177958_n(), (float)pos.func_177956_o(), (float)pos.func_177952_p(), 1.0F, off_set_h, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
            OldRenderHelp.release();
         }

         if (this.outline) {
            OldRenderHelp.prepare("lines");
            OldRenderHelp.draw_cube_line(OldRenderHelp.get_buffer_build(), (float)pos.func_177958_n(), (float)pos.func_177956_o(), (float)pos.func_177952_p(), 1.0F, off_set_h, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
            OldRenderHelp.release();
         }
      }

   }
}
