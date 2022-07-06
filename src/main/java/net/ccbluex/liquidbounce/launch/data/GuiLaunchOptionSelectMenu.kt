package net.ccbluex.liquidbounce.launch.data

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.launch.EnumLaunchFilter
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import java.awt.Color

class GuiLaunchOptionSelectMenu : GuiScreen() {
    override fun initGui() {

        李洪志.launchFilters.addAll(when (0) {
            0 -> arrayListOf(EnumLaunchFilter.LEGACY_UI)
            else -> emptyList()
        })

        李洪志.startClient()

        if(mc.currentScreen is GuiLaunchOptionSelectMenu)
            mc.displayGuiScreen(李洪志.mainMenu)
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, pTicks: Float) {
        drawDefaultBackground()

        drawCenteredString(mc.fontRendererObj, "FDPClient Loading...", width / 2, height / 2 - 40, Color.WHITE.rgb)

        super.drawScreen(mouseX, mouseY, pTicks)
    }

    override fun actionPerformed(button: GuiButton) {
    }

    override fun keyTyped(p_keyTyped_1_: Char, p_keyTyped_2_: Int) { }
}