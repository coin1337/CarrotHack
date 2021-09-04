package me.hero.onepop.onepopclient.hacks.movement;

import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Predicate;
import me.hero.onepop.onepopclient.event.events.OnePopEventPacket;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketVehicleMove;

public class OnePopBlink extends OnePopHack {
   OnePopSetting visualize = this.create("Visualize", "BlinkVisualize", true);
   OnePopSetting entityblink = this.create("Entity Blink", "EntityBlink", true);
   private EntityOtherPlayerMP Original;
   private EntityDonkey RidingEntity;
   private final LinkedList<Packet> Packets = new LinkedList();
   @EventHandler
   private final Listener<OnePopEventPacket> packet_event = new Listener((event) -> {
      if (event.get_packet() instanceof CPacketPlayer || event.get_packet() instanceof CPacketConfirmTeleport || this.entityblink.get_value(true) && event.get_packet() instanceof CPacketVehicleMove) {
         event.cancel();
         this.Packets.add(event.get_packet());
      }

   }, new Predicate[0]);

   public OnePopBlink() {
      super(OnePopCategory.ONEPOP_MOVEMENT);
      this.name = "Blink";
      this.tag = "Blink";
      this.description = "ez.";
   }

   public void enable() {
      super.enable();
      this.Packets.clear();
      this.Original = null;
      this.RidingEntity = null;
      if (this.visualize.get_value(true)) {
         this.Original = new EntityOtherPlayerMP(mc.field_71441_e, mc.field_71449_j.func_148256_e());
         this.Original.func_82149_j(mc.field_71439_g);
         this.Original.field_70177_z = mc.field_71439_g.field_70177_z;
         this.Original.field_70759_as = mc.field_71439_g.field_70759_as;
         this.Original.field_71071_by.func_70455_b(mc.field_71439_g.field_71071_by);
         mc.field_71441_e.func_73027_a(-1048575, this.Original);
         if (mc.field_71439_g.func_184218_aH() && mc.field_71439_g.func_184187_bx() instanceof EntityDonkey) {
            EntityDonkey l_Original = (EntityDonkey)mc.field_71439_g.func_184187_bx();
            this.RidingEntity = new EntityDonkey(mc.field_71441_e);
            this.RidingEntity.func_82149_j(l_Original);
            this.RidingEntity.func_110207_m(l_Original.func_190695_dh());
            mc.field_71441_e.func_73027_a(-1048574, this.RidingEntity);
            this.Original.func_184205_a(this.RidingEntity, true);
         }
      }

   }

   public void disable() {
      super.disable();
      if (!this.Packets.isEmpty() && mc.field_71441_e != null) {
         while(!this.Packets.isEmpty()) {
            ((NetHandlerPlayClient)Objects.requireNonNull(mc.func_147114_u())).func_147297_a((Packet)this.Packets.getFirst());
            this.Packets.removeFirst();
         }
      }

      if (this.Original != null) {
         if (this.Original.func_184218_aH()) {
            this.Original.func_184210_p();
         }

         mc.field_71441_e.func_72900_e(this.Original);
      }

      if (this.RidingEntity != null) {
         assert mc.field_71441_e != null;

         mc.field_71441_e.func_72900_e(this.RidingEntity);
      }

   }
}
