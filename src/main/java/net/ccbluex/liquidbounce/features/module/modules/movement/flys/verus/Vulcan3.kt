package net.ccbluex.liquidbounce.features.module.modules.movement.flys.verus

import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.event.EventState
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.event.StepEvent
import net.ccbluex.liquidbounce.utils.ClientUtils
import net.ccbluex.liquidbounce.features.module.modules.movement.flys.FlyMode
import net.ccbluex.liquidbounce.value.FloatValue
import net.ccbluex.liquidbounce.value.BoolValue
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition
import net.minecraft.network.play.server.S08PacketPlayerPosLook
import kotlin.math.cos
import kotlin.math.sin

class Vulcan3Fly : FlyMode("Vulcan3") {

    private val hclipValue = FloatValue("${valuePrefix}HClip", 5.0f, 3.0f, 7.0f)
    private val vclipValue = FloatValue("${valuePrefix}VClip", 5.0f, 3.0f, 7.0f)
    private val betterBypassValue = BoolValue("${valuePrefix}Timer", false)

    private var ClipWait = false
    private var ClipNow = false
    private var Glide = false
    private var CanDisabled = true

    override fun onEnable() {
        if(mc.thePlayer.onGround) {
            if(betterBypassValue.get()) {
                mc.timer.timerSpeed = 0.14f
            }
            ClipWait = true
            clip(0.0F, -2.0F)
        } else if(!mc.thePlayer.onGround) {
            ClientUtils.displayChatMessage("§8[§c§lVulcan-Fly§8] §cU aren't on ground!")
            fly.state = false
        }
    }

    override fun onUpdate(event: UpdateEvent) {
        if(ClipNow) {
            ClipNow = false
            clip(hclipValue.get(), vclipValue.get())
            Glide = true
        }
        if(Glide) {
            if(mc.thePlayer.fallDistance >= 0.7f) {
                mc.thePlayer.fallDistance = 0.0f
                mc.thePlayer.motionY = 0.01
                if(betterBypassValue.get()) mc.timer.timerSpeed = 1.0f
                CanDisabled = true
            }
            if(mc.thePlayer.onGround && CanDisabled) {
                fly.state = false
            }
        }
    }

    override fun onPacket(event: PacketEvent) {
        val packet = event.packet
        if(ClipWait && packet is S08PacketPlayerPosLook) {
            ClipWait = false
            ClipNow = true
        }
    }

    override fun onDisable() {
        ClipWait = false
        ClipNow = false
        CanDisabled = false
    }

    private fun clip(dist: Float, y: Float) {
        val yaw = Math.toRadians(mc.thePlayer.rotationYaw.toDouble())
        mc.thePlayer.setPosition(mc.thePlayer.posX + -(sin(yaw) * dist), mc.thePlayer.posY + y, mc.thePlayer.posZ + (cos(yaw) * dist))
        mc.netHandler.addToSendQueue(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false))
    }
}
