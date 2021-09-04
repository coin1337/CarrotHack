package me.hero.onepop.onepopclient.guiscreen.hud;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class OnePopPvpHud extends OnePopPinnable {
   public OnePopPvpHud() {
      super("PVP Hud", "pvphud", 1.0F, 0, 0);
   }

   public void render() {
      int nl_r = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
      int nl_g = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
      int nl_b = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);
      int nl_a = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorA").get_value(1);
      String totem = this.get_totems();
      String trap = this.trap_enabled();
      String aura = this.aura_enabled();
      String surround = this.surround_enabled();
      String holefill = this.holefill_enabled();
      String socks = this.socks_enabled();
      String selftrap = this.selftrap_enabled();
      String killaura = this.killaura_enabled();
      this.create_line(totem, this.docking(1, totem), 2, nl_r, nl_g, nl_b, nl_a);
      this.create_line(aura, this.docking(1, aura), 13, nl_r, nl_g, nl_b, nl_a);
      this.create_line(trap, this.docking(1, trap), 24, nl_r, nl_g, nl_b, nl_a);
      this.create_line(surround, this.docking(1, surround), 34, nl_r, nl_g, nl_b, nl_a);
      this.create_line(holefill, this.docking(1, holefill), 45, nl_r, nl_g, nl_b, nl_a);
      this.create_line(socks, this.docking(1, socks), 56, nl_r, nl_g, nl_b, nl_a);
      this.create_line(selftrap, this.docking(1, selftrap), 67, nl_r, nl_g, nl_b, nl_a);
      this.create_line(killaura, this.docking(1, killaura), 78, nl_r, nl_g, nl_b, nl_a);
      this.set_width(this.get(surround, "width") + 2);
      this.set_height(this.get(surround, "height") * 5);
   }

   public String selftrap_enabled() {
      try {
         return OnePop.get_hack_manager().get_module_with_tag("SelfTrap").is_active() ? "§aSelfTrap" : "§4SelfTrap";
      } catch (Exception var2) {
         return "0";
      }
   }

   public String trap_enabled() {
      try {
         return OnePop.get_hack_manager().get_module_with_tag("Trap").is_active() ? "§aTrap" : "§4Trap";
      } catch (Exception var2) {
         return "0";
      }
   }

   public String aura_enabled() {
      try {
         return OnePop.get_hack_manager().get_module_with_tag("AutoCrystal").is_active() ? "§aAura" : "§4Aura";
      } catch (Exception var2) {
         return "0";
      }
   }

   public String killaura_enabled() {
      try {
         return OnePop.get_hack_manager().get_module_with_tag("KillAura").is_active() ? "§aKillAura" : "§4KillAura";
      } catch (Exception var2) {
         return "0";
      }
   }

   public String socks_enabled() {
      try {
         return OnePop.get_hack_manager().get_module_with_tag("AntiCity").is_active() ? "§aAntiCity" : "§4AntiCity";
      } catch (Exception var2) {
         return "0";
      }
   }

   public String surround_enabled() {
      try {
         return OnePop.get_hack_manager().get_module_with_tag("Surround").is_active() ? "§aSurround" : "§4Surround";
      } catch (Exception var2) {
         return "0";
      }
   }

   public String holefill_enabled() {
      try {
         return OnePop.get_hack_manager().get_module_with_tag("HoleFill").is_active() ? "§aHoleFill" : "§4HoleFill";
      } catch (Exception var2) {
         return "0";
      }
   }

   public String get_totems() {
      try {
         int totems = this.offhand() + this.mc.field_71439_g.field_71071_by.field_70462_a.stream().filter((itemStack) -> {
            return itemStack.func_77973_b() == Items.field_190929_cY;
         }).mapToInt(ItemStack::func_190916_E).sum();
         return totems >= 1 ? "§aTotems " + totems : "§4Totems " + totems;
      } catch (Exception var2) {
         return "0";
      }
   }

   public int offhand() {
      return this.mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_190929_cY ? 1 : 0;
   }
}
