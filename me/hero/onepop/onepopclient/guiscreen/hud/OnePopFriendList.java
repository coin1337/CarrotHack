package me.hero.onepop.onepopclient.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Iterator;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnable;
import me.hero.onepop.onepopclient.util.OnePopOnlineFriends;
import net.minecraft.entity.Entity;

public class OnePopFriendList extends OnePopPinnable {
   int passes;
   public static ChatFormatting bold;

   public OnePopFriendList() {
      super("Friends", "Friends", 1.0F, 0, 0);
   }

   public void render() {
      int nl_r = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorR").get_value(1);
      int nl_g = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorG").get_value(1);
      int nl_b = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorB").get_value(1);
      int nl_a = OnePop.get_setting_manager().get_setting_with_tag("HUD", "HUDStringsColorA").get_value(1);
      String line1 = "gamers: ";
      this.passes = 0;
      this.create_line(line1, this.docking(1, line1), 2, nl_r, nl_g, nl_b, nl_a);
      if (!OnePopOnlineFriends.getFriends().isEmpty()) {
         Iterator var6 = OnePopOnlineFriends.getFriends().iterator();

         while(var6.hasNext()) {
            Entity e = (Entity)var6.next();
            ++this.passes;
            this.create_line(e.func_70005_c_(), this.docking(1, e.func_70005_c_()), this.get(line1, "height") * this.passes + 3, nl_r, nl_g, nl_b, nl_a);
         }
      }

      this.set_width(this.get(line1, "width") + 2);
      this.set_height(this.get(line1, "height") + 2);
   }

   static {
      bold = ChatFormatting.BOLD;
   }
}
