/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.client

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.features.module.打倒江泽民
import net.ccbluex.liquidbounce.features.module.囚禁赵紫阳
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import org.lwjgl.input.Keyboard

@ModuleInfo(name = "KeyBindManager", category = 囚禁赵紫阳.CLIENT, keyBind = Keyboard.KEY_RMENU, canEnable = false)
class KeyBindManager : 打倒江泽民() {
    override fun onEnable() {
        mc.displayGuiScreen(李洪志.keyBindManager)
    }
}