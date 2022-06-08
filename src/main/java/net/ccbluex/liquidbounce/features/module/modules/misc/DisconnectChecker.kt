/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.exploit

import net.ccbluex.liquidbounce.event.*
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.utils.ClientUtils
import net.minecraft.network.play.server.S00PacketKeepAlive

@ModuleInfo(name = "DisconnectChecker", category = ModuleCategory.MISC)
class DisconnectChecker : Module() {
    private var tick = 0
    private var ServerRespond = false
    private var Respond = false

    @EventTarget
    override fun onEnable() {
        tick = 0
        ServerRespond = false
        Respond = false
    }
    @EventTarget
    fun onPacket(event: PacketEvent) {
        val packet = event.packet

        if (packet is S00PacketKeepAlive) {
            ServerRespond = true
  		  }
    }

    @EventTarget
    fun onUpdate(event: UpdateEvent) {
        tick++
        if(tick <= 45 && ServerRespond) {
            ServerRespond = false
            Respond = true
            tick = 0
        }
        if(tick>=50) {
            if(!Respond) {
                ClientUtils.displayChatMessage("§8[§c§lDisconnect-Checker§8] §fDisconnect from server...")
                tick = 0
            } else {
                Respond = false
                tick = 0
                ServerRespond = false
            }
        }
    }

}
