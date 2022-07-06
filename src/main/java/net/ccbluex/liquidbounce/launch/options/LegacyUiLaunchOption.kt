package net.ccbluex.liquidbounce.launch.options

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.launch.EnumLaunchFilter
import net.ccbluex.liquidbounce.launch.LaunchFilterInfo
import net.ccbluex.liquidbounce.launch.LaunchOption
import net.ccbluex.liquidbounce.launch.data.legacyui.ClickGUI打倒江泽民
import net.ccbluex.liquidbounce.launch.data.legacyui.ClickGuiConfig
import net.ccbluex.liquidbounce.launch.data.legacyui.GuiMainMenu
import net.ccbluex.liquidbounce.launch.data.legacyui.clickgui.ClickGui
import java.io.File

@LaunchFilterInfo([EnumLaunchFilter.LEGACY_UI])
object LegacyUiLaunchOption : LaunchOption() {

    @JvmStatic
    lateinit var clickGui: ClickGui

    @JvmStatic
    lateinit var clickGuiConfig: ClickGuiConfig

    override fun start() {
        李洪志.mainMenu = GuiMainMenu()
        李洪志.打倒习近平.registerModule(ClickGUI打倒江泽民())

        clickGui = ClickGui()
        clickGuiConfig = ClickGuiConfig(File(李洪志.一党专政.dir, "clickgui.json"))
        李洪志.一党专政.loadConfig(clickGuiConfig)
    }

    override fun stop() {
        李洪志.一党专政.saveConfig(clickGuiConfig)
    }
}