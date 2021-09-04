package me.hero.onepop.onepopclient.hacks.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;

public class OnePopStrengthDetect extends OnePopHack {
   private final Set<EntityPlayer> str = Collections.newSetFromMap(new WeakHashMap());
   public static final Minecraft mc = Minecraft.func_71410_x();

   public OnePopStrengthDetect() {
      super(OnePopCategory.ONEPOP_CHAT);
      this.name = "Strength Detect";
      this.tag = "StrengthDetect";
      this.description = "got yo ass drinking sum";
   }

   public void update() {
      Iterator var1 = mc.field_71441_e.field_73010_i.iterator();

      while(var1.hasNext()) {
         EntityPlayer player = (EntityPlayer)var1.next();
         if (!player.equals(mc.field_71439_g)) {
            if (player.func_70644_a(MobEffects.field_76420_g) && !this.str.contains(player)) {
               OnePopMessageUtil.send_client_message(ChatFormatting.RED + "" + ChatFormatting.BOLD + "StrengthDetect" + ChatFormatting.RESET + ChatFormatting.GRAY + " > " + ChatFormatting.RESET + player.getDisplayNameString() + " Has Strength");
               this.str.add(player);
            }

            if (this.str.contains(player) && !player.func_70644_a(MobEffects.field_76420_g)) {
               OnePopMessageUtil.send_client_message(ChatFormatting.RED + "" + ChatFormatting.BOLD + "StrengthDetect" + ChatFormatting.RESET + ChatFormatting.GRAY + " > " + ChatFormatting.RESET + player.getDisplayNameString() + " Has Ran Out Of Strength");
               this.str.remove(player);
            }
         }
      }

   }
}
