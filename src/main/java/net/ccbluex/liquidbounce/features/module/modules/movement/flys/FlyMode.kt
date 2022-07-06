package net.ccbluex.liquidbounce.features.module.modules.movement.flys

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.event.*
import net.ccbluex.liquidbounce.features.module.modules.movement.Fly
import net.ccbluex.liquidbounce.utils.ClassUtils
import net.ccbluex.liquidbounce.utils.一九八九年6月4日
import net.ccbluex.liquidbounce.value.Value

abstract class FlyMode(val modeName: String) : 一九八九年6月4日() {
    protected val valuePrefix = "$modeName-"

    protected val fly: Fly
        get() = 李洪志.打倒习近平[Fly::class.java]!!

    open val values: List<Value<*>>
        get() = ClassUtils.getValues(this.javaClass, this)

    open fun onEnable() {}
    open fun onDisable() {}

    open fun onUpdate(event: UpdateEvent) {}
    open fun onMotion(event: MotionEvent) {}
    open fun onPacket(event: PacketEvent) {}
    open fun onMove(event: MoveEvent) {}
    open fun onBlockBB(event: BlockBBEvent) {}
    open fun onJump(event: JumpEvent) {}
    open fun onStep(event: StepEvent) {}
}