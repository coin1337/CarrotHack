package me.hero.onepop.onepopclient.manager;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import me.hero.onepop.OnePop;
import me.hero.onepop.onepopclient.guiscreen.render.components.OnePopFrame;
import me.hero.onepop.onepopclient.guiscreen.render.pinnables.OnePopPinnable;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.hero.onepop.onepopclient.util.OnePopCustomUserUtil;
import me.hero.onepop.onepopclient.util.OnePopDrawnUtil;
import me.hero.onepop.onepopclient.util.OnePopEnemyUtil;
import me.hero.onepop.onepopclient.util.OnePopEzMessageUtil;
import me.hero.onepop.onepopclient.util.OnePopFriendUtil;
import me.hero.onepop.onepopclient.util.OnePopEnemyUtil.Enemy;
import me.hero.onepop.onepopclient.util.OnePopFriendUtil.Friend;

public class OnePopConfigManager {
   private final String MAIN_FOLDER = "ONEPOPCLIENT/";
   private final String CONFIGS_FOLDER = "ONEPOPCLIENT/configs/";
   private String ACTIVE_CONFIG_FOLDER = "ONEPOPCLIENT/configs/default/";
   private final String CLIENT_FILE = "client.json";
   private final String CONFIG_FILE = "config.txt";
   private final String DRAWN_FILE = "drawn.txt";
   private final String JUMBEER_FILE = "jumbeer.txt";
   private final String CUSTOMUSER_FILE = "customuser.txt";
   private final String OFFHAND_FILE = "offhand.txt";
   private final String ENEMIES_FILE = "enemies.json";
   private final String FRIENDS_FILE = "friends.json";
   private final String HUD_FILE = "hud.json";
   private final String BINDS_FILE = "binds.txt";
   private final String CLIENT_DIR = "ONEPOPCLIENT/client.json";
   private final String CONFIG_DIR = "ONEPOPCLIENT/config.txt";
   private final String DRAWN_DIR = "ONEPOPCLIENT/drawn.txt";
   private final String JUMBEER = "ONEPOPCLIENT/jumbeer.txt";
   private final String CUSTOM = "ONEPOPCLIENT/customuser.txt";
   private final String OFFHAND = "ONEPOPCLIENT/offhand.txt";
   private final String ENEMIES_DIR = "ONEPOPCLIENT/enemies.json";
   private final String FRIENDS_DIR = "ONEPOPCLIENT/friends.json";
   private final String HUD_DIR = "ONEPOPCLIENT/hud.json";
   private String CURRENT_CONFIG_DIR;
   private String BINDS_DIR;
   private final Path MAIN_FOLDER_PATH;
   private final Path CONFIGS_FOLDER_PATH;
   private Path ACTIVE_CONFIG_FOLDER_PATH;
   private final Path CLIENT_PATH;
   private final Path CONFIG_PATH;
   private final Path DRAWN_PATH;
   private final Path JUMBEER_PATH;
   private final Path CUSTOM_PATH;
   private final Path OFFHAND_PATH;
   private final Path ENEMIES_PATH;
   private final Path FRIENDS_PATH;
   private final Path HUD_PATH;
   private Path BINDS_PATH;
   private Path CURRENT_CONFIG_PATH;

   public OnePopConfigManager() {
      this.CURRENT_CONFIG_DIR = "ONEPOPCLIENT/ONEPOPCLIENT/configs/" + this.ACTIVE_CONFIG_FOLDER;
      this.BINDS_DIR = this.CURRENT_CONFIG_DIR + "binds.txt";
      this.MAIN_FOLDER_PATH = Paths.get("ONEPOPCLIENT/");
      this.CONFIGS_FOLDER_PATH = Paths.get("ONEPOPCLIENT/configs/");
      this.ACTIVE_CONFIG_FOLDER_PATH = Paths.get(this.ACTIVE_CONFIG_FOLDER);
      this.CLIENT_PATH = Paths.get("ONEPOPCLIENT/client.json");
      this.CONFIG_PATH = Paths.get("ONEPOPCLIENT/config.txt");
      this.DRAWN_PATH = Paths.get("ONEPOPCLIENT/drawn.txt");
      this.JUMBEER_PATH = Paths.get("ONEPOPCLIENT/jumbeer.txt");
      this.CUSTOM_PATH = Paths.get("ONEPOPCLIENT/customuser.txt");
      this.OFFHAND_PATH = Paths.get("ONEPOPCLIENT/offhand.txt");
      this.ENEMIES_PATH = Paths.get("ONEPOPCLIENT/enemies.json");
      this.FRIENDS_PATH = Paths.get("ONEPOPCLIENT/friends.json");
      this.HUD_PATH = Paths.get("ONEPOPCLIENT/hud.json");
      this.BINDS_PATH = Paths.get(this.BINDS_DIR);
      this.CURRENT_CONFIG_PATH = Paths.get(this.CURRENT_CONFIG_DIR);
   }

