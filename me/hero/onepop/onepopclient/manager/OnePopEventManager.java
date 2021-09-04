package me.hero.onepop.onepopclient.manager;

import java.util.Iterator;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.command.OnePopCommand;
import me.hero.onepop.onepopclient.command.OnePopCommands;
import me.hero.onepop.onepopclient.event.OnePopEventBus;
import me.hero.onepop.onepopclient.event.events.OnePopEventGameOverlay;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import me.hero.turok.draw.OldRenderHelp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class OnePopEventManager {
   private final Minecraft mc = Minecraft.func_71410_x();

   @SubscribeEvent
   public void onUpdate(LivingUpdateEvent event) {
      if (!event.isCanceled()) {
         ;
      }
   }

   @SubscribeEvent
   public void onTick(ClientTickEvent event) {
      if (this.mc.field_71439_g != null) {
         OnePop.get_hack_manager().update();
      }
   }

   @SubscribeEvent
   public void onWorldRender(RenderWorldLastEvent event) {
      if (!event.isCanceled()) {
         OnePop.get_hack_manager().render(event);
      }
   }

   @SubscribeEvent
   public void onRender(Post event) {
      if (!event.isCanceled()) {
         OnePopEventBus.EVENT_BUS.post(new OnePopEventGameOverlay(event.getPartialTicks(), new ScaledResolution(this.mc)));
         ElementType target = ElementType.EXPERIENCE;
         if (!this.mc.field_71439_g.func_184812_l_() && this.mc.field_71439_g.func_184187_bx() instanceof AbstractHorse) {
            target = ElementType.HEALTHMOUNT;
         }

         if (event.getType() == target) {
            OnePop.get_hack_manager().render();
            if (!OnePop.get_hack_manager().get_module_with_tag("GUI").is_active()) {
               OnePop.get_hud_manager().render();
            }

            GL11.glPushMatrix();
            GL11.glEnable(3553);
            GL11.glEnable(3042);
            GlStateManager.func_179147_l();
            GL11.glPopMatrix();
            OldRenderHelp.release_gl();
         }

      }
   }

   @SubscribeEvent(
      priority = EventPriority.NORMAL,
      receiveCanceled = true
   )
   public void onKeyInput(KeyInputEvent event) {
      if (Keyboard.getEventKeyState()) {
         OnePop.get_hack_manager().bind(Keyboard.getEventKey());
      }

   }

   @SubscribeEvent(
      priority = EventPriority.NORMAL
   )
   public void onChat(ClientChatEvent event) {
      String message = event.getMessage();
      String[] message_args = OnePopCommandManager.command_list.get_message(event.getMessage());
      boolean true_command = false;
      if (message_args.length > 0) {
         event.setCanceled(true);
         this.mc.field_71456_v.func_146158_b().func_146239_a(event.getMessage());
         Iterator var5 = OnePopCommands.get_pure_command_list().iterator();

         while(var5.hasNext()) {
            OnePopCommand command = (OnePopCommand)var5.next();

            try {
               if (OnePopCommandManager.command_list.get_message(event.getMessage())[0].equalsIgnoreCase(command.get_name())) {
                  true_command = command.get_message(OnePopCommandManager.command_list.get_message(event.getMessage()));
               }
            } catch (Exception var8) {
            }
         }

         if (!true_command && OnePopCommandManager.command_list.has_prefix(event.getMessage())) {
            OnePopMessageUtil.send_client_message("Try using " + OnePopCommandManager.get_prefix() + "help list to see all commands");
            true_command = false;
         }
      }

   }

   @SubscribeEvent
   public void onInputUpdate(InputUpdateEvent event) {
      OnePopEventBus.EVENT_BUS.post(event);
   }

   @SubscribeEvent
   public void onNoRender(RenderBlockOverlayEvent event) {
      OnePopEventBus.EVENT_BUS.post(event);
   }
}
