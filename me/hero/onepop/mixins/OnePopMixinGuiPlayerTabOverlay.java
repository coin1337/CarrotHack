package me.hero.onepop.mixins;

import java.util.List;
import me.hero.onepop.onepopclient.util.OnePopTabUtil;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetworkPlayerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({GuiPlayerTabOverlay.class})
public class OnePopMixinGuiPlayerTabOverlay {
   @Redirect(
      method = {"renderPlayerlist"},
      at = @At(
   value = "INVOKE",
   target = "Ljava/util/List;subList(II)Ljava/util/List;"
)
   )
   public List<NetworkPlayerInfo> subListHook(List<NetworkPlayerInfo> list, int fromIndex, int toIndex) {
      return 255 > list.size() ? list.subList(fromIndex, list.size()) : list.subList(fromIndex, 255);
   }

   @Inject(
      method = {"getPlayerName"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void getPlayerNameHook(NetworkPlayerInfo networkPlayerInfoIn, CallbackInfoReturnable<String> info) {
      info.setReturnValue(OnePopTabUtil.get_player_name(networkPlayerInfoIn));
   }
}
