package me.hero.onepop.mixins;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.event.OnePopEventBus;
import me.hero.onepop.onepopclient.event.events.OnePopEventGUIScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Minecraft.class})
public class OnePopMixinMinecraft {
   @Shadow
   public EntityPlayerSP field_71439_g;
   @Shadow
   public PlayerControllerMP field_71442_b;

   @Inject(
      method = {"displayGuiScreen"},
      at = {@At("HEAD")}
   )
   private void displayGuiScreen(GuiScreen guiScreenIn, CallbackInfo info) {
      OnePopEventGUIScreen guiscreen = new OnePopEventGUIScreen(guiScreenIn);
      OnePopEventBus.EVENT_BUS.post(guiscreen);
   }

   @Inject(
      method = {"shutdown"},
      at = {@At("HEAD")}
   )
   private void shutdown(CallbackInfo info) {
      OnePop.get_config_manager().save_settings();
   }

   @Redirect(
      method = {"sendClickBlockToController"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/entity/EntityPlayerSP;isHandActive()Z"
)
   )
   private boolean isHandActive(EntityPlayerSP player) {
      return OnePop.get_hack_manager().get_module_with_tag("Multitask").is_active() ? false : this.field_71439_g.func_184587_cr();
   }

   @Redirect(
      method = {"rightClickMouse"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getIsHittingBlock()Z"
)
   )
   private boolean isHittingBlock(PlayerControllerMP playerControllerMP) {
      return OnePop.get_hack_manager().get_module_with_tag("Multitask").is_active() ? false : this.field_71442_b.func_181040_m();
   }
}
