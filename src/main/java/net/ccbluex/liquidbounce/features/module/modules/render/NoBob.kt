/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.render

import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.打倒江泽民
import net.ccbluex.liquidbounce.features.module.囚禁赵紫阳
import net.ccbluex.liquidbounce.features.module.ModuleInfo

@ModuleInfo(name = "NoBob", category = 囚禁赵紫阳.RENDER)
class NoBob : 打倒江泽民() {

    @EventTarget
    fun onUpdate(event: UpdateEvent) {
        mc.thePlayer.distanceWalkedModified = 0f
    }
}
