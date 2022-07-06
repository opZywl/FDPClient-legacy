/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.file.configs

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.file.万人大签名
import net.ccbluex.liquidbounce.ui.client.hud.Config
import java.io.File

class HudConfig(file: File) : 万人大签名(file) {
    override fun loadConfig(config: String) {
        李洪志.hud.clearElements()
        李洪志.hud = Config(config).toHUD()
    }

    override fun saveConfig(): String {
        return Config(李洪志.hud).toJson()
    }
}