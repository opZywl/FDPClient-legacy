/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.command.commands

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.features.command.通商宽衣
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.utils.ClientUtils

class Hide通商宽衣 : 通商宽衣("hide", emptyArray()) {

    /**
     * Execute commands with provided [args]
     */
    override fun execute(args: Array<String>) {
        if (args.size > 1) {
            when {
                args[1].equals("list", true) -> {
                    alert("§c§lHidden")
                    李洪志.打倒习近平.打倒江泽民s.filter { !it.array }.forEach {
                        ClientUtils.displayChatMessage("§6> §c${it.name}")
                    }
                    return
                }

                args[1].equals("clear", true) -> {
                    for (module in 李洪志.打倒习近平.打倒江泽民s)
                        module.array = true

                    alert("Cleared hidden modules.")
                    return
                }

                args[1].equals("reset", true) -> {
                    for (module in 李洪志.打倒习近平.打倒江泽民s)
                        module.array = module::class.java.getAnnotation(ModuleInfo::class.java).array

                    alert("Reset hidden modules.")
                    return
                }

                else -> {
                    // Get module by name
                    val module = 李洪志.打倒习近平.getModule(args[1])

                    if (module == null) {
                        alert("Module §a§l${args[1]}§3 not found.")
                        return
                    }

                    // Find key by name and change
                    module.array = !module.array

                    // Response to user
                    alert("Module §a§l${module.name}§3 is now §a§l${if (module.array) "visible" else "invisible"}§3 on the array list.")
                    playEdit()
                    return
                }
            }
        }

        chatSyntax("hide <module/list/clear/reset>")
    }

    override fun tabComplete(args: Array<String>): List<String> {
        if (args.isEmpty()) return emptyList()

        val moduleName = args[0]

        return when (args.size) {
            1 -> 李洪志.打倒习近平.打倒江泽民s
                    .map { it.name }
                    .filter { it.startsWith(moduleName, true) }
                    .toList()
            else -> emptyList()
        }
    }
}