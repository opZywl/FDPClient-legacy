package net.ccbluex.liquidbounce.ui.client.hud.element.elements

import net.ccbluex.liquidbounce.ui.client.hud.element.Border
import net.ccbluex.liquidbounce.ui.client.hud.element.Element
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo
import net.ccbluex.liquidbounce.ui.font.Fonts
import net.ccbluex.liquidbounce.utils.render.RenderUtils
import net.ccbluex.liquidbounce.value.FloatValue
import net.ccbluex.liquidbounce.value.IntegerValue
import net.ccbluex.liquidbounce.value.ListValue
import net.minecraft.block.material.Material
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.opengl.GL11
import java.awt.Color
import kotlin.jvm.internal.Intrinsics

@ElementInfo(name = "Armor")
class Armor : Element() {
    var colorModeValue = ListValue("Text-Color", arrayOf("Custom", "Astolfo"), "Custom")
    var brightnessValue = FloatValue("Brightness", 1.0f, 0.0f, 1.0f)
    var redValue = IntegerValue("Text-R", 255, 0, 255)
    var greenValue = IntegerValue("Text-G", 255, 0, 255)
    var blueValue = IntegerValue("Text-B", 255, 0, 255)
    var newRainbowIndex = IntegerValue("NewRainbowOffset", 1, 1, 50)
    var saturationValue = FloatValue("Saturation", 0.9f, 0.0f, 1.0f)
    var speed = IntegerValue("AllSpeed", 0, 0, 400)

    override fun drawElement(partialTicks: Float): Border? {
        var x2 = 0
        val playercontrollermp = `access$getMc$p$s1046033730`().playerController
        Intrinsics.checkExpressionValueIsNotNull(playercontrollermp, "mc.playerController")
        if (playercontrollermp.isNotCreative) {
            GL11.glPushMatrix()
            val minecraft = `access$getMc$p$s1046033730`()
            Intrinsics.checkExpressionValueIsNotNull(minecraft, "mc")
            val renderItem = minecraft.renderItem
            val isInsideWater = `access$getMc$p$s1046033730`().thePlayer.isInsideOfMaterial(Material.water)
            var x = 1
            var i = 0
            val y = if (isInsideWater) -10 else 0
            val colorMode = colorModeValue.get()
            val color = Color(
                (redValue.get() as Number).toInt(),
                (greenValue.get() as Number).toInt(),
                (blueValue.get() as Number).toInt()
            ).rgb
            val rainbow = colorMode.equals("Rainbow", true)
            var index = 0
            val b0: Byte = 3
            while (index <= b0) {
                if (`access$getMc$p$s1046033730`().thePlayer.inventory.armorInventory[index] != null) {
                    x2 += 20
                }
                ++index
            }
            RenderUtils.drawRect(-2.0f, -4.0f, 2.0f + x2.toFloat(), 29.0f, Color(50, 50, 50, 60))
            index = 3
            val flag = false
            while (index >= 0) {
                val colorall = if (rainbow) 0 else if (colorMode.equals(
                        "Astolfo",
                        true
                    )
                ) RenderUtils.Astolfo(
                    index * (speed.get() as Number).toInt(),
                    (saturationValue.get() as Number).toFloat(),
                    (brightnessValue.get() as Number).toFloat()
                ) else color
                val itemstack = `access$getMc$p$s1046033730`().thePlayer.inventory.armorInventory[index]
                if (itemstack != null) {
                    RenderUtils.drawGradientSidewaysV(
                        x.toDouble(),
                        0.0,
                        x.toDouble() + 18.0,
                        17.0,
                        colorall,
                        Color(140, 140, 140, 40).rgb
                    )
                    Fonts.font24.drawStringWithShadow(
                        (itemstack.maxDamage - itemstack.itemDamage).toString(),
                        x.toFloat() + 4.0f,
                        20.0f,
                        colorall
                    )
                    RenderUtils.drawRect(x.toFloat(), +25.0f, x.toFloat() + 18.0f, 26.0f, Color(140, 140, 140, 220).rgb)
                    RenderUtils.drawRect(
                        x.toFloat(),
                        +25.0f,
                        x.toFloat() + 18.0f * (itemstack.maxDamage - itemstack.itemDamage).toFloat() / itemstack.maxDamage.toFloat(),
                        26.0f,
                        colorall
                    )
                    renderItem.renderItemIntoGUI(itemstack, x + 1, y)
                    x += 20
                    ++i
                }
                --index
            }
            GlStateManager.enableAlpha()
            GlStateManager.disableBlend()
            GlStateManager.disableLighting()
            GlStateManager.disableCull()
            GL11.glPopMatrix()
        }
        return Border(-2.0f, -4.0f, 82.0f, 29.0f)
    }

    companion object {
        fun `access$getMc$p$s1046033730`(): Minecraft {
            return mc
        }
    }
}