   public boolean set_active_config_folder(String folder) {
      if (folder.equals(this.ACTIVE_CONFIG_FOLDER)) {
         return false;
      } else {
         this.ACTIVE_CONFIG_FOLDER = "ONEPOPCLIENT/configs/" + folder;
         this.ACTIVE_CONFIG_FOLDER_PATH = Paths.get(this.ACTIVE_CONFIG_FOLDER);
         this.CURRENT_CONFIG_DIR = "ONEPOPCLIENT/ONEPOPCLIENT/configs/" + this.ACTIVE_CONFIG_FOLDER;
         this.CURRENT_CONFIG_PATH = Paths.get(this.CURRENT_CONFIG_DIR);
         this.BINDS_DIR = this.CURRENT_CONFIG_DIR + "binds.txt";
         this.BINDS_PATH = Paths.get(this.BINDS_DIR);
         this.load_settings();
         return true;
      }
   }

   private void save_ezmessage() throws IOException {
      FileWriter writer = new FileWriter("ONEPOPCLIENT/jumbeer.txt");

      try {
         writer.write(OnePopEzMessageUtil.get_message());
      } catch (Exception var3) {
         writer.write("oi jumbeer");
      }

      writer.close();
   }

   private void save_customuser() throws IOException {
      FileWriter writer = new FileWriter("ONEPOPCLIENT/customuser.txt");

      try {
         writer.write(OnePopCustomUserUtil.get_message());
      } catch (Exception var3) {
         writer.write("oi cheroso faz o norender");
      }

      writer.close();
   }

   private void load_ezmessage() throws IOException {
      StringBuilder sb = new StringBuilder();
      Iterator var2 = Files.readAllLines(this.JUMBEER_PATH).iterator();

      while(var2.hasNext()) {
         String s = (String)var2.next();
         sb.append(s);
      }

      OnePopEzMessageUtil.set_message(sb.toString());
   }

   private void load_customuser() throws IOException {
      StringBuilder sb = new StringBuilder();
      Iterator var2 = Files.readAllLines(this.CUSTOM_PATH).iterator();

      while(var2.hasNext()) {
         String s = (String)var2.next();
         sb.append(s);
      }

      OnePopCustomUserUtil.set_message(sb.toString());
   }

   private void save_drawn() throws IOException {
      FileWriter writer = new FileWriter("ONEPOPCLIENT/drawn.txt");
      Iterator var2 = OnePopDrawnUtil.hidden_tags.iterator();

      while(var2.hasNext()) {
         String s = (String)var2.next();
         writer.write(s + System.lineSeparator());
      }

      writer.close();
   }

   private void load_drawn() throws IOException {
      OnePopDrawnUtil.hidden_tags = Files.readAllLines(this.DRAWN_PATH);
   }

   private void save_friends() throws IOException {
      Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
      String json = gson.toJson(OnePopFriendUtil.friends);
      OutputStreamWriter file = new OutputStreamWriter(new FileOutputStream("ONEPOPCLIENT/friends.json"), StandardCharsets.UTF_8);
      file.write(json);
      file.close();
   }

   private void load_friends() throws IOException {
      Gson gson = new Gson();
      Reader reader = Files.newBufferedReader(Paths.get("ONEPOPCLIENT/friends.json"));
      OnePopFriendUtil.friends = (ArrayList)gson.fromJson(reader, (new TypeToken<ArrayList<Friend>>() {
      }).getType());
      reader.close();
   }

   private void save_enemies() throws IOException {
      Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
      String json = gson.toJson(OnePopEnemyUtil.enemies);
      OutputStreamWriter file = new OutputStreamWriter(new FileOutputStream("ONEPOPCLIENT/enemies.json"), StandardCharsets.UTF_8);
      file.write(json);
      file.close();
   }

   private void load_enemies() throws IOException {
      Gson gson = new Gson();
      Reader reader = Files.newBufferedReader(Paths.get("ONEPOPCLIENT/enemies.json"));
      OnePopEnemyUtil.enemies = (ArrayList)gson.fromJson(reader, (new TypeToken<ArrayList<Enemy>>() {
      }).getType());
      reader.close();
   }

