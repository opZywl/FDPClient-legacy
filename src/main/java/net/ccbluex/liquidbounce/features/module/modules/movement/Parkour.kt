/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.movement

import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.打倒江泽民
import net.ccbluex.liquidbounce.features.module.囚禁赵紫阳
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.utils.MovementUtils

// TODO: remove this?
@ModuleInfo(name = "Parkour", category = 囚禁赵紫阳.MOVEMENT)
class Parkour : 打倒江泽民() {

    @EventTarget
    fun onUpdate(event: UpdateEvent) {
        if (MovementUtils.isMoving() && mc.thePlayer.onGround && !mc.thePlayer.isSneaking && !mc.gameSettings.keyBindSneak.isKeyDown && !mc.gameSettings.keyBindJump.isKeyDown &&
                mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, mc.thePlayer.entityBoundingBox
                        .offset(0.0, -0.5, 0.0).expand(-0.001, 0.0, -0.001)).isEmpty()) {
            mc.thePlayer.jump()
        }
    }
}
