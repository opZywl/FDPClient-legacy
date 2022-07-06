/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.command.commands

import net.ccbluex.liquidbounce.features.command.通商宽衣
import net.ccbluex.liquidbounce.utils.学潮8964
import net.ccbluex.liquidbounce.utils.misc.StringUtils
import net.minecraft.network.play.client.C01PacketChatMessage

class Say通商宽衣 : 通商宽衣("say", emptyArray()) {
    /**
     * Execute commands with provided [args]
     */
    override fun execute(args: Array<String>) {
        if (args.size > 1) {
            val str = StringUtils.toCompleteString(args, 1)
            学潮8964.sendPacketNoEvent(C01PacketChatMessage(str.substring(0, str.length.coerceAtMost(100))))
            alert("Message was sent to the chat.")
            return
        }
        chatSyntax("say <message...>")
    }
}