   private void save_hacks() throws IOException {
      Iterator var1 = OnePop.get_hack_manager().get_array_hacks().iterator();

      while(var1.hasNext()) {
         OnePopHack hack = (OnePopHack)var1.next();
         String file_name = this.ACTIVE_CONFIG_FOLDER + hack.get_tag() + ".txt";
         Path file_path = Paths.get(file_name);
         this.delete_file(file_name);
         this.verify_file(file_path);
         File file = new File(file_name);
         BufferedWriter br = new BufferedWriter(new FileWriter(file));
         Iterator var7 = OnePop.get_setting_manager().get_settings_with_hack(hack).iterator();

         while(var7.hasNext()) {
            OnePopSetting setting = (OnePopSetting)var7.next();
            String var9 = setting.get_type();
            byte var10 = -1;
            switch(var9.hashCode()) {
            case -1377687758:
               if (var9.equals("button")) {
                  var10 = 0;
               }
               break;
            case -612288131:
               if (var9.equals("combobox")) {
                  var10 = 1;
               }
               break;
            case -151809121:
               if (var9.equals("integerslider")) {
                  var10 = 4;
               }
               break;
            case 102727412:
               if (var9.equals("label")) {
                  var10 = 2;
               }
               break;
            case 1954943986:
               if (var9.equals("doubleslider")) {
                  var10 = 3;
               }
            }

            switch(var10) {
            case 0:
               br.write(setting.get_tag() + ":" + setting.get_value(true) + "\r\n");
               break;
            case 1:
               br.write(setting.get_tag() + ":" + setting.get_current_value() + "\r\n");
               break;
            case 2:
               br.write(setting.get_tag() + ":" + setting.get_value("") + "\r\n");
               break;
            case 3:
               br.write(setting.get_tag() + ":" + setting.get_value(1.0D) + "\r\n");
               break;
            case 4:
               br.write(setting.get_tag() + ":" + setting.get_value(1) + "\r\n");
            }
         }

         br.close();
      }

   }

   private void load_hacks() throws IOException {
      BufferedReader br;
      for(Iterator var1 = OnePop.get_hack_manager().get_array_hacks().iterator(); var1.hasNext(); br.close()) {
         OnePopHack hack = (OnePopHack)var1.next();
         String file_name = this.ACTIVE_CONFIG_FOLDER + hack.get_tag() + ".txt";
         File file = new File(file_name);
         FileInputStream fi_stream = new FileInputStream(file.getAbsolutePath());
         DataInputStream di_stream = new DataInputStream(fi_stream);
         br = new BufferedReader(new InputStreamReader(di_stream));
         ArrayList bugged_lines = new ArrayList();

         String line;
         while((line = br.readLine()) != null) {
            try {
               String colune = line.trim();
               String tag = colune.split(":")[0];
               String value = colune.split(":")[1];
               OnePopSetting setting = OnePop.get_setting_manager().get_setting_with_tag(hack, tag);

               try {
                  String var14 = setting.get_type();
                  byte var15 = -1;
                  switch(var14.hashCode()) {
                  case -1377687758:
                     if (var14.equals("button")) {
                        var15 = 0;
                     }
                     break;
                  case -612288131:
                     if (var14.equals("combobox")) {
                        var15 = 4;
                     }
                     break;
                  case -151809121:
                     if (var14.equals("integerslider")) {
                        var15 = 3;
                     }
                     break;
                  case 102727412:
                     if (var14.equals("label")) {
                        var15 = 1;
                     }
                     break;
                  case 1954943986:
                     if (var14.equals("doubleslider")) {
                        var15 = 2;
                     }
                  }

                  switch(var15) {
                  case 0:
                     setting.set_value(Boolean.parseBoolean(value));
                     break;
                  case 1:
                     setting.set_value(value);
                     break;
                  case 2:
                     setting.set_value(Double.parseDouble(value));
                     break;
                  case 3:
                     setting.set_value(Integer.parseInt(value));
                     break;
                  case 4:
                     setting.set_current_value(value);
                  }
               } catch (Exception var16) {
                  bugged_lines.add(colune);
                  OnePop.send_minecraft_log("Error loading '" + value + "' to setting '" + tag + "'");
                  break;
               }
            } catch (Exception var17) {
               var17.printStackTrace();
            }
         }
      }

   }

