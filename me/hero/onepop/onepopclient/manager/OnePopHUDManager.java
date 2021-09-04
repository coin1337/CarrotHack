package me.hero.onepop.onepopclient.manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopArmorDurabilityWarner;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopArmorPreview;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopArrayList;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopCompass;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopCoordinates;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopCrystalCount;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopEXPCount;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopEffectHud;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopEntityList;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopFPS;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopFriendList;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopGappleCount;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopInventoryPreview;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopInventoryXCarryPreview;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopPing;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopPlayerList;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopPvpHud;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopSpeedometer;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopSurroundBlocks;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopTPS;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopTime;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopTotemCount;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopUser;
import me.hero.onepop.onepopclient.guiscreen.hud.OnePopWatermark;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnable;

public class OnePopHUDManager {
   public static ArrayList<OnePopPinnable> array_hud = new ArrayList();

   public OnePopHUDManager() {
      this.add_component_pinnable(new OnePopCompass());
      this.add_component_pinnable(new OnePopWatermark());
      this.add_component_pinnable(new OnePopArrayList());
      this.add_component_pinnable(new OnePopCoordinates());
      this.add_component_pinnable(new OnePopInventoryPreview());
      this.add_component_pinnable(new OnePopInventoryXCarryPreview());
      this.add_component_pinnable(new OnePopArmorPreview());
      this.add_component_pinnable(new OnePopUser());
      this.add_component_pinnable(new OnePopTotemCount());
      this.add_component_pinnable(new OnePopCrystalCount());
      this.add_component_pinnable(new OnePopEXPCount());
      this.add_component_pinnable(new OnePopGappleCount());
      this.add_component_pinnable(new OnePopTime());
      this.add_component_pinnable(new OnePopFPS());
      this.add_component_pinnable(new OnePopPing());
      this.add_component_pinnable(new OnePopSurroundBlocks());
      this.add_component_pinnable(new OnePopFriendList());
      this.add_component_pinnable(new OnePopArmorDurabilityWarner());
      this.add_component_pinnable(new OnePopPvpHud());
      this.add_component_pinnable(new OnePopEffectHud());
      this.add_component_pinnable(new OnePopSpeedometer());
      this.add_component_pinnable(new OnePopEntityList());
      this.add_component_pinnable(new OnePopTPS());
      this.add_component_pinnable(new OnePopPlayerList());
      array_hud.sort(Comparator.comparing(OnePopPinnable::get_title));
   }

   public void add_component_pinnable(OnePopPinnable module) {
      array_hud.add(module);
   }

   public ArrayList<OnePopPinnable> get_array_huds() {
      return array_hud;
   }

   public void render() {
      Iterator var1 = this.get_array_huds().iterator();

      while(var1.hasNext()) {
         OnePopPinnable pinnables = (OnePopPinnable)var1.next();
         if (pinnables.is_active()) {
            pinnables.render();
         }
      }

   }

   public OnePopPinnable get_pinnable_with_tag(String tag) {
      OnePopPinnable pinnable_requested = null;
      Iterator var3 = this.get_array_huds().iterator();

      while(var3.hasNext()) {
         OnePopPinnable pinnables = (OnePopPinnable)var3.next();
         if (pinnables.get_tag().equalsIgnoreCase(tag)) {
            pinnable_requested = pinnables;
         }
      }

      return pinnable_requested;
   }
}
