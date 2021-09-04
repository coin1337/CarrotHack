package me.hero.onepop.onepopclient.hacks.render;

import java.util.function.Predicate;
import me.hero.onepop.onepopclient.event.events.TransformSideFirstPersonEvent;
import me.hero.onepop.onepopclient.guiscreen.settings.OnePopSetting;
import me.hero.onepop.onepopclient.hacks.OnePopCategory;
import me.hero.onepop.onepopclient.hacks.OnePopHack;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

public class OnePopViewmodleChanger extends OnePopHack {
   OnePopSetting custom_fov = this.create("FOV", "FOVSlider", 130, 10, 170);
   OnePopSetting cancelEating = this.create("No Eat", "NoEat", false);
   OnePopSetting xRight = this.create("Right X", "RightX", 0.0D, -50.0D, 50.0D);
   OnePopSetting yRight = this.create("Right Y", "RightY", 0.0D, -50.0D, 50.0D);
   OnePopSetting zRight = this.create("Right Z", "RightZ", 0.0D, -50.0D, 50.0D);
   OnePopSetting scaleRight = this.create("Scale Right", "ScaleRight", 10.0D, 0.0D, 50.0D);
   OnePopSetting rotateRightX = this.create("Rotate Right X", "RotateRightX", 0, -360, 360);
   OnePopSetting rotateRightY = this.create("Rotate Right Y", "RotateRightY", 0, -360, 360);
   OnePopSetting rotateRightZ = this.create("Rotate Right Z", "RotateRightZ", 0, -360, 360);
   OnePopSetting scaleLeft = this.create("Scale Left", "ScaleLeft", 10.0D, 0.0D, 50.0D);
   OnePopSetting xLeft = this.create("Left X", "LeftX", 0.0D, -50.0D, 50.0D);
   OnePopSetting yLeft = this.create("Left Y", "LeftY", 0.0D, -50.0D, 50.0D);
   OnePopSetting zLeft = this.create("Left Z", "LeftZ", 0.0D, -50.0D, 50.0D);
   OnePopSetting rotateLeftX = this.create("Rotate Left X", "RotateLeftX", 0, -360, 360);
   OnePopSetting rotateLeftY = this.create("Rotate Left Y", "RotateLeftY", 0, -360, 360);
   OnePopSetting rotateLeftZ = this.create("Rotate Left Z", "RotateLeftZ", 0, -360, 360);
   private float fov;
   @EventHandler
   private final Listener<TransformSideFirstPersonEvent> eventListener = new Listener((event) -> {
      if (event.getHandSide() == EnumHandSide.RIGHT) {
         GL11.glTranslatef((float)this.xRight.get_value(1.0D) / 100.0F, (float)this.yRight.get_value(1.0D) / 100.0F, (float)this.zRight.get_value(1.0D) / 100.0F);
         GL11.glRotatef((float)this.rotateRightX.get_value(1.0D), 1.0F, 0.0F, 0.0F);
         GL11.glRotatef((float)this.rotateRightY.get_value(1.0D), 0.0F, 1.0F, 0.0F);
         GL11.glRotatef((float)this.rotateRightZ.get_value(1.0D), 0.0F, 0.0F, 1.0F);
         GL11.glScalef((float)this.scaleRight.get_value(1.0D) / 10.0F, (float)this.scaleRight.get_value(1.0D) / 10.0F, (float)this.scaleRight.get_value(1.0D) / 10.0F);
      } else if (event.getHandSide() == EnumHandSide.LEFT) {
         GL11.glTranslatef((float)this.xLeft.get_value(1.0D) / 100.0F, (float)this.yLeft.get_value(1.0D) / 100.0F, (float)this.zLeft.get_value(1.0D) / 100.0F);
         GL11.glRotatef((float)this.rotateLeftX.get_value(1.0D), 1.0F, 0.0F, 0.0F);
         GL11.glRotatef((float)this.rotateLeftY.get_value(1.0D), 0.0F, 1.0F, 0.0F);
         GL11.glRotatef((float)this.rotateLeftZ.get_value(1.0D), 0.0F, 0.0F, 1.0F);
         GL11.glScalef((float)this.scaleLeft.get_value(1.0D) / 10.0F, (float)this.scaleLeft.get_value(1.0D) / 10.0F, (float)this.scaleLeft.get_value(1.0D) / 10.0F);
      }

   }, new Predicate[0]);

   public OnePopViewmodleChanger() {
      super(OnePopCategory.ONEPOP_RENDER);
      this.name = "Custom Viewmodel";
      this.tag = "CustomViewmodel";
      this.description = "epik";
   }

   protected void enable() {
      this.fov = mc.field_71474_y.field_74334_X;
      MinecraftForge.EVENT_BUS.register(this);
   }

   protected void disable() {
      mc.field_71474_y.field_74334_X = this.fov;
      MinecraftForge.EVENT_BUS.unregister(this);
   }

   public void update() {
      mc.field_71474_y.field_74334_X = (float)this.custom_fov.get_value(1);
   }
}