   private void save_client() throws IOException {
      Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
      JsonParser parser = new JsonParser();
      JsonObject main_json = new JsonObject();
      JsonObject config = new JsonObject();
      JsonObject gui = new JsonObject();
      config.add("name", new JsonPrimitive(OnePop.get_name()));
      config.add("version", new JsonPrimitive(OnePop.get_version()));
      config.add("user", new JsonPrimitive(OnePop.get_actual_user()));
      config.add("prefix", new JsonPrimitive(OnePopCommandManager.get_prefix()));
      Iterator var6 = OnePop.click_gui.get_array_frames().iterator();

      while(var6.hasNext()) {
         OnePopFrame frames_gui = (OnePopFrame)var6.next();
         JsonObject frame_info = new JsonObject();
         frame_info.add("name", new JsonPrimitive(frames_gui.get_name()));
         frame_info.add("tag", new JsonPrimitive(frames_gui.get_tag()));
         frame_info.add("x", new JsonPrimitive(frames_gui.get_x()));
         frame_info.add("y", new JsonPrimitive(frames_gui.get_y()));
         gui.add(frames_gui.get_tag(), frame_info);
      }

      main_json.add("configuration", config);
      main_json.add("gui", gui);
      JsonElement json_pretty = parser.parse(main_json.toString());
      String json = gson.toJson(json_pretty);
      OutputStreamWriter file = new OutputStreamWriter(new FileOutputStream("ONEPOPCLIENT/client.json"), StandardCharsets.UTF_8);
      file.write(json);
      file.close();
   }

   private void load_client() throws IOException {
      InputStream stream = Files.newInputStream(this.CLIENT_PATH);
      JsonObject json_client = (new JsonParser()).parse(new InputStreamReader(stream)).getAsJsonObject();
      JsonObject json_config = json_client.get("configuration").getAsJsonObject();
      JsonObject json_gui = json_client.get("gui").getAsJsonObject();
      OnePopCommandManager.set_prefix(json_config.get("prefix").getAsString());
      Iterator var5 = OnePop.click_gui.get_array_frames().iterator();

      while(var5.hasNext()) {
         OnePopFrame frames = (OnePopFrame)var5.next();
         JsonObject frame_info = json_gui.get(frames.get_tag()).getAsJsonObject();
         OnePopFrame frame_requested = OnePop.click_gui.get_frame_with_tag(frame_info.get("tag").getAsString());
         frame_requested.set_x(frame_info.get("x").getAsInt());
         frame_requested.set_y(frame_info.get("y").getAsInt());
      }

      stream.close();
   }

   private void save_hud() throws IOException {
      Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
      JsonParser parser = new JsonParser();
      JsonObject main_json = new JsonObject();
      JsonObject main_frame = new JsonObject();
      JsonObject main_hud = new JsonObject();
      main_frame.add("name", new JsonPrimitive(OnePop.click_hud.get_frame_hud().get_name()));
      main_frame.add("tag", new JsonPrimitive(OnePop.click_hud.get_frame_hud().get_tag()));
      main_frame.add("x", new JsonPrimitive(OnePop.click_hud.get_frame_hud().get_x()));
      main_frame.add("y", new JsonPrimitive(OnePop.click_hud.get_frame_hud().get_y()));
      Iterator var6 = OnePop.get_hud_manager().get_array_huds().iterator();

      while(var6.hasNext()) {
         OnePopPinnable pinnables_hud = (OnePopPinnable)var6.next();
         JsonObject frame_info = new JsonObject();
         frame_info.add("title", new JsonPrimitive(pinnables_hud.get_title()));
         frame_info.add("tag", new JsonPrimitive(pinnables_hud.get_tag()));
         frame_info.add("state", new JsonPrimitive(pinnables_hud.is_active()));
         frame_info.add("dock", new JsonPrimitive(pinnables_hud.get_dock()));
         frame_info.add("x", new JsonPrimitive(pinnables_hud.get_x()));
         frame_info.add("y", new JsonPrimitive(pinnables_hud.get_y()));
         main_hud.add(pinnables_hud.get_tag(), frame_info);
      }

      main_json.add("frame", main_frame);
      main_json.add("hud", main_hud);
      JsonElement pretty_json = parser.parse(main_json.toString());
      String json = gson.toJson(pretty_json);
      this.delete_file("ONEPOPCLIENT/hud.json");
      this.verify_file(this.HUD_PATH);
      OutputStreamWriter file = new OutputStreamWriter(new FileOutputStream("ONEPOPCLIENT/hud.json"), StandardCharsets.UTF_8);
      file.write(json);
      file.close();
   }

