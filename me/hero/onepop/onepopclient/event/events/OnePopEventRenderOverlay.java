package me.hero.onepop.onepopclient.event.events;

import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class OnePopEventRenderOverlay {
   RenderGameOverlayEvent event;

   public OnePopEventRenderOverlay(RenderGameOverlayEvent event) {
      this.event = event;
   }

   protected void set_event(RenderGameOverlayEvent event) {
      this.event = event;
   }

   public RenderGameOverlayEvent get_event() {
      return this.event;
   }
}
