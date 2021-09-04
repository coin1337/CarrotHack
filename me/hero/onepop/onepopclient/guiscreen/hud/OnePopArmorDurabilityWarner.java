package me.hero.onepop.onepopclient.guiscreen.hud;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnable;
import net.minecraft.item.ItemStack;

public class OnePopArmorDurabilityWarner extends OnePopPinnable {
   public OnePopArmorDurabilityWarner() {
      super("Armor Warner", "ArmorWarner", 1.0F, 0, 0);
   }

   public void render() {
      String line;
      if (OnePop.get_setting_manager().get_setting_with_tag("GUI", "ClientLanguage").in("PT")) {
         line = "sua armadura vai quebrar :((";
      } else {
         line = "watch ur armor";
      }

      if (this.is_damaged()) {
         this.create_line(line, this.docking(1, line), 2, 255, 20, 20, 255);
      }

      this.set_width(this.get(line, "width") + 2);
      this.set_height(this.get(line, "height") + 2);
   }

   private boolean is_damaged() {
      Iterator var1 = this.get_armor().entrySet().iterator();

      while(var1.hasNext()) {
         Entry<Integer, ItemStack> armor_slot = (Entry)var1.next();
         if (!((ItemStack)armor_slot.getValue()).func_190926_b()) {
            ItemStack stack = (ItemStack)armor_slot.getValue();
            double max_dam = (double)stack.func_77958_k();
            double dam_left = (double)(stack.func_77958_k() - stack.func_77952_i());
            double percent = dam_left / max_dam * 100.0D;
            if (percent < 30.0D) {
               return true;
            }
         }
      }

      return false;
   }

   private Map<Integer, ItemStack> get_armor() {
      return this.get_inv_slots(5, 8);
   }

   private Map<Integer, ItemStack> get_inv_slots(int current, int last) {
      HashMap full_inv_slots;
      for(full_inv_slots = new HashMap(); current <= last; ++current) {
         full_inv_slots.put(current, (ItemStack)this.mc.field_71439_g.field_71069_bz.func_75138_a().get(current));
      }

      return full_inv_slots;
   }
}
