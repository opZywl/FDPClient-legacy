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
import net.ccbluex.liquidbounce.value.BoolValue
import net.ccbluex.liquidbounce.value.IntegerValue
import net.ccbluex.liquidbounce.value.ListValue
import net.minecraft.block.BlockAir
import net.minecraft.network.play.client.C03PacketPlayer
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition
import net.minecraft.util.AxisAlignedBB

class Verus2Fly : FlyMode("Verus2") {
    private val speedValue = FloatValue("${valuePrefix}Speed", 1.5f, 0f, 5f)
    private val spoofGround = BoolValue("${valuePrefix}GroundSpoof",false)
    private val damageMode = ListValue("${valuePrefix}VerusDamageMode", arrayOf("Damage1", "Damage2", "Damage3","CustomDamage"), "Damage1")
    private val packet1 = FloatValue("${valuePrefix}Packet1-Yclip", 2.15f,-1f,7f).displayable { damageMode.equals("CustomDamage") }
    private val packet2 = FloatValue("${valuePrefix}Packet2-Yclip", 0f,-1f,7f).displayable { damageMode.equals("CustomDamage") }
    private val packet3 = FloatValue("${valuePrefix}Packet3-Yclip", 0.25f,-1f,7f).displayable { damageMode.equals("CustomDamage") }
    private val reDamage = BoolValue("${valuePrefix}ReDamage", true)
    private val alwaysReDamage = BoolValue("${valuePrefix}AlwaysReDamage",true).displayable {reDamage.get()} 
    private val reDamageAmount = IntegerValue("${valuePrefix}ReDamageAmount", 3, 1, 10).displayable { reDamage.get() && !alwaysReDamage.get() }
    private val reDamageDelay = FloatValue("${valuePrefix}ReDamageDelay", 7f,0f,30f).displayable { reDamage.get() }
    private val reDamageNoMove = BoolValue("${valuePrefix}ReDamageNoMove", false).displayable {reDamage.get()}
    private val reDamageTimer = BoolValue("${valuePrefix}ReDamageDamgeTimer",true).displayable {reDamage.get()}
    private val damageTimer = FloatValue("${valuePrefix}DamageTimer", 0.5f, 0f, 3f)
    private val damageTime = FloatValue("${valuePrefix}DamageTimerTime", 0.3f, 0f, 2f)
    private val stopTime = FloatValue("${valuePrefix}NoMoveTime",0.1f,0f,1f)
    private val boostTimer = FloatValue("${valuePrefix}BoostTimer", 1f, 0f, 5f)
    private val boostTime = FloatValue("BoostTime", 10f, 0f, 60f)
    private val useBB = BoolValue("${valuePrefix}UseBlocksBB", true)

    private var flyable = false
    private val timer = MSTimer()
    private var redamaged = false
    private var redamages = 0
    
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
            PacketUtils.sendPacketNoEvent(C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 4, mc.thePlayer.posZ, false))
            PacketUtils.sendPacketNoEvent(C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false))
            PacketUtils.sendPacketNoEvent(C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true))
          }
          "customdamage" -> {
            PacketUtils.sendPacketNoEvent(C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + packet1.get().toDouble(), mc.thePlayer.posZ, false))
            PacketUtils.sendPacketNoEvent(C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + packet2.get().toDouble(), mc.thePlayer.posZ, false))
            PacketUtils.sendPacketNoEvent(C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + packet3.get().toDouble(), mc.thePlayer.posZ, true))
          }
        }
    }

    override fun onEnable() {
        verusDamage()
            
        mc.thePlayer.motionX = 0.0
        mc.thePlayer.motionY = 0.0
        mc.thePlayer.motionZ = 0.0
        mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.41999998688697815, mc.thePlayer.posZ)
        flyable = true
        mc.timer.timerSpeed = damageTimer.get()
        fly.launchY += 0.419
        timer.reset()
    }

    override fun onUpdate(event: UpdateEvent) {
        if (reDamage.get()) {
            if (timer.hasTimePassed((reDamageDelay.get() * 100f).toLong())) {

                redamaged = true
                if (alwaysReDamage.get()) {
                    verusDamage()
                    timer.reset()
                    redamages = 0
                    if (reDamageTimer.get()) {
                        mc.timer.timerSpeed = damageTimer.get()
                    } else {
                        mc.timer.timerSpeed = boostTimer.get()
                    }
                    
                } else if (redamages < reDamageAmount.get()) {
                    redamages++
                    verusDamage()
                    timer.reset()
                    if (reDamageTimer.get()) {
                        mc.timer.timerSpeed = damageTimer.get()
                    } else {
                        mc.timer.timerSpeed = boostTimer.get()
                    }
                }
            }
        }
        
        
        if (timer.hasTimePassed((damageTime.get() * 1000f).toLong())) {
            mc.timer.timerSpeed = boostTimer.get()
        }

        if (timer.hasTimePassed((boostTime.get() * 100).toLong() )) {
            if (flyable) {
                MovementUtils.strafe(speedValue.get())
            }
            flyable = false
        }

        if (flyable && timer.hasTimePassed( (stopTime.get() * 1000).toLong() )) {
            MovementUtils.strafe(speedValue.get())
        } else if (!timer.hasTimePassed((stopTime.get() * 1000).toLong())) {
            if (redamaged) {
                if (reDamageNoMove.get()) {
                    mc.thePlayer.motionX = 0.0
                    mc.thePlayer.motionY = 0.0
                    mc.thePlayer.motionZ = 0.0
                }
            } else {
                mc.thePlayer.motionX = 0.0
                mc.thePlayer.motionY = 0.0
                mc.thePlayer.motionZ = 0.0
            }
        }
    }

    override fun onPacket(event: PacketEvent) {
        val packet = event.packet
        if (packet is C03PacketPlayer) {
            packet.onGround = spoofGround.get()
        }
    }

    override fun onBlockBB(event: BlockBBEvent) {
        if (event.block is BlockAir && event.y <= fly.launchY && useBB.get()) {
            event.boundingBox = AxisAlignedBB.fromBounds(event.x.toDouble(), event.y.toDouble(), event.z.toDouble(), event.x + 1.0, fly.launchY, event.z + 1.0)
        }
    }

    override fun onJump(event: JumpEvent) {
        event.cancelEvent()
    }
}
