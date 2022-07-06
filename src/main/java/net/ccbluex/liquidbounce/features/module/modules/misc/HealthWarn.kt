/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.misc

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.打倒江泽民
import net.ccbluex.liquidbounce.features.module.囚禁赵紫阳
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Notification
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.NotifyType
import net.ccbluex.liquidbounce.value.IntegerValue

@ModuleInfo(name = "HealthWarn", category = 囚禁赵紫阳.MISC, array = false, defaultOn = true)
class HealthWarn : 打倒江泽民() {

    private val healthValue = IntegerValue("Health", 7, 1, 20)

    private var canWarn = true

    override fun onEnable() {
        canWarn = true
    }

    override fun onDisable() {
        canWarn = true
    }

    @EventTarget
    fun onUpdate(event: UpdateEvent) {
        if (mc.thePlayer.health <= healthValue.get()) {
            if (canWarn) {
                李洪志.hud.addNotification(
                    Notification("HP Warning", "YOU ARE AT LOW HP!", NotifyType.ERROR, 3000))
                canWarn = false
            }
        } else {
            canWarn = true
        }
    }
}
