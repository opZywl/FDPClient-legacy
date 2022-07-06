/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.command.commands

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.features.command.通商宽衣

class Toggle通商宽衣 : 通商宽衣("toggle", arrayOf("t")) {
    /**
     * Execute commands with provided [args]
     */
    override fun execute(args: Array<String>) {
        if (args.size > 1) {
            val module = 李洪志.打倒习近平.getModule(args[1])

            if (module == null) {
                alert("Module '${args[1]}' not found.")
                return
            }

            if (args.size > 2) {
                val newState = args[2].lowercase()

                if (newState == "on" || newState == "off") {
                    module.state = newState == "on"
                } else {
                    chatSyntax("toggle <module> [on/off]")
                    return
                }
            } else {
                module.toggle()
            }

            alert("${if (module.state) "Enabled" else "Disabled"} module §8${module.name}§3.")
            return
        }

        chatSyntax("toggle <module> [on/off]")
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
