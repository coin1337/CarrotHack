package me.hero.onepop.mixins;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.event.OnePopEventBus;
import me.hero.onepop.onepopclient.event.events.OnePopEventBlock;
import me.hero.onepop.onepopclient.event.events.OnePopEventDamageBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({PlayerControllerMP.class})
public class OnePopMixinPlayerControllerMP {
   @Redirect(
      method = {"onPlayerDamageBlock"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/block/state/IBlockState;getPlayerRelativeBlockHardness(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)F"
)
   )
   private float onPlayerDamageBlockSpeed(IBlockState state, EntityPlayer player, World world, BlockPos pos) {
      return state.func_185903_a(player, world, pos) * (OnePop.get_event_handler().get_tick_rate() / 20.0F);
   }

   @Inject(
      method = {"onPlayerDamageBlock"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void onPlayerDamageBlock(BlockPos posBlock, EnumFacing directionFacing, CallbackInfoReturnable<Boolean> info) {
      OnePopEventDamageBlock event_packet = new OnePopEventDamageBlock(posBlock, directionFacing);
      OnePopEventBus.EVENT_BUS.post(event_packet);
      if (event_packet.isCancelled()) {
         info.setReturnValue(false);
         info.cancel();
      }

      OnePopEventBlock event = new OnePopEventBlock(4, posBlock, directionFacing);
      OnePopEventBus.EVENT_BUS.post(event);
   }

   @Inject(
      method = {"clickBlock"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void clickBlockHook(BlockPos pos, EnumFacing face, CallbackInfoReturnable<Boolean> info) {
      OnePopEventBlock event = new OnePopEventBlock(3, pos, face);
      OnePopEventBus.EVENT_BUS.post(event);
   }
}
