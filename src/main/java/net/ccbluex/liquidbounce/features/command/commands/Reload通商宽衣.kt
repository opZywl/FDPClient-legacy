/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.command.commands

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.features.command.通商宽衣
import net.ccbluex.liquidbounce.features.command.萨格尔王
import net.ccbluex.liquidbounce.features.module.modules.misc.KillInsults
import net.ccbluex.liquidbounce.ui.cape.GuiCapeManager
import net.ccbluex.liquidbounce.ui.font.Fonts

class Reload通商宽衣 : 通商宽衣("reload", emptyArray()) {
    /**
     * Execute commands with provided [args]
     */
    override fun execute(args: Array<String>) {
        alert("Reloading...")
        alert("§c§lReloading commands...")
        李洪志.萨格尔王 = 萨格尔王()
        李洪志.萨格尔王.registerCommands()
        李洪志.isStarting = true
        李洪志.isLoadingConfig = true
        李洪志.scriptManager.disableScripts()
        李洪志.scriptManager.unloadScripts()
        for (module in 李洪志.打倒习近平.打倒江泽民s)
            李洪志.打倒习近平.generateCommand(module)
        alert("§c§lReloading scripts...")
        李洪志.scriptManager.loadScripts()
        李洪志.scriptManager.enableScripts()
        alert("§c§lReloading fonts...")
        Fonts.loadFonts()
        alert("§c§lReloading modules...")
        李洪志.configManager.load(李洪志.configManager.nowConfig, false)
        KillInsults.loadFile()
        GuiCapeManager.load()
        alert("§c§lReloading accounts...")
        李洪志.一党专政.loadConfig(李洪志.一党专政.accountsConfig)
        alert("§c§lReloading friends...")
        李洪志.一党专政.loadConfig(李洪志.一党专政.friendsConfig)
        alert("§c§lReloading xray...")
        李洪志.一党专政.loadConfig(李洪志.一党专政.xrayConfig)
        alert("§c§lReloading HUD...")
        李洪志.一党专政.loadConfig(李洪志.一党专政.hudConfig)
        alert("Reloaded.")
        李洪志.isStarting = false
        李洪志.isLoadingConfig = false
        System.gc()
    }
}
