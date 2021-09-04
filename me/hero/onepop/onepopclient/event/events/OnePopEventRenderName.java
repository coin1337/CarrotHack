package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import net.minecraft.client.entity.AbstractClientPlayer;

public class OnePopEventRenderName extends OnePopEventCancellable {
   public AbstractClientPlayer Entity;
   public double X;
   public double Y;
   public double Z;
   public String Name;
   public double DistanceSq;

   public OnePopEventRenderName(AbstractClientPlayer entityIn, double x, double y, double z, String name, double distanceSq) {
      this.Entity = entityIn;
      x = this.X;
      y = this.Y;
      z = this.Z;
      this.Name = name;
      this.DistanceSq = distanceSq;
   }
}
