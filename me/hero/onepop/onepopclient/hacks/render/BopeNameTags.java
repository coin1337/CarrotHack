package me.hero.onepop.onepopclient.hacks.render;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;
import me.hero.onepop.onepopclient.event.events.OnePopEventRender;
import me.hero.onepop.onepopclient.event.events.OnePopEventRenderName;
import me.hero.onepop.onepopclient.guiscreen.render.OnePopDraw;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopEntityUtil;
import me.hero.onepop.onepopclient.util.OnePopFriendUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class BopeNameTags extends OnePopHack {
   OnePopSetting name_ = this.create("Name", "NameTagName", true);
   OnePopSetting life_ = this.create("Health", "NameTagHealth", true);
   OnePopSetting ping_ = this.create("Ping", "NameTagPing", true);
   OnePopSetting armor = this.create("Armor", "NameTagArmor", true);
   OnePopSetting main_ = this.create("Main Hand", "NameTagMainHand", true);
   OnePopSetting off_h = this.create("Off Hand", "NameTagOffHand", true);
   OnePopSetting smot = this.create("Smooth", "NameTagSmooth", false);
   OnePopSetting range = this.create("Range", "NameTagRange", 200, 0, 200);
   OnePopSetting size = this.create("Size", "NameTagSize", 2.0D, 1.0D, 4.0D);
   OnePopSetting r = this.create("R", "Red", 0, 0, 255);
   OnePopSetting g = this.create("G", "Green", 0, 0, 255);
   OnePopSetting b = this.create("B", "Blue", 0, 0, 255);
   float partial_ticks = 0.0F;
   float uhadajsndiupa = 0.1F;
   OnePopDraw font = new OnePopDraw(1.0F);
   int CLEAR = 256;
   int MASK = 2929;
   @EventHandler
   private final Listener<OnePopEventRenderName> on_render_name = new Listener((event) -> {
      event.cancel();
   }, new Predicate[0]);

   public BopeNameTags() {
      super(OnePopCategory.ONEPOP_RENDER);
      this.name = "Bope NameTags";
      this.tag = "BopeNameTags";
      this.description = "rina don't kill me it just looks cool";
   }

   public void render(OnePopEventRender event) {
      if (mc.field_71439_g != null && mc.field_71441_e != null) {
         this.partial_ticks = event.get_partial_ticks();
         GlStateManager.func_179098_w();
         GlStateManager.func_179140_f();
         GlStateManager.func_179097_i();
         mc.field_71441_e.field_72996_f.stream().filter((entity) -> {
            return entity instanceof EntityLivingBase;
         }).filter((entity) -> {
            return entity != mc.field_71439_g;
         }).map((entity) -> {
            return (EntityLivingBase)entity;
         }).filter((entity) -> {
            return !entity.field_70128_L;
         }).filter((entity) -> {
            return entity instanceof EntityPlayer;
         }).filter((entity) -> {
            return mc.field_71439_g.func_70032_d(entity) < (float)this.range.get_value(1);
         }).sorted(Comparator.comparing((entity) -> {
            return -mc.field_71439_g.func_70032_d(entity);
         })).forEach(this::draw);
      }

      GlStateManager.func_179090_x();
      RenderHelper.func_74518_a();
      GlStateManager.func_179145_e();
      GlStateManager.func_179126_j();
   }

   public void draw(Entity entity) {
      if (mc.func_175598_ae().field_78733_k != null) {
         boolean smooth = this.smot.get_value(true);
         boolean person_view = mc.func_175598_ae().field_78733_k.field_74320_O == 2;
         float viewer_pitch = mc.func_175598_ae().field_78732_j;
         float viewer_yaw = mc.func_175598_ae().field_78735_i;
         String spac = " ";
         String name = this.name_.get_value(true) ? entity.func_70005_c_() : "";
         String life = this.life_.get_value(true) ? (this.name_.get_value(true) ? spac : "") + ChatFormatting.RED + Math.round(((EntityLivingBase)entity).func_110143_aJ() + (entity instanceof EntityPlayer ? ((EntityPlayer)entity).func_110139_bj() : 0.0F)) + ChatFormatting.RESET : "";
         String ping = this.ping_.get_value(true) ? ChatFormatting.BLUE + this.get_ping(entity) + ChatFormatting.RESET + spac : "";
         String tag = ping + name + life;
         GlStateManager.func_179094_E();
         Vec3d pos = OnePopEntityUtil.getInterpolatedRenderPos(entity, this.partial_ticks);
         double x = pos.field_72450_a;
         double y = pos.field_72448_b + (double)(entity.field_70131_O + this.uhadajsndiupa - (entity.func_70093_af() ? 0.25F : 0.0F));
         double z = pos.field_72449_c;
         GlStateManager.func_179137_b(x, y, z);
         GlStateManager.func_179114_b(-viewer_yaw, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179114_b((float)(person_view ? -1 : 1) * viewer_pitch, 1.0F, 0.0F, 0.0F);
         float distance = mc.field_71439_g.func_70032_d(entity);
         float scaling = distance >= 4.0F ? distance / 8.0F * (float)Math.pow(1.258925437927246D, (double)this.size.get_value(1)) : (float)Math.pow(1.258925437927246D, 0.5D);
         GlStateManager.func_179152_a(scaling, scaling, scaling);
         GlStateManager.func_179152_a(-0.025F, -0.025F, 0.025F);
         GlStateManager.func_179140_f();
         GlStateManager.func_179132_a(false);
         GL11.glDisable(this.MASK);
         int colapse_x = this.font.get_other_string_width(tag, smooth) / 2;
         this.draw_background(colapse_x);
         GlStateManager.func_179098_w();
         OnePopDraw var10000;
         if (OnePopFriendUtil.isFriend(entity.func_70005_c_())) {
            var10000 = this.font;
            OnePopDraw.draw_string(tag, -colapse_x, 10, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), 255);
         } else {
            var10000 = this.font;
            OnePopDraw.draw_string(tag, -colapse_x, 10, 190, 190, 190, 255);
         }

         if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            int off_set_x = -10;
            int off_set_y = -5;
            Iterator var24 = player.field_71071_by.field_70460_b.iterator();

            ItemStack armor_slot_item;
            while(var24.hasNext()) {
               armor_slot_item = (ItemStack)var24.next();
               if (armor_slot_item != null && this.armor.get_value(true)) {
                  off_set_x -= 8;
               }
            }

            ItemStack main_hand;
            if (player.func_184592_cb() != null && this.off_h.get_value(true)) {
               off_set_x -= 8;
               main_hand = player.func_184592_cb();
               this.render_item(main_hand, off_set_x, -off_set_y);
               off_set_x += 16;
            }

            for(int i = 0; i < 4; ++i) {
               armor_slot_item = (ItemStack)player.field_71071_by.field_70460_b.get(i);
               if (armor_slot_item != null && this.armor.get_value(true)) {
                  this.render_item(armor_slot_item, off_set_x, -off_set_y);
                  off_set_x += 16;
               }
            }

            if (player.func_184614_ca() != null && this.main_.get_value(true)) {
               off_set_x += 0;
               main_hand = player.func_184614_ca();
               this.render_item(main_hand, off_set_x, -off_set_y);
               off_set_x += 8;
            }
         }

         GlStateManager.func_187432_a(0.0F, 0.0F, 0.0F);
         GL11.glTranslatef(0.0F, 20.0F, 0.0F);
         GL11.glEnable(3553);
         GL11.glEnable(2929);
         GL11.glDepthMask(true);
         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.func_179152_a(-40.0F, -40.0F, 40.0F);
         GlStateManager.func_179126_j();
         GlStateManager.func_179121_F();
      }

   }

   public void render_item(ItemStack item, int x, int y) {
      GL11.glPushMatrix();
      GL11.glDepthMask(true);
      GlStateManager.func_179086_m(this.CLEAR);
      GlStateManager.func_179097_i();
      GlStateManager.func_179126_j();
      RenderHelper.func_74519_b();
      mc.func_175599_af().field_77023_b = -200.0F;
      GlStateManager.func_179152_a(1.0F, 1.0F, 0.01F);
      mc.func_175599_af().func_180450_b(item, x, y / 2 - 12);
      mc.func_175599_af().func_175030_a(mc.field_71466_p, item, x, y / 2 - 12 + 2);
      mc.func_175599_af().field_77023_b = 0.0F;
      GlStateManager.func_179152_a(1.0F, 1.0F, 1.0F);
      RenderHelper.func_74518_a();
      GlStateManager.func_179141_d();
      GlStateManager.func_179084_k();
      GlStateManager.func_179140_f();
      GlStateManager.func_179139_a(0.5D, 0.5D, 0.5D);
      GlStateManager.func_179097_i();
      GlStateManager.func_179126_j();
      GlStateManager.func_179152_a(2.0F, 2.0F, 2.0F);
      GL11.glPopMatrix();
   }

   public void draw_background(int colapse_x) {
      GlStateManager.func_179147_l();
      GlStateManager.func_187428_a(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
      GlStateManager.func_179090_x();
      Tessellator tessellator = Tessellator.func_178181_a();
      BufferBuilder bufferbuilder = tessellator.func_178180_c();
      GL11.glTranslatef(0.0F, -20.0F, 0.0F);
      bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
      bufferbuilder.func_181662_b((double)(-colapse_x - 1), 8.0D, 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.5F).func_181675_d();
      bufferbuilder.func_181662_b((double)(-colapse_x - 1), 19.0D, 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.5F).func_181675_d();
      bufferbuilder.func_181662_b((double)(colapse_x + 1), 19.0D, 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.5F).func_181675_d();
      bufferbuilder.func_181662_b((double)(colapse_x + 1), 8.0D, 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.5F).func_181675_d();
      tessellator.func_78381_a();
      bufferbuilder.func_181668_a(2, DefaultVertexFormats.field_181706_f);
      bufferbuilder.func_181662_b((double)(-colapse_x - 1), 8.0D, 0.0D).func_181666_a(0.1F, 0.1F, 0.1F, 0.1F).func_181675_d();
      bufferbuilder.func_181662_b((double)(-colapse_x - 1), 19.0D, 0.0D).func_181666_a(0.1F, 0.1F, 0.1F, 0.1F).func_181675_d();
      bufferbuilder.func_181662_b((double)(colapse_x + 1), 19.0D, 0.0D).func_181666_a(0.1F, 0.1F, 0.1F, 0.1F).func_181675_d();
      bufferbuilder.func_181662_b((double)(colapse_x + 1), 8.0D, 0.0D).func_181666_a(0.1F, 0.1F, 0.1F, 0.1F).func_181675_d();
      tessellator.func_78381_a();
   }

   public String get_ping(Entity entity) {
      String ping = "";
      if (entity instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)entity;

         try {
            ping = Integer.toString(mc.func_147114_u().func_175102_a(player.func_110124_au()).func_178853_c());
         } catch (Exception var5) {
         }
      }

      return ping;
   }
}
