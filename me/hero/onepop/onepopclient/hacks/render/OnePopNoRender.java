package me.hero.onepop.onepopclient.hacks.render;

import java.util.function.Predicate;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.event.events.OnePopEventRenderArmorOverlay;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent.OverlayType;

public class OnePopNoRender extends OnePopHack {
   OnePopSetting armor = this.create("Armor", "Armor", true);
   OnePopSetting fire = this.create("Fire", "Fire", true);
   OnePopSetting hurtcam = this.create("HurtCam", "NoRenderNoHurtCam", true);
   @EventHandler
   private Listener<RenderBlockOverlayEvent> eventrender = new Listener((event) -> {
      if (this.fire.get_value(true) && event.getOverlayType() == OverlayType.FIRE) {
         event.setCanceled(true);
      }

   }, new Predicate[0]);
   @EventHandler
   private Listener<OnePopEventRenderArmorOverlay> eventarmor = new Listener((event) -> {
      if (this.armor.get_value(true) && event.entity instanceof EntityPlayer) {
         event.cancel();
      }

      if (OnePop.get_module_manager().get_module_with_tag("Chams").is_active()) {
         this.armor.set_value(true);
      }

   }, new Predicate[0]);

   public OnePopNoRender() {
      super(OnePopCategory.ONEPOP_RENDER);
      this.name = "No Render";
      this.tag = "NoRender";
      this.description = "não renderiza a cara feia do cheroso";
   }
}
