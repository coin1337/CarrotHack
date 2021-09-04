package me.hero.onepop.onepopclient.hacks.misc;

import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;

public class OnePopRpc extends OnePopHack {
   OnePopSetting nigg = this.create("Show Name", "RPCShowName", true);

   public OnePopRpc() {
      super(OnePopCategory.ONEPOP_MISC);
      this.name = "Discord RPC";
      this.tag = "RPC";
      this.description = "shows that you have a very cool client";
   }

   public void enable() {
      OnePop.get_rpc().run();
   }

   public void disable() {
      OnePop.get_rpc().stop();
   }
}
