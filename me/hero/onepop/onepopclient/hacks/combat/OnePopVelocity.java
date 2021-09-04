package me.hero.onepop.onepopclient.hacks.combat;

import java.util.function.Predicate;
import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import me.hero.onepop.onepopclient.event.events.OnePopEventEntity;
import me.hero.onepop.onepopclient.event.events.OnePopEventPacket;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;

public class OnePopVelocity extends OnePopHack {
   @EventHandler
   private Listener<OnePopEventPacket.ReceivePacket> damage = new Listener((event) -> {
      if (event.get_era() == OnePopEventCancellable.Era.EVENT_PRE) {
         if (event.get_packet() instanceof SPacketEntityVelocity) {
            SPacketEntityVelocity knockback = (SPacketEntityVelocity)event.get_packet();
            if (knockback.func_149412_c() == mc.field_71439_g.func_145782_y()) {
               event.cancel();
               knockback.field_149415_b = (int)((float)knockback.field_149415_b * 0.0F);
               knockback.field_149416_c = (int)((float)knockback.field_149416_c * 0.0F);
               knockback.field_149414_d = (int)((float)knockback.field_149414_d * 0.0F);
            }
         } else if (event.get_packet() instanceof SPacketExplosion) {
            event.cancel();
            SPacketExplosion knockbackx = (SPacketExplosion)event.get_packet();
            knockbackx.field_149152_f *= 0.0F;
            knockbackx.field_149153_g *= 0.0F;
            knockbackx.field_149159_h *= 0.0F;
         }
      }

   }, new Predicate[0]);
   @EventHandler
   private Listener<OnePopEventEntity.OnePopEventColision> explosion = new Listener((event) -> {
      if (event.get_entity() == mc.field_71439_g) {
         event.cancel();
      }

   }, new Predicate[0]);

   public OnePopVelocity() {
      super(OnePopCategory.ONEPOP_COMBAT);
      this.name = "Velocity";
      this.tag = "Velocity";
      this.description = "No kockback";
   }
}
