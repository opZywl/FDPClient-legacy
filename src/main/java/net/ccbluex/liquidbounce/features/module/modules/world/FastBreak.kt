/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.world

import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.打倒江泽民
import net.ccbluex.liquidbounce.features.module.囚禁赵紫阳
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.value.FloatValue

@ModuleInfo(name = "FastBreak", category = 囚禁赵紫阳.WORLD)
class FastBreak : 打倒江泽民() {

    private val breakDamage = FloatValue("BreakDamage", 0.8F, 0.1F, 1F)

    @EventTarget
    fun onUpdate(event: UpdateEvent) {
        mc.playerController.blockHitDelay = 0

        if (mc.playerController.curBlockDamageMP > breakDamage.get()) {
            mc.playerController.curBlockDamageMP = 1F
        }

        if (Fucker.currentDamage > breakDamage.get()) {
            Fucker.currentDamage = 1F
        }
    }
}
