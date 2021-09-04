package me.hero.onepop.onepopclient.event.events;

import me.hero.onepop.onepopclient.event.OnePopEventCancellable;
import net.minecraft.network.Packet;

public class OnePopEventPacket extends OnePopEventCancellable {
   private final Packet packet;

   public OnePopEventPacket(Packet packet) {
      this.packet = packet;
   }

   public Packet get_packet() {
      return this.packet;
   }

   public static class SentPacket extends OnePopEventPacket {
      public SentPacket(Packet<?> packet) {
         super(packet);
      }
   }

   public static class SendPacket extends OnePopEventPacket {
      public SendPacket(Packet packet) {
         super(packet);
      }
   }

   public static class ReceivePacket extends OnePopEventPacket {
      public ReceivePacket(Packet packet) {
         super(packet);
      }
   }
}
