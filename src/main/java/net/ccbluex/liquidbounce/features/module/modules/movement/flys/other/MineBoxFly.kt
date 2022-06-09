package net.ccbluex.liquidbounce.features.module.modules.movement.flys.other

import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.modules.movement.flys.FlyMode
import net.ccbluex.liquidbounce.utils.MovementUtils
import net.ccbluex.liquidbounce.value.FloatValue
import net.minecraft.network.play.client.C03PacketPlayer

class MineBoxFly : FlyMode("MineBox") {
    private val speedValue = FloatValue("${valuePrefix}Speed", 1f, 0f, 5f)
    private val vspeedValue = FloatValue("${valuePrefix}Vertical", 1f, 0f, 5f)

    override fun onEnable() {
    }

    override fun onUpdate(event: UpdateEvent) {
        mc.thePlayer.motionX = 0.0
        mc.thePlayer.motionY = 0.0
        mc.thePlayer.motionZ = 0.0
        if (mc.gameSettings.keyBindJump.isKeyDown) {
            mc.thePlayer.motionY += vspeedValue.get()
        }
        if (mc.gameSettings.keyBindSneak.isKeyDown) {
            mc.thePlayer.motionY -= vspeedValue.get()
        }

        MovementUtils.strafe(speedValue.get())
    }


    override fun onPacket(event: PacketEvent) {
        val packet = event.packet

        if (packet is C03PacketPlayer) {
            event.cancelEvent()
        }
    }

    override fun onDisable() {
        mc.thePlayer.motionX = 0.0
        mc.thePlayer.motionY = 0.0
        mc.thePlayer.motionZ = 0.0
    }
}
