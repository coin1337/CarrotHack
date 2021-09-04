package me.hero.onepop;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import java.util.Objects;
import me.hero.onepop.onepopclient.util.OnePopBlockInteractionHelper;
import me.hero.onepop.onepopclient.util.OnePopBlockUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;

public class OnePopDiscordPresence {
   public final DiscordRPC discord_rpc;
   public DiscordRichPresence discord_presence;
   public final Minecraft mc = Minecraft.func_71410_x();
   public String detail_option_1;
   public String detail_option_2;
   public String detail_option_3;
   public String detail_option_4;
   public String state_option_1;
   public String state_option_2;
   public String state_option_3;
   public String state_option_4;

   public OnePopDiscordPresence() {
      this.discord_rpc = DiscordRPC.INSTANCE;
      this.discord_presence = new DiscordRichPresence();
      this.detail_option_1 = "";
      this.detail_option_2 = "";
      this.detail_option_3 = "";
      this.detail_option_4 = "";
      this.state_option_1 = "";
      this.state_option_2 = "";
      this.state_option_3 = "";
      this.state_option_4 = "";
   }

   public void stop() {
      this.discord_rpc.Discord_Shutdown();
   }

   public void run() {
      this.discord_presence = new DiscordRichPresence();
      DiscordEventHandlers handler_ = new DiscordEventHandlers();
      this.discord_rpc.Discord_Initialize("519692561443717130", handler_, true, "");
      this.discord_presence.largeImageText = "1Pop 0.9.7";
      this.discord_presence.largeImageKey = "discord";
      (new Thread(() -> {
         while(!Thread.currentThread().isInterrupted()) {
            try {
               if (this.mc.field_71441_e == null) {
                  this.detail_option_1 = "";
                  this.detail_option_2 = "sleeping on da 1pop.club";
                  this.state_option_1 = "zzz";
               } else {
                  this.detail_option_1 = "";
                  if (this.mc.func_71387_A()) {
                     this.detail_option_2 = "1popping some nn's";
                     this.state_option_1 = "get 1popped kiddo";
                  } else if (OnePop.get_setting_manager().get_setting_with_tag("RPC", "RPCShowName").get_value(true)) {
                     this.detail_option_2 = "1popping at " + ((ServerData)Objects.requireNonNull(this.mc.func_147104_D())).field_78845_b;
                     this.state_option_1 = this.mc.field_71439_g.func_70005_c_() + " 1pop.club hitting p100";
                     OnePopBlockInteractionHelper.get_detail_measure();
                     if (OnePopBlockInteractionHelper.detail) {
                        this.discord_presence.largeImageText = OnePopBlockUtil.get_ca_target();
                     }
                  } else {
                     this.detail_option_2 = "1popping at " + ((ServerData)Objects.requireNonNull(this.mc.func_147104_D())).field_78845_b;
                     this.state_option_1 = "1pop.club hitting p100";
                     OnePopBlockInteractionHelper.get_detail_measure();
                     if (OnePopBlockInteractionHelper.detail) {
                        this.discord_presence.largeImageText = OnePopBlockUtil.get_ca_target();
                     }
                  }
               }

               String detail = this.detail_option_1 + this.detail_option_2 + this.detail_option_3 + this.detail_option_4;
               String state = this.state_option_1 + this.state_option_2 + this.state_option_3 + this.state_option_4;
               this.discord_rpc.Discord_RunCallbacks();
               this.discord_presence.details = detail;
               this.discord_presence.state = state;
               this.discord_rpc.Discord_UpdatePresence(this.discord_presence);
            } catch (Exception var4) {
               var4.printStackTrace();
            }

            try {
               Thread.sleep(4000L);
            } catch (InterruptedException var3) {
               var3.printStackTrace();
            }
         }

      }, "RPC-Callback-Handler")).start();
   }

   public String set(String presume) {
      return " " + presume;
   }
}
