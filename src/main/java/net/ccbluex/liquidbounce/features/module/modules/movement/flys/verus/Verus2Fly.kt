package net.ccbluex.liquidbounce.features.module.modules.movement.flys.verus

import net.ccbluex.liquidbounce.event.BlockBBEvent
import net.ccbluex.liquidbounce.event.JumpEvent
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.modules.movement.flys.FlyMode
import net.ccbluex.liquidbounce.utils.MovementUtils
import net.ccbluex.liquidbounce.utils.PacketUtils
import net.ccbluex.liquidbounce.utils.timer.MSTimer
import net.ccbluex.liquidbounce.value.FloatValue
import net.ccbluex.liquidbounce.value.ListValue
import net.ccbluex.liquidbounce.value.BoolValue
import net.minecraft.block.BlockAir
import net.minecraft.network.play.client.C03PacketPlayer
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition
import net.minecraft.util.AxisAlignedBB

class Verus2Fly : FlyMode("Verus2") {
    private val damageMode = ListValue("VerusDamage", arrayOf("Damage1","Damage2","Damage3","CustomDamage"),"Damage1")
    private val packet1 = FloatValue("CustomPacket1Clip", 4f,0f,5f).displayable {damageMode.equals("CustomDamage")}
    private val packet2 = FloatValue("CustomPacket2Clip", -0.2f,-1f,5f).displayable {damageMode.equals("CustomDamage")}
    private val packet3 = FloatValue("CustomPacket3Clip", 0.5f,0f,5f).displayable {damageMode.equals("CustomDamage")}
    private val speedValue = FloatValue("${valuePrefix}Speed", 1.5f, 0f, 5f)
    private val yMotionZero = BoolValue("SetYMotion0",true)
    private val blocksBB = BoolValue("useBlocksBBfly",true)
    private val groundSpoof = BoolValue("groundSpoof",true)

    private var flyable = false
    private val timer = MSTimer()
    
    private fun verusDamage() {
        when (damageMode.get().lowercase()) {
          "damage1" -> {
            PacketUtils.sendPacketNoEvent(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 3.05, mc.thePlayer.posZ, false))
            PacketUtils.sendPacketNoEvent(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false))
            PacketUtils.sendPacketNoEvent(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.41999998688697815, mc.thePlayer.posZ, true))
          }
          "damage2" -> {
            PacketUtils.sendPacketNoEvent(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 3.35, mc.thePlayer.posZ, false))
            PacketUtils.sendPacketNoEvent(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false))
            PacketUtils.sendPacketNoEvent(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true))
          }
          "damage3" -> {
            PacketUtils.sendPacketNoEvent(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 4, mc.thePlayer.posZ, false))
            PacketUtils.sendPacketNoEvent(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false))
            PacketUtils.sendPacketNoEvent(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true))
          }
          "customdamage" -> {
            PacketUtils.sendPacketNoEvent(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + packet1.get().toDouble(), mc.thePlayer.posZ, false))
            PacketUtils.sendPacketNoEvent(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + packet2.get().toDouble(), mc.thePlayer.posZ, false))
            PacketUtils.sendPacketNoEvent(C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + packet3.get().toDouble(), mc.thePlayer.posZ, true))
          }
        }
    }

    override fun onEnable() {
        verusDamage()
        mc.thePlayer.motionX = 0.0
        mc.thePlayer.motionY = 0.0
        mc.thePlayer.motionZ = 0.0
        mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.42, mc.thePlayer.posZ)
        flyable = true
        mc.timer.timerSpeed = 0.5f
        fly.launchY += 0.42
        timer.reset()
    }

    override fun onUpdate(event: UpdateEvent) {
        if (yMotionZero.get()) {
            mc.thePlayer.motionY = 0.0
        }
        if (timer.hasTimePassed(300)) {
            mc.timer.timerSpeed = 1f
        }

        if (timer.hasTimePassed(1500)) {
            if (flyable) {
                MovementUtils.strafe(0.48f)
            }
            flyable = false
        }

        if (flyable && timer.hasTimePassed(100)) {
            MovementUtils.strafe(speedValue.get())
        } else if (!timer.hasTimePassed(100)) {
            mc.thePlayer.motionX = 0.0
            mc.thePlayer.motionY = 0.0
            mc.thePlayer.motionZ = 0.0
        }
    }

    override fun onPacket(event: PacketEvent) {
        val packet = event.packet

        if (packet is C03PacketPlayer) {
            packet.onGround = groundSpoof.get()
        }
    }

    override fun onBlockBB(event: BlockBBEvent) {
        if (event.block is BlockAir && event.y <= fly.launchY && blocksBB.get()) {
            event.boundingBox = AxisAlignedBB.fromBounds(event.x.toDouble(), event.y.toDouble(), event.z.toDouble(), event.x + 1.0, fly.launchY, event.z + 1.0)
        }
    }

    override fun onJump(event: JumpEvent) {
        event.cancelEvent()
    }
}
