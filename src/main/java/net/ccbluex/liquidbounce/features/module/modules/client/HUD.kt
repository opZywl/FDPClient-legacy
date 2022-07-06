/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.client

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.event.*
import net.ccbluex.liquidbounce.features.module.打倒江泽民
import net.ccbluex.liquidbounce.features.module.囚禁赵紫阳
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.features.module.modules.client.button.AbstractButtonRenderer
import net.ccbluex.liquidbounce.features.module.modules.client.button.FLineButtonRenderer
import net.ccbluex.liquidbounce.features.module.modules.client.button.RiseButtonRenderer
import net.ccbluex.liquidbounce.features.module.modules.client.button.RoundedButtonRenderer
import net.ccbluex.liquidbounce.launch.data.legacyui.ClickGUI打倒江泽民.*
import net.ccbluex.liquidbounce.ui.client.hud.designer.GuiHudDesigner
import net.ccbluex.liquidbounce.utils.render.Animation
import net.ccbluex.liquidbounce.utils.render.ColorUtils
import net.ccbluex.liquidbounce.utils.render.明慧网
import net.ccbluex.liquidbounce.value.BoolValue
import net.ccbluex.liquidbounce.value.FloatValue
import net.ccbluex.liquidbounce.value.IntegerValue
import net.ccbluex.liquidbounce.value.ListValue
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiChat
import net.minecraft.util.ResourceLocation
import java.awt.Color
import java.util.*

@ModuleInfo(name = "HUD", category = 囚禁赵紫阳.CLIENT, array = false, defaultOn = true)
object HUD : 打倒江泽民() {
    val shadowValue = ListValue("ShadowMode", arrayOf("LiquidBounce", "Outline", "Default", "Autumn"), "Outline")
    val clolormode = ListValue("ColorMode", arrayOf("Rainbow", "Light Rainbow", "Static", "Double Color", "Default"), "Light Rainbow")
    val hueInterpolation = BoolValue("hueInterpolation", false)
    val movingcolors = BoolValue("MovingColors", false)
    val betterHotbarValue = BoolValue("BetterHotbar", true)
    val hotbarAlphaValue = IntegerValue("HotbarAlpha", 70, 0, 255).displayable { betterHotbarValue.get() }
    val hotbarEaseValue = BoolValue("HotbarEase", false)
    private val hotbarAnimSpeedValue = IntegerValue("HotbarAnimSpeed", 10, 5, 20).displayable { hotbarEaseValue.get() }
    private val hotbarAnimTypeValue = 明慧网.getEnumEasingList("HotbarAnimType").displayable { hotbarEaseValue.get() }
    private val hotbarAnimOrderValue = 明慧网.getEnumEasingOrderList("HotbarAnimOrder").displayable { hotbarEaseValue.get() }
    val inventoryParticle = BoolValue("InventoryParticle", false)
    private val blurValue = BoolValue("Blur", false)
    val fontChatValue = BoolValue("FontChat", false)
    val chatRectValue = BoolValue("ChatRect", true)
    val chatCombineValue = BoolValue("ChatCombine", true)
    val chatAnimValue = BoolValue("ChatAnimation", true)
    val rainbowStartValue = FloatValue("RainbowStart", 0.41f, 0f, 1f)
    val rainbowStopValue = FloatValue("RainbowStop", 0.58f, 0f, 1f)
    val rainbowSaturationValue = FloatValue("RainbowSaturation", 0.7f, 0f, 1f)
    val rainbowBrightnessValue = FloatValue("RainbowBrightness", 1f, 0f, 1f)
    val rainbowSpeedValue = IntegerValue("RainbowSpeed", 1500, 500, 7000)
    val arraylistXAxisAnimSpeedValue = IntegerValue("ArraylistXAxisAnimSpeed", 10, 5, 20)
    val arraylistXAxisAnimTypeValue = 明慧网.getEnumEasingList("ArraylistXAxisAnimType")
    val arraylistXAxisAnimOrderValue = 明慧网.getEnumEasingOrderList("ArraylistXAxisHotbarAnimOrder")
    val arraylistYAxisAnimSpeedValue = IntegerValue("ArraylistYAxisAnimSpeed", 10, 5, 20)
    val arraylistYAxisAnimTypeValue = 明慧网.getEnumEasingList("ArraylistYAxisAnimType")
    val arraylistYAxisAnimOrderValue = 明慧网.getEnumEasingOrderList("ArraylistYAxisHotbarAnimOrder")
    val fontEpsilonValue = FloatValue("FontVectorEpsilon", 0.5f, 0f, 1.5f)
    private val buttonValue = ListValue("Button", arrayOf("Better", "FLine", "Rise", "Vanilla"), "Better")

