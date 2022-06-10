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
              if (needboost && (mc.thePlayer.posY - launchy) >= 0.9) {
                  mc.timer.timerSpeed = 1.1f
              } else {
                mc.timer.timerSpeed = 1.0f
              }
              MovementUtils.strafe(MovementUtils.getSpeed() * 1.011f)
            }
        } else {
            mc.thePlayer.motionX = 0.0
            mc.thePlayer.motionZ = 0.0
        }
    }
}
