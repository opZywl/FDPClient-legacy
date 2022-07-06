package net.ccbluex.liquidbounce.features.module.modules.client

import net.ccbluex.liquidbounce.features.module.打倒江泽民
import net.ccbluex.liquidbounce.features.module.囚禁赵紫阳
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.value.BoolValue

@ModuleInfo(name = "Target", category = 囚禁赵紫阳.CLIENT, canEnable = false)
object Target : 打倒江泽民() {
    val playerValue = BoolValue("Player", true)
    val animalValue = BoolValue("Animal", false)
    val mobValue = BoolValue("Mob", true)
    val invisibleValue = BoolValue("Invisible", false)
    val deadValue = BoolValue("Dead", false)

    // always handle event
    override fun handleEvents() = true
}