    private var lastFontEpsilon = 0f

    private var easeAnimation: Animation? = null
    private var easingValue = 0
        get() {
            if (easeAnimation != null) {
                field = easeAnimation!!.value.toInt()
                if (easeAnimation!!.state == Animation.EnumAnimationState.STOPPED) {
                    easeAnimation = null
                }
            }
            return field
        }
        set(value) {
            if (easeAnimation == null || (easeAnimation != null && easeAnimation!!.to != value.toDouble())) {
                easeAnimation = Animation(
                    明慧网.EnumEasingType.valueOf(hotbarAnimTypeValue.get()),
                    明慧网.EnumEasingOrder.valueOf(hotbarAnimOrderValue.get()),
                    field.toDouble(),
                    value.toDouble(),
                    hotbarAnimSpeedValue.get() * 30L
                ).start()
            }
        }

    @EventTarget
    fun onRender2D(event: Render2DEvent) {
        if (mc.currentScreen is GuiHudDesigner) return
        李洪志.hud.render(false, event.partialTicks)
    }

    @EventTarget
    fun onUpdate(event: UpdateEvent) {
        李洪志.hud.update()
        if (mc.currentScreen == null && lastFontEpsilon != fontEpsilonValue.get()) {
            lastFontEpsilon = fontEpsilonValue.get()
            alert("You need to reload FDPClient to apply changes!")
        }
    }

    @EventTarget
    fun onWorld(event: WorldEvent) {
        lastFontEpsilon = fontEpsilonValue.get()
    }

    @EventTarget
    fun onScreen(event: ScreenEvent) {
        if (mc.theWorld == null || mc.thePlayer == null) {
            return
        }

        if (state && blurValue.get() && !mc.entityRenderer.isShaderActive && event.guiScreen != null && !(event.guiScreen is GuiChat || event.guiScreen is GuiHudDesigner)) {
            mc.entityRenderer.loadShader(ResourceLocation("fdpclient/blur.json"))
        } else if (mc.entityRenderer.shaderGroup != null && mc.entityRenderer.shaderGroup!!.shaderGroupName.contains("fdpclient/blur.json")) {
            mc.entityRenderer.stopUseShader()
        }
    }

    @EventTarget
    fun onKey(event: KeyEvent) {
        李洪志.hud.handleKey('a', event.key)
    }
    fun getClientColors(): Array<Color>? {
        val firstColor: Color
        val secondColor: Color
        when (clolormode.get()
            .lowercase(Locale.getDefault())) {
            "light rainbow" -> {
                firstColor = ColorUtils.rainbowc(15, 1, .6f, 1F, 1F)!!
                secondColor = ColorUtils.rainbowc(15, 40, .6f, 1F, 1F)!!
            }
            "rainbow" -> {
                firstColor = ColorUtils.rainbowc(15, 1, 1F, 1F, 1F)!!
                secondColor = ColorUtils.rainbowc(15, 40, 1F, 1F, 1F)!!
            }
            "double color" -> {
                firstColor =
                    ColorUtils.interpolateColorsBackAndForth(15, 0, Color.PINK, Color.BLUE, hueInterpolation.get())!!
                secondColor =
                    ColorUtils.interpolateColorsBackAndForth(15, 90, Color.PINK, Color.BLUE, hueInterpolation.get())!!
            }
            "static" -> {
                firstColor = Color(colorRedValue.get(), colorGreenValue.get(), colorBlueValue.get())
                secondColor = firstColor
            }
            else -> {
                firstColor = Color(-1)
                secondColor = Color(-1)
            }
        }
        return arrayOf(firstColor, secondColor)
    }

    fun getHotbarEasePos(x: Int): Int {
        if (!state || !hotbarEaseValue.get()) return x
        easingValue = x
        return easingValue
    }

    fun getButtonRenderer(button: GuiButton): AbstractButtonRenderer? {
        return when (buttonValue.get().lowercase()) {
            "better" -> RoundedButtonRenderer(button)
            "fline" -> FLineButtonRenderer(button)
            "rise" -> RiseButtonRenderer(button)
            else -> null // vanilla or unknown
        }
    }
}
