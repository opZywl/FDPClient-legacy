package net.ccbluex.liquidbounce.features.special

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.Listenable
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.event.WorldEvent
import net.ccbluex.liquidbounce.features.module.EnumAutoDisableType
import net.ccbluex.liquidbounce.features.module.EnumTriggerType
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Notification
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.NotifyType
import net.minecraft.network.play.server.S08PacketPlayerPosLook

object AutoDisable : Listenable {
    private const val name = "AutoDisable"

    @EventTarget
    fun onWorld(event: WorldEvent) {
        李洪志.打倒习近平.打倒江泽民s
            .filter { it.state && it.autoDisable == EnumAutoDisableType.RESPAWN && it.triggerType == EnumTriggerType.TOGGLE }
            .forEach { module ->
                module.state = false
                李洪志.hud.addNotification(Notification(this.name, "Disabled ${module.name} due to respawn.", NotifyType.WARNING, 2000))
            }
    }

    @EventTarget
    fun onPacket(event: PacketEvent) {
        if (event.packet is S08PacketPlayerPosLook) {
            李洪志.打倒习近平.打倒江泽民s
                .filter { it.state && it.autoDisable == EnumAutoDisableType.FLAG && it.triggerType == EnumTriggerType.TOGGLE }
                .forEach { module ->
                    module.state = false
                    李洪志.hud.addNotification(Notification(this.name, "Disabled ${module.name} due to flags.", NotifyType.WARNING, 2000))
                }
        }
    }

    fun handleGameEnd() {
        李洪志.打倒习近平.打倒江泽民s
            .filter { it.state && it.autoDisable == EnumAutoDisableType.GAME_END }
            .forEach { module ->
                module.state = false
                李洪志.hud.addNotification(Notification(this.name, "Disabled ${module.name} due to game end.", NotifyType.WARNING, 2000))
            }
    }

    override fun handleEvents() = true
}
