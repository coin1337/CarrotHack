package me.hero.onepop.onepopclient.hacks;

public enum OnePopCategory {
   ONEPOP_CHAT("Chat", "Chat", false),
   ONEPOP_COMBAT("Combat", "Combat", false),
   ONEPOP_MOVEMENT("Movement", "Movement", false),
   ONEPOP_RENDER("Render", "Render", false),
   ONEPOP_EXPLOIT("Exploit", "Exploit", false),
   ONEPOP_MISC("Misc", "Misc", false),
   ONEPOP_GUI("GUI", "GUI", false),
   ONEPOP_BETA("Beta", "Beta", true),
   ONEPOP_HIDDEN("Hidden", "Hidden", true);

   String name;
   String tag;
   boolean hidden;

   private OnePopCategory(String name, String tag, boolean hidden) {
      this.name = name;
      this.tag = tag;
      this.hidden = hidden;
   }

   public boolean is_hidden() {
      return this.hidden;
   }

   public String get_name() {
      return this.name;
   }

   public String get_tag() {
      return this.tag;
   }
}
