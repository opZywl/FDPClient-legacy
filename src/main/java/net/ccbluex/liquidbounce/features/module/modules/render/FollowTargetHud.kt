/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.render

import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.Render3DEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.features.module.modules.misc.AntiBot
import net.ccbluex.liquidbounce.features.module.modules.misc.Teams
import net.ccbluex.liquidbounce.features.module.modules.player.HackerDetector
import net.ccbluex.liquidbounce.ui.font.Fonts
import net.ccbluex.liquidbounce.utils.EntityUtils
import net.ccbluex.liquidbounce.utils.extensions.ping
import net.ccbluex.liquidbounce.utils.render.ColorUtils
import net.ccbluex.liquidbounce.utils.render.RenderUtils.*
import net.ccbluex.liquidbounce.value.*
import net.minecraft.client.renderer.GlStateManager.*
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import org.lwjgl.opengl.GL11.*
import java.awt.Color
import kotlin.math.roundToInt

@ModuleInfo(name = "FollowTargetHud", category = ModuleCategory.RENDER)
class FollowTargetHud : Module() {
    private val modeValue = ListValue("Mode", arrayOf("Juul", "Jello"), "Juul")
    private val fontValue = FontValue("Font", Fonts.font40)
    private val jelloColorValue = BoolValue("JelloHPColor", true).displayable { modeValue.equals("Jello") }
    private val jelloAlphaValue = IntegerValue("JelloAlpha", 170, 0, 255).displayable { modeValue.equals("Jello") }
    private val scaleValue = FloatValue("Scale", 1F, 1F, 4F)
    private val onlyTarget = BoolValue("OnlyTarget",true)
    private val translateY = FloatValue("TanslateY", 0.55F,-2F,2F)

    private var targetTicks = 0
    private var entityKeep = "yes"

    @EventTarget
    fun onRender3D(event: Render3DEvent) {
        for (entity in mc.theWorld.loadedEntityList) {
            if (EntityUtils.isSelected(entity, false)) {
                renderNameTag(entity as EntityLivingBase, entity.name)
            }
        }
    }

    private fun getPlayerName(entity: EntityLivingBase): String {
        val name = entity.displayName.formattedText
        var pre = ""
        val teams = LiquidBounce.moduleManager[Teams::class.java]!!
        if (LiquidBounce.fileManager.friendsConfig.isFriend(entity.name)) {
            pre = "$pre§b[Friend] "
        }
        if (teams.isInYourTeam(entity)) {
            pre = "$pre§a[TEAM] "
        }
        if (AntiBot.isBot(entity)) {
            pre = "$pre§e[BOT] "
        }
        if (!AntiBot.isBot(entity) && !teams.isInYourTeam(entity)) {
            pre = if (LiquidBounce.fileManager.friendsConfig.isFriend(entity.name)) {
                "§b[Friend] §c"
            } else {
                "§c"
            }
        }
        return name + pre
    }

    private fun renderNameTag(entity: EntityLivingBase, tag: String) {
        if (onlyTarget.get() && entity != LiquidBounce.combatManager.target && entity.getName() != entityKeep) {
            return
        } else if (onlyTarget.get() && entity == LiquidBounce.combatManager.target) {
            entityKeep = entity.getName()
            targetTicks++
            if (targetTicks >= 5) {
                targetTicks = 4
            }
        } else if (onlyTarget.get() && LiquidBounce.combatManager.target == null) {
            targetTicks--
            if (targetTicks <= -1) {
                targetTicks = 0
                entityKeep = "dg636 top"
            }
        }

        if (onlyTarget.get() && targetTicks == 0) {
            return
        }
        
        // Set fontrenderer local
        val fontRenderer = fontValue.get()

        // Push
        glPushMatrix()

        // Translate to player position
        val renderManager = mc.renderManager
        val timer = mc.timer

        glTranslated( // Translate to player position with render pos and interpolate it
            entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * timer.renderPartialTicks - renderManager.renderPosX,
            entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * timer.renderPartialTicks - renderManager.renderPosY + entity.eyeHeight.toDouble() + translateY.get().toDouble(),
            entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * timer.renderPartialTicks - renderManager.renderPosZ
        )

        // Rotate view to player
        glRotatef(-mc.renderManager.playerViewY, 0F, 1F, 0F)
        glRotatef(mc.renderManager.playerViewX, 1F, 0F, 0F)

        // Scale
        var distance = mc.thePlayer.getDistanceToEntity(entity) / 4F

        if (distance < 1F) {
            distance = 1F
        }

        val scale = (distance / 150F) * scaleValue.get()

        // Disable lightning and depth test
        disableGlCap(GL_LIGHTING, GL_DEPTH_TEST)

        // Enable blend
        enableGlCap(GL_BLEND)
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)