   private void load_hud() throws IOException {
      InputStream input_stream = Files.newInputStream(this.HUD_PATH);
      JsonObject main_hud = (new JsonParser()).parse(new InputStreamReader(input_stream)).getAsJsonObject();
      JsonObject main_frame = main_hud.get("frame").getAsJsonObject();
      JsonObject main_huds = main_hud.get("hud").getAsJsonObject();
      OnePop.click_hud.get_frame_hud().set_x(main_frame.get("x").getAsInt());
      OnePop.click_hud.get_frame_hud().set_y(main_frame.get("y").getAsInt());
      Iterator var5 = OnePop.get_hud_manager().get_array_huds().iterator();

      while(var5.hasNext()) {
         OnePopPinnable pinnables = (OnePopPinnable)var5.next();
         JsonObject hud_info = main_huds.get(pinnables.get_tag()).getAsJsonObject();
         OnePopPinnable pinnable_requested = OnePop.get_hud_manager().get_pinnable_with_tag(hud_info.get("tag").getAsString());
         pinnable_requested.set_active(hud_info.get("state").getAsBoolean());
         pinnable_requested.set_dock(hud_info.get("dock").getAsBoolean());
         pinnable_requested.set_x(hud_info.get("x").getAsInt());
         pinnable_requested.set_y(hud_info.get("y").getAsInt());
      }

      input_stream.close();
   }

   private void save_binds() throws IOException {
      String file_name = this.ACTIVE_CONFIG_FOLDER + "BINDS.txt";
      Path file_path = Paths.get(file_name);
      this.delete_file(file_name);
      this.verify_file(file_path);
      File file = new File(file_name);
      BufferedWriter br = new BufferedWriter(new FileWriter(file));
      Iterator var5 = OnePop.get_hack_manager().get_array_hacks().iterator();

      while(var5.hasNext()) {
         OnePopHack modules = (OnePopHack)var5.next();
         br.write(modules.get_tag() + ":" + modules.get_bind(1) + ":" + modules.is_active() + "\r\n");
      }

      br.close();
   }

   private void load_binds() throws IOException {
      String file_name = this.ACTIVE_CONFIG_FOLDER + "BINDS.txt";
      File file = new File(file_name);
      FileInputStream fi_stream = new FileInputStream(file.getAbsolutePath());
      DataInputStream di_stream = new DataInputStream(fi_stream);
      BufferedReader br = new BufferedReader(new InputStreamReader(di_stream));

      String line;
      while((line = br.readLine()) != null) {
         try {
            String colune = line.trim();
            String tag = colune.split(":")[0];
            String bind = colune.split(":")[1];
            String active = colune.split(":")[2];
            OnePopHack module = OnePop.get_hack_manager().get_module_with_tag(tag);
            module.set_bind(Integer.parseInt(bind));
            module.set_active(Boolean.parseBoolean(active));
         } catch (Exception var12) {
         }
      }

      br.close();
   }

   public void save_settings() {
      try {
         this.verify_dir(this.MAIN_FOLDER_PATH);
         this.verify_dir(this.CONFIGS_FOLDER_PATH);
         this.verify_dir(this.ACTIVE_CONFIG_FOLDER_PATH);
         this.save_hacks();
         this.save_binds();
         this.save_friends();
         this.save_enemies();
         this.save_client();
         this.save_drawn();
         this.save_ezmessage();
         this.save_customuser();
         this.save_hud();
      } catch (IOException var2) {
         OnePop.send_minecraft_log("Something has gone wrong while saving settings");
         OnePop.send_minecraft_log(var2.toString());
      }

   }

   public void load_settings() {
      try {
         this.load_binds();
         this.load_client();
         this.load_drawn();
         this.load_enemies();
         this.load_ezmessage();
         this.load_customuser();
         this.load_friends();
         this.load_hacks();
         this.load_hud();
      } catch (IOException var2) {
         OnePop.send_minecraft_log("Something has gone wrong while loading settings");
         OnePop.send_minecraft_log(var2.toString());
      }

   }

   public boolean delete_file(String path) throws IOException {
      File f = new File(path);
      return f.delete();
   }

   public void verify_file(Path path) throws IOException {
      if (!Files.exists(path, new LinkOption[0])) {
         Files.createFile(path);
      }

   }

   public void verify_dir(Path path) throws IOException {
      if (!Files.exists(path, new LinkOption[0])) {
         Files.createDirectory(path);
      }

   }
}
