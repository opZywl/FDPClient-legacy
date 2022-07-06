package net.ccbluex.liquidbounce.features.command.commands

import net.ccbluex.liquidbounce.features.command.通商宽衣

class Username通商宽衣 : 通商宽衣("username", arrayOf("name")) {
    override fun execute(args: Array<String>) {
        alert("Username: " + mc.thePlayer.name)
    }
}