package me.hero.onepop.onepopclient.hacks.combat;

import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;

public class OnePopAutoTotem extends OnePopHack {
   OnePopSetting delay = this.create("Delay", "TotemDelay", false);
   private boolean switching = false;
   private int last_slot;

   public OnePopAutoTotem() {
      super(OnePopCategory.ONEPOP_COMBAT);
      this.name = "Auto Totem";
      this.tag = "AutoTotem";
      this.description = "put totem in offhand";
   }

   public void update() {
      if (mc.field_71462_r == null || mc.field_71462_r instanceof GuiInventory) {
         if (this.switching) {
            this.swap_items(this.last_slot, 2);
            return;
         }

         if (mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_190931_a) {
            this.swap_items(this.get_item_slot(), this.delay.get_value(true) ? 1 : 0);
         }
      }

   }

   private int get_item_slot() {
      if (Items.field_190929_cY == mc.field_71439_g.func_184592_cb().func_77973_b()) {
         return -1;
      } else {
         for(int i = 36; i >= 0; --i) {
            Item item = mc.field_71439_g.field_71071_by.func_70301_a(i).func_77973_b();
            if (item == Items.field_190929_cY) {
               if (i < 9) {
                  return -1;
               }

               return i;
            }
         }

         return -1;
      }
   }

   public void swap_items(int slot, int step) {
      if (slot != -1) {
         if (step == 0) {
            mc.field_71442_b.func_187098_a(0, slot, 0, ClickType.PICKUP, mc.field_71439_g);
            mc.field_71442_b.func_187098_a(0, 45, 0, ClickType.PICKUP, mc.field_71439_g);
            mc.field_71442_b.func_187098_a(0, slot, 0, ClickType.PICKUP, mc.field_71439_g);
         }

         if (step == 1) {
            mc.field_71442_b.func_187098_a(0, slot, 0, ClickType.PICKUP, mc.field_71439_g);
            this.switching = true;
            this.last_slot = slot;
         }

         if (step == 2) {
            mc.field_71442_b.func_187098_a(0, 45, 0, ClickType.PICKUP, mc.field_71439_g);
            mc.field_71442_b.func_187098_a(0, slot, 0, ClickType.PICKUP, mc.field_71439_g);
            this.switching = false;
         }

         mc.field_71442_b.func_78765_e();
      }
   }
}
