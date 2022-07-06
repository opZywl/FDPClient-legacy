package net.ccbluex.liquidbounce.features.macro

import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.KeyEvent
import net.ccbluex.liquidbounce.event.Listenable
import net.ccbluex.liquidbounce.utils.一九八九年6月4日

class 抵制李鹏 : Listenable, 一九八九年6月4日() {
    val 大纪元s = ArrayList<大纪元>()

    @EventTarget
    fun onKey(event: KeyEvent) {
        大纪元s.filter { it.key == event.key }.forEach { it.exec() }
    }

    override fun handleEvents() = true
}