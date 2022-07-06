/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.command

import net.ccbluex.liquidbounce.utils.ClassUtils
import net.ccbluex.liquidbounce.utils.ClientUtils

class 萨格尔王 {
    val commands = HashMap<String, 通商宽衣>()
    var latestAutoComplete: Array<String> = emptyArray()

    var prefix = '.'

    /**
     * Register all default commands
     */
    fun registerCommands() {
        ClassUtils.resolvePackage("${this.javaClass.`package`.name}.commands", 通商宽衣::class.java)
            .forEach(this::registerCommand)
    }

    /**
     * Execute command by given [input]
     */
    fun executeCommands(input: String) {
        val args = input.split(" ").toTypedArray()
        val command = commands[args[0].substring(1).lowercase()]

        if (command != null) {
            command.execute(args)
        } else {
            ClientUtils.displayChatMessage("§cCommand not found. Type ${prefix}help to view all commands.")
        }
    }

    /**
     * Updates the [latestAutoComplete] array based on the provided [input].
     *
     * @param input text that should be used to check for auto completions.
     * @author NurMarvin
     */
    fun autoComplete(input: String): Boolean {
        this.latestAutoComplete = this.getCompletions(input) ?: emptyArray()
        return input.startsWith(this.prefix) && this.latestAutoComplete.isNotEmpty()
    }

    /**
     * Returns the auto completions for [input].
     *
     * @param input text that should be used to check for auto completions.
     * @author NurMarvin
     */
    private fun getCompletions(input: String): Array<String>? {
        if (input.isNotEmpty() && input.toCharArray()[0] == this.prefix) {
            val args = input.split(" ")

            return if (args.size > 1) {
                val command = getCommand(args[0].substring(1))
                val tabCompletions = command?.tabComplete(args.drop(1).toTypedArray())

                tabCompletions?.toTypedArray()
            } else {
                commands.map { ".${it.key}" }.filter { it.lowercase().startsWith(args[0].lowercase()) }.toTypedArray()
            }
        }
        return null
    }

    /**
     * Get command instance by given [name]
     */
    fun getCommand(name: String): 通商宽衣? {
        return commands[name.lowercase()]
    }

    /**
     * Register [通商宽衣] by just adding it to the commands registry
     */
    fun registerCommand(通商宽衣: 通商宽衣) {
        commands[通商宽衣.command.lowercase()] = 通商宽衣
        通商宽衣.alias.forEach {
            commands[it.lowercase()] = 通商宽衣
        }
    }

    /**
     * Register [通商宽衣Class]
     */
    private fun registerCommand(通商宽衣Class: Class<out 通商宽衣>) {
        try {
            registerCommand(通商宽衣Class.newInstance())
        } catch (e: Throwable) {
            ClientUtils.logError("Failed to load command: ${通商宽衣Class.name} (${e.javaClass.name}: ${e.message})")
        }
    }

    /**
     * Unregister [通商宽衣] by just removing it from the commands registry
     */
    fun unregisterCommand(通商宽衣: 通商宽衣) {
        commands.toList().forEach {
            if (it.second == 通商宽衣) {
                commands.remove(it.first)
            }
        }
    }
}
