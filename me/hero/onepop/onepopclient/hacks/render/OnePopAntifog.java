package me.hero.onepop.onepopclient.hacks.render;

import java.util.function.Predicate;
import me.hero.onepop.onepopclient.event.events.OnePopEventSetupFog;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.renderer.GlStateManager;

public class OnePopAntifog extends OnePopHack {
   @EventHandler
   private Listener<OnePopEventSetupFog> setup_fog = new Listener((event) -> {
      event.cancel();
      mc.field_71460_t.func_191514_d(false);
      GlStateManager.func_187432_a(0.0F, -1.0F, 0.0F);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179104_a(1028, 4608);
   }, new Predicate[0]);

   public OnePopAntifog() {
      super(OnePopCategory.ONEPOP_RENDER);
      this.name = "Anti Fog";
      this.tag = "AntiFog";
      this.description = "see even more";
   }
}
