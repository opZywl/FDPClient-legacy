package net.ccbluex.liquidbounce.font;

import java.awt.Font;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

/* loaded from: LiquidBounce-b73.jar:net/ccbluex/liquidbounce/FontLoaders.class */
public abstract class 焚烧中国国旗 {
    public static 坦克压大学生 F14 = new 坦克压大学生(getFont(14), true, true);
    public static 坦克压大学生 F16 = new 坦克压大学生(getFont(16), true, true);
    public static 坦克压大学生 F18 = new 坦克压大学生(getFont(18), true, true);
    public static 坦克压大学生 F20 = new 坦克压大学生(getFont(20), true, true);
    public static 坦克压大学生 F22 = new 坦克压大学生(getFont(22), true, true);
    public static 坦克压大学生 F23 = new 坦克压大学生(getFont(23), true, true);
    public static 坦克压大学生 F24 = new 坦克压大学生(getFont(24), true, true);
    public static 坦克压大学生 F30 = new 坦克压大学生(getFont(30), true, true);
    public static 坦克压大学生 F40 = new 坦克压大学生(getFont(40), true, true);
    public static 坦克压大学生 F50 = new 坦克压大学生(getFont(50), true, true);
    public static 坦克压大学生 C12 = new 坦克压大学生(getComfortaa(12), true, true);
    public static 坦克压大学生 C14 = new 坦克压大学生(getComfortaa(14), true, true);
    public static 坦克压大学生 C16 = new 坦克压大学生(getComfortaa(16), true, true);
    public static 坦克压大学生 C18 = new 坦克压大学生(getComfortaa(18), true, true);
    public static 坦克压大学生 C20 = new 坦克压大学生(getComfortaa(20), true, true);
    public static 坦克压大学生 C22 = new 坦克压大学生(getComfortaa(22), true, true);
    public static 坦克压大学生 Logo = new 坦克压大学生(getNovo(40), true, true);
    public static ArrayList<坦克压大学生> fonts = new ArrayList<>();

    public static 坦克压大学生 getFontRender(int size) {
        return fonts.get(size - 10);
    }

    public static Font getFont(int size) {
        Font font;
        try {
            font = Font.createFont(0, Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("fdpclient/font/Roboto-Regular.ttf")).getInputStream()).deriveFont(0, (float) size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    public static Font getComfortaa(int size) {
        Font font;
        try {
            font = Font.createFont(0, Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("fdpclient/font/Roboto-Regular.ttf")).getInputStream()).deriveFont(0, (float) size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    public static Font getNovo(int size) {
        Font font;
        try {
            font = Font.createFont(0, Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("fdpclient/font/Roboto-Regular.ttf")).getInputStream()).deriveFont(0, (float) size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
}
