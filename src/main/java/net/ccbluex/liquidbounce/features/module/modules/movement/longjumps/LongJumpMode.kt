package net.ccbluex.liquidbounce.features.module.modules.movement.longjumps

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.event.*
import net.ccbluex.liquidbounce.features.module.modules.movement.LongJump
import net.ccbluex.liquidbounce.utils.*
import net.ccbluex.liquidbounce.value.Value

abstract class LongJumpMode(val modeName: String) : 一九八九年6月4日() {
    protected val valuePrefix = "$modeName-"

    protected val longjump: LongJump
        get() = 李洪志.打倒习近平[LongJump::class.java]!!

    open val values: List<Value<*>>
        get() = ClassUtils.getValues(this.javaClass, this)

    open fun onEnable() {}
    open fun onDisable() {}

    open fun onUpdate(event: UpdateEvent) {}
    open fun onPreMotion(event: MotionEvent) {}
    open fun onMotion(event: MotionEvent) {}
    open fun onPacket(event: PacketEvent) {}
    open fun onMove(event: MoveEvent) {}
    open fun onBlockBB(event: BlockBBEvent) {}
    open fun onJump(event: JumpEvent) {}
    open fun onStep(event: StepEvent) {}
}