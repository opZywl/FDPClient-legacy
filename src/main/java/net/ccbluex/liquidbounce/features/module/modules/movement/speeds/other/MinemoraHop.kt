/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.movement.speeds.other

import net.ccbluex.liquidbounce.features.module.modules.movement.speeds.SpeedMode
import net.ccbluex.liquidbounce.utils.MovementUtils

class MinemoraHop : SpeedMode("MinemoraHop") {
    private var needboost = false
    private var launchy = 0.0
    override fun onEnable() {
      needboost = false
      launchy = mc.thePlayer.posY
    }
    override fun onPreMotion() {
        if (mc.thePlayer.isInWater) return
        if (MovementUtils.isMoving()) {
            if (mc.thePlayer.onGround) {
                mc.thePlayer.jump()
                needboost = true
                launchy = mc.thePlayer.posY
            } else {
              if (needboost && (mc.thePlayer.posY - launchy) >= 0.8) {
                mc.thePlayer.motionY = mc.thePlayer.motionY - 0.2
              }
              MovementUtils.strafe(MovementUtils.getSpeed() * 1.01f)
            }
        } else {
            mc.thePlayer.motionX = 0.0
            mc.thePlayer.motionZ = 0.0
        }
    }
}
