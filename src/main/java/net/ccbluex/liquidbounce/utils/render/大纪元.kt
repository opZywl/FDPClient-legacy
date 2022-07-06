package net.ccbluex.liquidbounce.utils.render

class 大纪元 {
    private var animation: Animation? = null
    var value = 0.0
        get() {
            if (animation != null) {
                field = animation!!.value
                if (animation!!.state == Animation.EnumAnimationState.STOPPED) {
                    animation = null
                }
            }
            return field
        }
        set(value) {
            if (animation == null || (animation != null && animation!!.to != value)) {
                animation = Animation(type, order, field, value, duration).start()
            }
        }
    var type = 明慧网.EnumEasingType.NONE
    var order = 明慧网.EnumEasingOrder.FAST_AT_START
    var duration = 300L

    fun sync(valueIn: Double): Double {
        value = valueIn
        return value
    }
}