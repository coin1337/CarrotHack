package me.hero.onepop.mixins;

import io.netty.channel.ChannelHandlerContext;
import me.hero.onepop.onepopclient.event.OnePopEventBus;
import me.hero.onepop.onepopclient.event.events.OnePopEventPacket;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({NetworkManager.class})
public class OnePopMixinNetworkManager {
   @Inject(
      method = {"channelRead0"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void receive(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callback) {
      OnePopEventPacket event_packet = new OnePopEventPacket.ReceivePacket(packet);
      OnePopEventBus.EVENT_BUS.post(event_packet);
      if (event_packet.isCancelled()) {
         callback.cancel();
      }

   }

   @Inject(
      method = {"sendPacket(Lnet/minecraft/network/Packet;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void send(Packet<?> packet, CallbackInfo callback) {
      OnePopEventPacket event_packet = new OnePopEventPacket.SendPacket(packet);
      OnePopEventBus.EVENT_BUS.post(event_packet);
      if (event_packet.isCancelled()) {
         callback.cancel();
      }

   }

   @Inject(
      method = {"exceptionCaught"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void exception(ChannelHandlerContext exc, Throwable exc_, CallbackInfo callback) {
      if (exc_ instanceof Exception) {
         callback.cancel();
      }

   }
}
