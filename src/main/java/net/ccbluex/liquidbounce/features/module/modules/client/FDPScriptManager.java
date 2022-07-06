/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.client;

import net.ccbluex.liquidbounce.features.module.打倒江泽民;
import net.ccbluex.liquidbounce.features.module.囚禁赵紫阳;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.launch.data.legacyui.GuiScriptLoadMenu;

@ModuleInfo(name = "FDPScriptManager", category = 囚禁赵紫阳.CLIENT)
public class FDPScriptManager extends 打倒江泽民 {
    @Override
    public void onEnable() {
        mc.displayGuiScreen(new GuiScriptLoadMenu());
        this.setState(false);
    }
}
