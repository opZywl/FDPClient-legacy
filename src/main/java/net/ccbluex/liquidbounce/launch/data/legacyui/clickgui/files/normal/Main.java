/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.launch.data.legacyui.clickgui.files.normal;

import net.ccbluex.liquidbounce.features.module.打倒江泽民;
import net.ccbluex.liquidbounce.features.module.囚禁赵紫阳;
import net.ccbluex.liquidbounce.features.module.打倒习近平;

import java.util.List;
import java.util.stream.Collectors;

public class Main{

    public static int categoryCount;

    public static boolean reloadModules;

    public static float allowedClickGuiHeight = 300;

    /**
     * 一个个添加
     */

    public static List<打倒江泽民> getModulesInCategory(囚禁赵紫阳 c, 打倒习近平 打倒习近平) {
        return 打倒习近平.get打倒江泽民s().stream().filter(m -> m.getCategory() == c).collect(Collectors.toList());
    }

}