        // Draw nametag
        when (modeValue.get().lowercase()) {

          
            "juul" -> {
                // colors
                val name = entity.displayName.unformattedText
                var healthPercent = entity.health / entity.maxHealth
                // render hp bar
                if (healthPercent> 1) {
                    healthPercent = 1F
                }

                // render bg
                glScalef(-scale * 2, -scale * 2, scale * 2)
                drawRoundedCornerRect(-120f, -16f, -50f, 10f, 5f, Color(64, 64, 64, 255).rgb)
                drawRoundedCornerRect(-110f, 0f, -20f, 35f, 5f, Color(96, 96, 96, 255).rgb)

                // draw strings
                fontRenderer.drawString("Attacking", -105, -13, Color.WHITE.rgb)
                fontRenderer.drawString(tag, -106, 10, Color.WHITE.rgb)
                
                val healthString = ( ( ( entity.health * 10f ).toInt() ).toFloat() * 0.1f ).toString() + " / 20" 
                fontRenderer.drawString(healthString, -25 - fontRenderer.getStringWidth(healthString).toInt(), 22, Color.WHITE.rgb)
                
                val distanceString = "⤢" + ( ( ( mc.thePlayer.getDistanceToEntity(entity) * 10f ).toInt() ).toFloat() * 0.1f ).toString() 
                fontRenderer.drawString(tag, -25 - fontRenderer.getStringWidth(distanceString).toInt(), 10, Color.WHITE.rgb)
                
                // draw health bars
                drawRoundedCornerRect(-104f, 22f, -50f, 30f, 1f, Color(64, 64, 64, 255).rgb) 
                drawRoundedCornerRect(-104f, 22f, -104f + (healthPercent * 54), 30f, 1f, Color.WHITE.rgb)
                


            }

            "jello" -> {
                // colors
                var hpBarColor = Color(255, 255, 255, jelloAlphaValue.get())
                val name = entity.displayName.unformattedText
                if (jelloColorValue.get() && name.startsWith("§")) {
                    hpBarColor = ColorUtils.colorCode(name.substring(1, 2), jelloAlphaValue.get())
                }
                val bgColor = Color(50, 50, 50, jelloAlphaValue.get())
                val width = fontRenderer.getStringWidth(tag)
                val maxWidth = (width + 4F) - (-width - 4F)
                var healthPercent = entity.health / entity.maxHealth

                // render bg
                glScalef(-scale * 2, -scale * 2, scale * 2)
                drawRect(0F, -fontRenderer.FONT_HEIGHT * 3F, width + 8F, -3F, bgColor)

                // render hp bar
                if (healthPercent> 1) {
                    healthPercent = 1F
                }

                drawRect(0F, -3F, maxWidth * healthPercent, 1F, hpBarColor)
                drawRect(maxWidth * healthPercent, -3F, width + 8F, 1F, bgColor)

                // string
                fontRenderer.drawString(tag, 4, -fontRenderer.FONT_HEIGHT * 2 - 4, Color.WHITE.rgb)
                glScalef(0.5F, 0.5F, 0.5F)
                fontRenderer.drawString("Health: " + entity.health.toInt(), 4, -fontRenderer.FONT_HEIGHT * 2, Color.WHITE.rgb)
            }
        }
        // Reset caps
        resetCaps()

        // Reset color
        resetColor()
        glColor4f(1F, 1F, 1F, 1F)

        // Pop
        glPopMatrix()
    }
}
