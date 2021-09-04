package me.hero.onepop.onepopclient.hacks.misc;

import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class OnePopOldHit extends OnePopHack {
   OnePopSetting sword = this.create("Only Sword", "OnePopSwingOnlySword", false);

   public OnePopOldHit() {
      super(OnePopCategory.ONEPOP_MISC);
      this.name = "OldHit";
      this.tag = "OldHit";
      this.description = "1.8 vibes";
   }

   public void update() {
      EntityRenderer Entity;
      ItemRenderer Item;
      boolean boo;
      if (this.sword.get_value(true)) {
         label18: {
            EntityPlayerSP Sp = mc.field_71439_g;
            ItemStack Stack = Sp.func_184614_ca();
            if (Stack.func_77973_b() instanceof ItemSword) {
               Entity = mc.field_71460_t;
               Item = Entity.field_78516_c;
               if ((double)Item.field_187470_g >= 0.9D) {
                  boo = true;
                  break label18;
               }
            }

            ItemStack var70 = Sp.func_184614_ca();
            boo = false;
         }
      } else {
         boo = true;
      }

      if (boo) {
         Entity = mc.field_71460_t;
         Item = Entity.field_78516_c;
         Item.field_187469_f = 1.0F;
         Entity = mc.field_71460_t;
         Item = Entity.field_78516_c;
         EntityPlayerSP var10001 = mc.field_71439_g;
         Item.field_187467_d = var10001.func_184614_ca();
      }

   }
}
