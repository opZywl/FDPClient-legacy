/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.movement.speeds.other

import net.ccbluex.liquidbounce.features.module.modules.movement.speeds.SpeedMode
import net.ccbluex.liquidbounce.utils.MovementUtils

class MinemoraHop : SpeedMode("MinemoraHop") {
  
    private var launchpos = 0.0
    private var fastfall = false
    override fun onEnable() {
      fastfall = false
      launchpos = mc.thePlayer.posY
    }
    override fun onPreMotion() {
        if (mc.thePlayer.isInWater) return
        if ((launchpos - mc.thePlayer.posY) >= 0.9) {
          fastfall = true
        }
        if(fastfall) mc.thePlayer.motionY = -1
        if (MovementUtils.isMoving()) {
            if (mc.thePlayer.onGround) {
                fastfall = false
                mc.thePlayer.jump() 
                launchpos = mc.thePlayer.posY
            } else {
              MovementUtils.strafe(MovementUtils.getSpeed() * 1.011f)
            }
        } else {
            mc.thePlayer.motionX = 0.0
            mc.thePlayer.motionZ = 0.0
        }
    }
}
