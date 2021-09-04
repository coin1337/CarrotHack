package me.hero.onepop.onepopclient.manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import me.hero.onepop.onepopclient.event.events.OnePopEventRender;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopClickGUI;
import me.hero.onepop.onepopclient.hacks.OnePopClickHUD;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.hacks.chat.OnePopAutoEz;
import me.hero.onepop.onepopclient.hacks.chat.OnePopBreakWarner;
import me.hero.onepop.onepopclient.hacks.chat.OnePopChatSuffix;
import me.hero.onepop.onepopclient.hacks.chat.OnePopClearChat;
import me.hero.onepop.onepopclient.hacks.chat.OnePopGlobalLocation;
import me.hero.onepop.onepopclient.hacks.chat.OnePopStrengthDetect;
import me.hero.onepop.onepopclient.hacks.chat.OnePopTotemAnnouncer;
import me.hero.onepop.onepopclient.hacks.chat.OnePopTotempop;
import me.hero.onepop.onepopclient.hacks.chat.OnePopVisualRange;
import me.hero.onepop.onepopclient.hacks.combat.OnePopAntiCrystal;
import me.hero.onepop.onepopclient.hacks.combat.OnePopAutoArmour;
import me.hero.onepop.onepopclient.hacks.combat.OnePopAutoCrystal;
import me.hero.onepop.onepopclient.hacks.combat.OnePopAutoTotem;
import me.hero.onepop.onepopclient.hacks.combat.OnePopAutoWeb;
import me.hero.onepop.onepopclient.hacks.combat.OnePopBedAura;
import me.hero.onepop.onepopclient.hacks.combat.OnePopCriticals;
import me.hero.onepop.onepopclient.hacks.combat.OnePopHoleFill;
import me.hero.onepop.onepopclient.hacks.combat.OnePopKillAura;
import me.hero.onepop.onepopclient.hacks.combat.OnePopOffhand;
import me.hero.onepop.onepopclient.hacks.combat.OnePopPacketXP;
import me.hero.onepop.onepopclient.hacks.combat.OnePopPhase;
import me.hero.onepop.onepopclient.hacks.combat.OnePopQuiver;
import me.hero.onepop.onepopclient.hacks.combat.OnePopSelfTrap;
import me.hero.onepop.onepopclient.hacks.combat.OnePopSocks;
import me.hero.onepop.onepopclient.hacks.combat.OnePopSurround;
import me.hero.onepop.onepopclient.hacks.combat.OnePopTrap;
import me.hero.onepop.onepopclient.hacks.combat.OnePopVelocity;
import me.hero.onepop.onepopclient.hacks.exploit.OnePopEntityMine;
import me.hero.onepop.onepopclient.hacks.exploit.OnePopNegroModule;
import me.hero.onepop.onepopclient.hacks.exploit.OnePopNoSwing;
import me.hero.onepop.onepopclient.hacks.exploit.OnePopPacketMine;
import me.hero.onepop.onepopclient.hacks.exploit.OnePopPortalGodMode;
import me.hero.onepop.onepopclient.hacks.exploit.OnePopScam;
import me.hero.onepop.onepopclient.hacks.exploit.OnePopSpeedTimer;
import me.hero.onepop.onepopclient.hacks.exploit.OnePopSpeedmine;
import me.hero.onepop.onepopclient.hacks.exploit.OnePopXCarry;
import me.hero.onepop.onepopclient.hacks.misc.OnePopAutoReplenish;
import me.hero.onepop.onepopclient.hacks.misc.OnePopAutoSwitchStop;
import me.hero.onepop.onepopclient.hacks.misc.OnePopFastUtil;
import me.hero.onepop.onepopclient.hacks.misc.OnePopFreecam;
import me.hero.onepop.onepopclient.hacks.misc.OnePopMiddleClickFriends;
import me.hero.onepop.onepopclient.hacks.misc.OnePopMultitask;
import me.hero.onepop.onepopclient.hacks.misc.OnePopOil;
import me.hero.onepop.onepopclient.hacks.misc.OnePopOldHit;
import me.hero.onepop.onepopclient.hacks.misc.OnePopRpc;
import me.hero.onepop.onepopclient.hacks.movement.OnePopBlink;
import me.hero.onepop.onepopclient.hacks.movement.OnePopBurrow;
import me.hero.onepop.onepopclient.hacks.movement.OnePopElytraFly;
import me.hero.onepop.onepopclient.hacks.movement.OnePopHoleTP;
import me.hero.onepop.onepopclient.hacks.movement.OnePopInventoryWalk;
import me.hero.onepop.onepopclient.hacks.movement.OnePopNoFall;
import me.hero.onepop.onepopclient.hacks.movement.OnePopNoSlow;
import me.hero.onepop.onepopclient.hacks.movement.OnePopScaffold;
import me.hero.onepop.onepopclient.hacks.movement.OnePopSprint;
import me.hero.onepop.onepopclient.hacks.movement.OnePopStep;
import me.hero.onepop.onepopclient.hacks.movement.OnePopStrafe;
import me.hero.onepop.onepopclient.hacks.movement.OnePopStrafeJump;
import me.hero.onepop.onepopclient.hacks.render.BopeNameTags;
import me.hero.onepop.onepopclient.hacks.render.OnePopAntifog;
import me.hero.onepop.onepopclient.hacks.render.OnePopBrightness;
import me.hero.onepop.onepopclient.hacks.render.OnePopCapes;
import me.hero.onepop.onepopclient.hacks.render.OnePopChams;
import me.hero.onepop.onepopclient.hacks.render.OnePopCityEsp;
import me.hero.onepop.onepopclient.hacks.render.OnePopFuckedDetector;
import me.hero.onepop.onepopclient.hacks.render.OnePopHighlight;
import me.hero.onepop.onepopclient.hacks.render.OnePopHoleESP;
import me.hero.onepop.onepopclient.hacks.render.OnePopNameTags;
import me.hero.onepop.onepopclient.hacks.render.OnePopNoRender;
import me.hero.onepop.onepopclient.hacks.render.OnePopShulkerPreview;
import me.hero.onepop.onepopclient.hacks.render.OnePopSkyColour;
import me.hero.onepop.onepopclient.hacks.render.OnePopTracers;
import me.hero.onepop.onepopclient.hacks.render.OnePopViewmodleChanger;
import me.hero.turok.draw.OldRenderHelp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class OnePopModuleManager {
   public static ArrayList<OnePopHack> array_hacks = new ArrayList();
   public static Minecraft mc = Minecraft.func_71410_x();

   public OnePopModuleManager() {
      this.add_hack(new OnePopClickGUI());
      this.add_hack(new OnePopClickHUD());
      this.add_hack(new OnePopGlobalLocation());
      this.add_hack(new OnePopChatSuffix());
      this.add_hack(new OnePopStrengthDetect());
      this.add_hack(new OnePopVisualRange());
      this.add_hack(new OnePopTotempop());
      this.add_hack(new OnePopClearChat());
      this.add_hack(new OnePopTotemAnnouncer());
      this.add_hack(new OnePopAutoEz());
      this.add_hack(new OnePopAntiCrystal());
      this.add_hack(new OnePopQuiver());
      this.add_hack(new OnePopBedAura());
      this.add_hack(new OnePopCriticals());
      this.add_hack(new OnePopKillAura());
      this.add_hack(new OnePopSurround());
      this.add_hack(new OnePopVelocity());
      this.add_hack(new OnePopAutoSwitchStop());
      this.add_hack(new OnePopAutoCrystal());
      this.add_hack(new OnePopHoleFill());
      this.add_hack(new OnePopTrap());
      this.add_hack(new OnePopSelfTrap());
      this.add_hack(new OnePopAutoArmour());
      this.add_hack(new OnePopAutoWeb());
      this.add_hack(new OnePopOffhand());
      this.add_hack(new OnePopAutoTotem());
      this.add_hack(new OnePopSocks());
      this.add_hack(new OnePopSpeedTimer());
      this.add_hack(new OnePopScam());
      this.add_hack(new OnePopXCarry());
      this.add_hack(new OnePopNoSwing());
      this.add_hack(new OnePopPortalGodMode());
      this.add_hack(new OnePopPacketMine());
      this.add_hack(new OnePopEntityMine());
      this.add_hack(new OnePopNegroModule());
      this.add_hack(new OnePopElytraFly());
      this.add_hack(new OnePopPhase());
      this.add_hack(new OnePopBurrow());
      this.add_hack(new OnePopScaffold());
      this.add_hack(new OnePopNoFall());
      this.add_hack(new OnePopNoSlow());
      this.add_hack(new OnePopStrafe());
      this.add_hack(new OnePopStrafeJump());
      this.add_hack(new OnePopStep());
      this.add_hack(new OnePopSprint());
      this.add_hack(new OnePopInventoryWalk());
      this.add_hack(new OnePopBlink());
      this.add_hack(new OnePopHoleTP());
      this.add_hack(new OnePopNameTags());
      this.add_hack(new BopeNameTags());
      this.add_hack(new OnePopNoRender());
      this.add_hack(new OnePopBreakWarner());
      this.add_hack(new OnePopHighlight());
      this.add_hack(new OnePopHoleESP());
      this.add_hack(new OnePopShulkerPreview());
      this.add_hack(new OnePopViewmodleChanger());
      this.add_hack(new OnePopAntifog());
      this.add_hack(new OnePopFuckedDetector());
      this.add_hack(new OnePopBrightness());
      this.add_hack(new OnePopTracers());
      this.add_hack(new OnePopSkyColour());
      this.add_hack(new OnePopChams());
      this.add_hack(new OnePopCapes());
      this.add_hack(new OnePopCityEsp());
      this.add_hack(new OnePopFreecam());
      this.add_hack(new OnePopPacketXP());
      this.add_hack(new OnePopOil());
      this.add_hack(new OnePopRpc());
      this.add_hack(new OnePopMultitask());
      this.add_hack(new OnePopMiddleClickFriends());
      this.add_hack(new OnePopOldHit());
      this.add_hack(new OnePopAutoReplenish());
      this.add_hack(new OnePopFastUtil());
      this.add_hack(new OnePopSpeedmine());
      array_hacks.sort(Comparator.comparing(OnePopHack::get_name));
   }

   public void add_hack(OnePopHack module) {
      array_hacks.add(module);
   }

   public ArrayList<OnePopHack> get_array_hacks() {
      return array_hacks;
   }

   public ArrayList<OnePopHack> get_array_active_hacks() {
      ArrayList<OnePopHack> actived_modules = new ArrayList();
      Iterator var2 = this.get_array_hacks().iterator();

      while(var2.hasNext()) {
         OnePopHack modules = (OnePopHack)var2.next();
         if (modules.is_active()) {
            actived_modules.add(modules);
         }
      }

      return actived_modules;
   }

   public Vec3d process(Entity entity, double x, double y, double z) {
      return new Vec3d((entity.field_70165_t - entity.field_70142_S) * x, (entity.field_70163_u - entity.field_70137_T) * y, (entity.field_70161_v - entity.field_70136_U) * z);
   }

   public Vec3d get_interpolated_pos(Entity entity, double ticks) {
      return (new Vec3d(entity.field_70142_S, entity.field_70137_T, entity.field_70136_U)).func_178787_e(this.process(entity, ticks, ticks, ticks));
   }

   public void render(RenderWorldLastEvent event) {
      mc.field_71424_I.func_76320_a("1pop");
      mc.field_71424_I.func_76320_a("setup");
      GlStateManager.func_179090_x();
      GlStateManager.func_179147_l();
      GlStateManager.func_179118_c();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179103_j(7425);
      GlStateManager.func_179097_i();
      GlStateManager.func_187441_d(1.0F);
      Vec3d pos = this.get_interpolated_pos(mc.field_71439_g, (double)event.getPartialTicks());
      OnePopEventRender event_render = new OnePopEventRender(OldRenderHelp.INSTANCE, pos);
      event_render.reset_translation();
      mc.field_71424_I.func_76319_b();
      Iterator var4 = this.get_array_hacks().iterator();

      while(var4.hasNext()) {
         OnePopHack modules = (OnePopHack)var4.next();
         if (modules.is_active()) {
            mc.field_71424_I.func_76320_a(modules.get_tag());
            modules.render(event_render);
            mc.field_71424_I.func_76319_b();
         }
      }

      mc.field_71424_I.func_76320_a("release");
      GlStateManager.func_187441_d(1.0F);
      GlStateManager.func_179103_j(7424);
      GlStateManager.func_179084_k();
      GlStateManager.func_179141_d();
      GlStateManager.func_179098_w();
      GlStateManager.func_179126_j();
      GlStateManager.func_179089_o();
      OldRenderHelp.release_gl();
      mc.field_71424_I.func_76319_b();
      mc.field_71424_I.func_76319_b();
   }

   public void update() {
      Iterator var1 = this.get_array_hacks().iterator();

      while(var1.hasNext()) {
         OnePopHack modules = (OnePopHack)var1.next();
         if (modules.is_active()) {
            modules.update();
         }
      }

   }

   public void render() {
      Iterator var1 = this.get_array_hacks().iterator();

      while(var1.hasNext()) {
         OnePopHack modules = (OnePopHack)var1.next();
         if (modules.is_active()) {
            modules.render();
         }
      }

   }

   public void bind(int event_key) {
      if (event_key != 0) {
         Iterator var2 = this.get_array_hacks().iterator();

         while(var2.hasNext()) {
            OnePopHack modules = (OnePopHack)var2.next();
            if (modules.get_bind(0) == event_key) {
               modules.toggle();
            }
         }

      }
   }

   public OnePopHack get_module_with_tag(String tag) {
      OnePopHack module_requested = null;
      Iterator var3 = this.get_array_hacks().iterator();

      while(var3.hasNext()) {
         OnePopHack module = (OnePopHack)var3.next();
         if (module.get_tag().equalsIgnoreCase(tag)) {
            module_requested = module;
         }
      }

      return module_requested;
   }

   public ArrayList<OnePopHack> get_modules_with_category(OnePopCategory category) {
      ArrayList<OnePopHack> module_requesteds = new ArrayList();
      Iterator var3 = this.get_array_hacks().iterator();

      while(var3.hasNext()) {
         OnePopHack modules = (OnePopHack)var3.next();
         if (modules.get_category().equals(category)) {
            module_requesteds.add(modules);
         }
      }

      return module_requesteds;
   }
}
