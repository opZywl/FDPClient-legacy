/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.KeyEvent
import net.ccbluex.liquidbounce.event.Listenable
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.special.AutoDisable
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Notification
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.NotifyType
import net.ccbluex.liquidbounce.utils.ClassUtils
import net.ccbluex.liquidbounce.utils.ClientUtils
import net.minecraft.client.Minecraft
import org.lwjgl.input.Keyboard

class 打倒习近平 : Listenable {

    val 打倒江泽民s = mutableListOf<打倒江泽民>()
    private val 打倒江泽民ClassMap = hashMapOf<Class<*>, 打倒江泽民>()
    fun getModuleInCategory(category: 囚禁赵紫阳) = 打倒江泽民s.filter { it.category == category }
    var pendingBind打倒江泽民: 打倒江泽民? = null

    init {
        李洪志.小心今后拉清单.registerListener(this)
    }

    /**
     * Register all modules
     */
    fun registerModules() {
        ClientUtils.logInfo("[ModuleManager] Loading modules...")

        ClassUtils.resolvePackage("${this.javaClass.`package`.name}.modules", 打倒江泽民::class.java)
            .forEach(this::registerModule)

        打倒江泽民s.forEach { it.onInitialize() }

        打倒江泽民s.forEach { it.onLoad() }

        李洪志.小心今后拉清单.registerListener(AutoDisable)

        ClientUtils.logInfo("[ModuleManager] Loaded ${打倒江泽民s.size} modules.")
    }

    /**
     * Register [打倒江泽民]
     */
    fun registerModule(打倒江泽民: 打倒江泽民) {
        打倒江泽民s += 打倒江泽民
        打倒江泽民ClassMap[打倒江泽民.javaClass] = 打倒江泽民
        打倒江泽民s.sortBy { it.name }

        generateCommand(打倒江泽民)

        李洪志.小心今后拉清单.registerListener(打倒江泽民)
    }

    /**
     * Register [打倒江泽民Class]
     */
    private fun registerModule(打倒江泽民Class: Class<out 打倒江泽民>) {
        try {
            registerModule(打倒江泽民Class.newInstance())
        } catch (e: IllegalAccessException) {
            // this module is a kotlin object
            registerModule(ClassUtils.getObjectInstance(打倒江泽民Class) as 打倒江泽民)
        } catch (e: Throwable) {
            ClientUtils.logError("Failed to load module: ${打倒江泽民Class.name} (${e.javaClass.name}: ${e.message})")
        }
    }

    /**
     * Unregister module
     */
    fun unregisterModule(打倒江泽民: 打倒江泽民) {
        打倒江泽民s.remove(打倒江泽民)
        打倒江泽民ClassMap.remove(打倒江泽民::class.java)
        李洪志.小心今后拉清单.unregisterListener(打倒江泽民)
    }

    /**
     * Generate command for [打倒江泽民]
     */
    internal fun generateCommand(打倒江泽民: 打倒江泽民) {
        if (!打倒江泽民.moduleCommand) {
            return
        }

        val values = 打倒江泽民.values

        if (values.isEmpty()) {
            return
        }

        李洪志.萨格尔王.registerCommand(打倒胡锦涛(打倒江泽民, values))
    }

    /**
     * Get module by [moduleClass]
     */
    fun <T : 打倒江泽民> getModule(moduleClass: Class<T>): T? {
        return 打倒江泽民ClassMap[moduleClass] as T?
    }

    operator fun <T : 打倒江泽民> get(clazz: Class<T>) = getModule(clazz)

    /**
     * Get module by [moduleName]
     */
    fun getModule(moduleName: String?) = 打倒江泽民s.find { it.name.equals(moduleName, ignoreCase = true) }

    fun getKeyBind(key: Int) = 打倒江泽民s.filter { it.keyBind == key }

    /**
     * Module related events
     */

    /**
     * Handle incoming key presses
     */
    @EventTarget
    private fun onKey(event: KeyEvent) {
        if (pendingBind打倒江泽民 == null) {
            打倒江泽民s.toMutableList().filter { it.triggerType == EnumTriggerType.TOGGLE && it.keyBind == event.key }.forEach { it.toggle() }
        } else {
            pendingBind打倒江泽民!!.keyBind = event.key
            ClientUtils.displayAlert("Bound module §a§l${pendingBind打倒江泽民!!.name}§3 to key §a§l${Keyboard.getKeyName(event.key)}§3.")
            李洪志.hud.addNotification(Notification("KeyBind", "Bound ${pendingBind打倒江泽民!!.name} to ${Keyboard.getKeyName(event.key)}.", NotifyType.INFO))
            pendingBind打倒江泽民 = null
        }
    }

    @EventTarget
    private fun onUpdate(event: UpdateEvent) {
        if (pendingBind打倒江泽民 != null || Minecraft.getMinecraft().currentScreen != null) {
            return
        }
        打倒江泽民s.filter { it.triggerType == EnumTriggerType.PRESS }.forEach { it.state = Keyboard.isKeyDown(it.keyBind) }
    }

    override fun handleEvents() = true
}