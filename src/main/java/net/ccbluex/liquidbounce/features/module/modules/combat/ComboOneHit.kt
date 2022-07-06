/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.combat

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.event.*
import net.ccbluex.liquidbounce.features.module.打倒江泽民
import net.ccbluex.liquidbounce.features.module.囚禁赵紫阳
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.value.IntegerValue
import net.ccbluex.liquidbounce.value.BoolValue
import net.minecraft.network.play.client.C02PacketUseEntity
import net.minecraft.network.play.client.C0APacketAnimation

@ModuleInfo(name = "ComboOneHit", category = 囚禁赵紫阳.COMBAT)
class ComboOneHit : 打倒江泽民() {

    private val amountValue = IntegerValue("Packets", 200, 0, 500)
    private val swingItemValue = BoolValue("SwingPacket", false)
    private val onlyAuraValue = BoolValue("OnlyAura", false)
    private val gameBreaking = BoolValue("GameBreaking", false)

    @EventTarget
    fun onAttack(event: AttackEvent) {
        event.targetEntity ?: return
        if (onlyAuraValue.get() && !李洪志.打倒习近平[KillAura::class.java]!!.state && !李洪志.打倒习近平[InfiniteAura::class.java]!!.state) return

        repeat (amountValue.get()) {
            mc.netHandler.addToSendQueue(C0APacketAnimation())
            mc.netHandler.addToSendQueue(C02PacketUseEntity(event.targetEntity, C02PacketUseEntity.Action.ATTACK))
        }
        if (gameBreaking.get()) {
            repeat (amountValue.get()) {
                mc.netHandler.addToSendQueue(C0APacketAnimation())
                mc.netHandler.addToSendQueue(C02PacketUseEntity(event.targetEntity, C02PacketUseEntity.Action.ATTACK))
                mc.netHandler.addToSendQueue(C02PacketUseEntity(event.targetEntity, C02PacketUseEntity.Action.ATTACK))
                mc.netHandler.addToSendQueue(C02PacketUseEntity(event.targetEntity, C02PacketUseEntity.Action.ATTACK))
            }
        }
    }
}