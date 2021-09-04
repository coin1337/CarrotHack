package me.hero.onepop.onepopclient.hacks.render;

import java.awt.Color;
import java.util.function.Predicate;
import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import me.hero.onepop.onepopclient.event.events.OnePopEventRenderEntity;
import me.hero.onepop.onepopclient.event.events.OnePopEventRenderEntityModel;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopRenderUtil;
import me.rina.turok.render.opengl.TurokRenderGL;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class OnePopChams extends OnePopHack {
   OnePopSetting mode = this.create("Mode", "ChamsMode", "Outline", this.combobox(new String[]{"Outline", "Pig", "Wireframe", "FilhoDaPuta"}));
   OnePopSetting players = this.create("Players", "ChamsPlayers", true);
   OnePopSetting mobs = this.create("Mobs", "ChamsMobs", true);
   OnePopSetting pigs_animals = this.create("Pigs & Animals", "ChamsPigs&Animals", true);
   OnePopSetting self = this.create("Self", "ChamsSelf", true);
   OnePopSetting top = this.create("Top", "ChamsTop", true);
   OnePopSetting r = this.create("R", "ChamsR", 255, 0, 255);
   OnePopSetting g = this.create("G", "ChamsG", 255, 0, 255);
   OnePopSetting b = this.create("B", "ChamsB", 255, 0, 255);
   OnePopSetting a = this.create("A", "ChamsA", 100, 0, 255);
   OnePopSetting width = this.create("Width", "ChamsWdith", 2.0D, 0.5D, 5.0D);
   OnePopSetting rainbow_mode = this.create("Rainbow", "ChamsRainbow", false);
   OnePopSetting sat = this.create("Satiation", "ChamsSatiation", 0.8D, 0.0D, 1.0D);
   OnePopSetting brightness = this.create("Brightness", "ChamsBrightness", 0.8D, 0.0D, 1.0D);
   @EventHandler
   private final Listener<OnePopEventRenderEntity> renderingEntity = new Listener((event) -> {
      if (event.get_era() == OnePopEventCancellable.Era.EVENT_PRE) {
         if (this.verifyEntity(event.getEntity())) {
            if (this.mode.in("Wireframe")) {
               TurokRenderGL.pushMatrix();
               TurokRenderGL.disableState(3553);
               TurokRenderGL.disableState(2896);
               GL11.glPolygonMode(1032, 6913);
               TurokRenderGL.enableState(10754);
               TurokRenderGL.enableState(3042);
               TurokRenderGL.blendFunc(770, 771);
               TurokRenderGL.enableState(2848);
               TurokRenderGL.hint(3154, 4354);
               TurokRenderGL.lineSize((float)this.width.get_value(1.0D));
               TurokRenderGL.color(this.r.get_value(1), this.g.get_value(2), this.b.get_value(3), 255);
               GL11.glPolygonOffset(1.0F, -1100000.0F);
               TurokRenderGL.popMatrix();
            } else if (this.mode.in("FilhoDaPuta")) {
               TurokRenderGL.pushMatrix();
               TurokRenderGL.enableState(32823);
               GL11.glPolygonOffset(1.0F, -1100000.0F);
               TurokRenderGL.disableState(3553);
               TurokRenderGL.disableState(2896);
               TurokRenderGL.enableState(3042);
               TurokRenderGL.blendFunc(770, 771);
               TurokRenderGL.color(this.r.get_value(1), this.g.get_value(2), this.b.get_value(3), this.a.get_value(4));
               TurokRenderGL.popMatrix();
            }
         }
      } else if (event.get_era() == OnePopEventCancellable.Era.EVENT_POST && this.verifyEntity(event.getEntity())) {
         if (this.mode.in("Wireframe")) {
            TurokRenderGL.pushMatrix();
            TurokRenderGL.enableState(3553);
            TurokRenderGL.enableState(2896);
            TurokRenderGL.disableState(3042);
            TurokRenderGL.disableState(2848);
            GL11.glPolygonMode(1032, 6914);
            TurokRenderGL.disableState(10754);
            GL11.glPolygonOffset(1.0F, 1100000.0F);
            TurokRenderGL.color(this.r.get_value(1), this.g.get_value(2), this.b.get_value(3), 200);
            TurokRenderGL.popMatrix();
         } else if (this.mode.in("FilhoDaPuta")) {
            TurokRenderGL.pushMatrix();
            TurokRenderGL.disableState(32823);
            GL11.glPolygonOffset(1.0F, 1100000.0F);
            TurokRenderGL.enableState(3553);
            TurokRenderGL.enableState(2896);
            TurokRenderGL.disableState(3042);
            TurokRenderGL.color(this.r.get_value(1), this.g.get_value(2), this.b.get_value(3), this.a.get_value(4));
            TurokRenderGL.popMatrix();
         }
      }

   }, new Predicate[0]);

   public OnePopChams() {
      super(OnePopCategory.ONEPOP_RENDER);
      this.name = "Chams";
      this.tag = "Chams";
      this.description = "very epic ghosts";
   }

   public void update() {
      if (this.rainbow_mode.get_value(true)) {
         this.cycle_rainbow();
      }

   }

   public void cycle_rainbow() {
      float[] tick_color = new float[]{(float)(System.currentTimeMillis() % 11520L) / 11520.0F};
      int color_rgb_o = Color.HSBtoRGB(tick_color[0], (float)this.sat.get_value(1), (float)this.brightness.get_value(1));
      this.r.set_value(color_rgb_o >> 16 & 255);
      this.g.set_value(color_rgb_o >> 8 & 255);
      this.b.set_value(color_rgb_o & 255);
   }

   public boolean verifyEntity(Entity entity) {
      boolean verified = false;
      if (entity instanceof EntityPlayer && this.players.get_value(true)) {
         verified = true;
      }

      if (entity instanceof EntityMob && this.mobs.get_value(true)) {
         verified = true;
      }

      if (entity instanceof EntityAnimal && this.pigs_animals.get_value(true)) {
         verified = true;
      }

      if (this.self.get_value(true) && entity instanceof EntityPlayer && entity == mc.field_71439_g) {
         verified = true;
      }

      return verified;
   }

   public void on_render_model(OnePopEventRenderEntityModel event) {
      if (event.stage == 0 && event.entity != null && this.verifyEntity(event.entity)) {
         Color color = new Color(this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1));
         boolean fancyGraphics = mc.field_71474_y.field_74347_j;
         mc.field_71474_y.field_74347_j = false;
         float gamma = mc.field_71474_y.field_74333_Y;
         mc.field_71474_y.field_74333_Y = 10000.0F;
         if (this.top.get_value(true)) {
            event.modelBase.func_78088_a(event.entity, event.limbSwing, event.limbSwingAmount, event.age, event.headYaw, event.headPitch, event.scale);
         }

         if (this.mode.in("outline")) {
            OnePopRenderUtil.renderOne((float)this.width.get_value(1));
            event.modelBase.func_78088_a(event.entity, event.limbSwing, event.limbSwingAmount, event.age, event.headYaw, event.headPitch, event.scale);
            GlStateManager.func_187441_d((float)this.width.get_value(1));
            OnePopRenderUtil.renderTwo();
            event.modelBase.func_78088_a(event.entity, event.limbSwing, event.limbSwingAmount, event.age, event.headYaw, event.headPitch, event.scale);
            GlStateManager.func_187441_d((float)this.width.get_value(1));
            OnePopRenderUtil.renderThree();
            OnePopRenderUtil.renderFour(color);
            event.modelBase.func_78088_a(event.entity, event.limbSwing, event.limbSwingAmount, event.age, event.headYaw, event.headPitch, event.scale);
            GlStateManager.func_187441_d((float)this.width.get_value(1));
            OnePopRenderUtil.renderFive();
         } else if (this.mode.in("Pig")) {
            GL11.glPushMatrix();
            GL11.glPushAttrib(1048575);
            GL11.glPolygonMode(1028, 6913);
            GL11.glPolygonMode(1032, 6913);
            TurokRenderGL.enableState(10754);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glEnable(2848);
            GL11.glEnable(3042);
            GlStateManager.func_179112_b(770, 771);
            GlStateManager.func_179131_c((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)color.getAlpha());
            GlStateManager.func_187441_d((float)this.width.get_value(1));
            event.modelBase.func_78088_a(event.entity, event.limbSwing, event.limbSwingAmount, event.age, event.headYaw, event.headPitch, event.scale);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
         } else {
            GL11.glPushMatrix();
            GL11.glPushAttrib(1048575);
            GL11.glPolygonMode(1028, 6913);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glEnable(2848);
            GL11.glEnable(3042);
            GlStateManager.func_179112_b(770, 771);
            GlStateManager.func_179131_c((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)color.getAlpha());
            GlStateManager.func_187441_d((float)this.width.get_value(1));
            event.modelBase.func_78088_a(event.entity, event.limbSwing, event.limbSwingAmount, event.age, event.headYaw, event.headPitch, event.scale);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
         }

         if (!this.top.get_value(true)) {
            event.modelBase.func_78088_a(event.entity, event.limbSwing, event.limbSwingAmount, event.age, event.headYaw, event.headPitch, event.scale);
         }

         try {
            mc.field_71474_y.field_74347_j = fancyGraphics;
            mc.field_71474_y.field_74333_Y = gamma;
         } catch (Exception var6) {
         }

         event.cancel();
      }
   }
}
