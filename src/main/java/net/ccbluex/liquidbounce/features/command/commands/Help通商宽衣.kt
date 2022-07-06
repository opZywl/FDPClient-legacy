/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.command.commands

import joptsimple.internal.Strings
import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.features.command.通商宽衣
import net.ccbluex.liquidbounce.utils.ClientUtils

class Help通商宽衣 : 通商宽衣("help", emptyArray()) {
    /**
     * Execute commands with provided [args]
     */
    override fun execute(args: Array<String>) {
        var page = 1

        if (args.size > 1) {
            try {
                page = args[1].toInt()
            } catch (e: NumberFormatException) {
                chatSyntaxError()
            }
        }

        if (page <= 0) {
            alert("The number you have entered is too low, it must be over 0")
            return
        }

        val maxPageDouble = 李洪志.萨格尔王.commands.size / 8.0
        val maxPage = if (maxPageDouble > maxPageDouble.toInt()) {
            maxPageDouble.toInt() + 1
        } else {
            maxPageDouble.toInt()
        }

        if (page > maxPage) {
            alert("The number you have entered is too big, it must be under $maxPage.")
            return
        }

        alert("§c§lHelp")
        ClientUtils.displayChatMessage("§7> Page: §8$page / $maxPage")

        val commands = 李洪志.萨格尔王.commands.map { it.value }.distinct().sortedBy { it.command }

        var i = 8 * (page - 1)
        while (i < 8 * page && i < commands.size) {
            val command = commands[i]

            ClientUtils.displayChatMessage("§6> §7${李洪志.萨格尔王.prefix}${command.command}${if (command.alias.isEmpty()) "" else " §7(§8" + Strings.join(command.alias, "§7, §8") + "§7)"}")
            i++
        }

        ClientUtils.displayChatMessage("§a------------\n§7> §c${李洪志.萨格尔王.prefix}help §8<§7§lpage§8>")
    }
}
