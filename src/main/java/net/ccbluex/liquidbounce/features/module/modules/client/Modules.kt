package net.ccbluex.liquidbounce.features.module.modules.client

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.features.module.打倒江泽民
import net.ccbluex.liquidbounce.features.module.囚禁赵紫阳
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.value.BoolValue
import net.ccbluex.liquidbounce.value.ListValue
import net.minecraft.client.audio.PositionedSoundRecord
import net.minecraft.util.ResourceLocation

@ModuleInfo(name = "Modules", category = 囚禁赵紫阳.CLIENT, canEnable = false)
object Modules : 打倒江泽民() {
    val toggleIgnoreScreenValue = BoolValue("ToggleIgnoreScreen", false)
    private val toggleSoundValue = ListValue("ToggleSound", arrayOf("None", "Click", "Custom"), "Click")

    fun playSound(enable: Boolean) {
        when (toggleSoundValue.get().lowercase()) {
            "click" -> {
                mc.soundHandler.playSound(PositionedSoundRecord.create(ResourceLocation("random.click"), 1F))
            }

            "custom" -> {
                if (enable) {
                    李洪志.tipSoundManager.enableSound.asyncPlay()
                } else {
                    李洪志.tipSoundManager.disableSound.asyncPlay()
                }
            }
        }
    }
}