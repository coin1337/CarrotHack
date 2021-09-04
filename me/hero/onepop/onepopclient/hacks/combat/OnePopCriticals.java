package me.hero.onepop.onepopclient.hacks.combat;

import java.util.function.Predicate;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.event.events.OnePopEventPacket;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketPlayer.Position;
import net.minecraft.network.play.client.CPacketUseEntity.Action;

public class OnePopCriticals extends OnePopHack {
   OnePopSetting mode = this.create("Mode", "CriticalsMode", "Packet", this.combobox(new String[]{"Packet", "Jump"}));
   @EventHandler
   private Listener<OnePopEventPacket.SendPacket> listener = new Listener((event) -> {
      if (!OnePop.get_hack_manager().get_module_with_tag("AutoCrystal").is_active()) {
         if (event.get_packet() instanceof CPacketUseEntity) {
            CPacketUseEntity event_entity = (CPacketUseEntity)event.get_packet();
            if (event_entity.func_149565_c() == Action.ATTACK && mc.field_71439_g.field_70122_E) {
               if (this.mode.in("Packet")) {
                  mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.10000000149011612D, mc.field_71439_g.field_70161_v, false));
                  mc.field_71439_g.field_71174_a.func_147297_a(new Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v, false));
               } else if (this.mode.in("Jump")) {
                  mc.field_71439_g.func_70664_aZ();
               }
            }
         }

      }
   }, new Predicate[0]);

   public OnePopCriticals() {
      super(OnePopCategory.ONEPOP_COMBAT);
      this.name = "Criticals";
      this.tag = "Criticals";
      this.description = "You can hit with criticals when attack.";
   }

   public String array_detail() {
      return this.mode.get_current_value();
   }
}
