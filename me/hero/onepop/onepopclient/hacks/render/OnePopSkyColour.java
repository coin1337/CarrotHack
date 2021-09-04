package me.hero.onepop.onepopclient.hacks.render;

import java.awt.Color;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnePopSkyColour extends OnePopHack {
   OnePopSetting r = this.create("R", "SkyColourR", 255, 0, 255);
   OnePopSetting g = this.create("G", "SkyColourG", 255, 0, 255);
   OnePopSetting b = this.create("B", "SkyColourB", 255, 0, 255);
   OnePopSetting rainbow_mode = this.create("Rainbow", "SkyColourRainbow", false);

   public OnePopSkyColour() {
      super(OnePopCategory.ONEPOP_RENDER);
      this.name = "Sky Colour";
      this.tag = "SkyColour";
      this.description = "Changes the sky's colour";
   }

   @SubscribeEvent
   public void fog_colour(FogColors event) {
      event.setRed((float)this.r.get_value(1) / 255.0F);
      event.setGreen((float)this.g.get_value(1) / 255.0F);
      event.setBlue((float)this.b.get_value(1) / 255.0F);
   }

   @SubscribeEvent
   public void fog_density(FogDensity event) {
      event.setDensity(0.0F);
      event.setCanceled(true);
   }

   protected void enable() {
      MinecraftForge.EVENT_BUS.register(this);
   }

   protected void disable() {
      MinecraftForge.EVENT_BUS.unregister(this);
   }

   public void update() {
      if (this.rainbow_mode.get_value(true)) {
         this.cycle_rainbow();
      }

   }

   public void cycle_rainbow() {
      float[] tick_color = new float[]{(float)(System.currentTimeMillis() % 11520L) / 11520.0F};
      int color_rgb_o = Color.HSBtoRGB(tick_color[0], 0.8F, 0.8F);
      this.r.set_value(color_rgb_o >> 16 & 255);
      this.g.set_value(color_rgb_o >> 8 & 255);
      this.b.set_value(color_rgb_o & 255);
   }
}
