package me.hero.onepop.onepopclient.command.commands;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import me.hero.onepop.onepopclient.command.OnePopCommand;
import me.hero.onepop.onepopclient.util.OnePopMessageUtil;
import net.minecraft.client.Minecraft;

public class OnePopClip extends OnePopCommand {
   public OnePopClip() {
      super("grab", "copy your coords to clipboard");
   }

   public boolean get_message(String[] message) {
      Minecraft mc = Minecraft.func_71410_x();
      String x = Integer.toString((int)mc.field_71439_g.field_70165_t);
      String z = Integer.toString((int)mc.field_71439_g.field_70161_v);
      StringSelection selection = new StringSelection(x + " " + z);
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      clipboard.setContents(selection, selection);
      OnePopMessageUtil.send_client_message("copied your coords to clipboard");
      return true;
   }
}
