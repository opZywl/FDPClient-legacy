package net.ccbluex.liquidbounce.script

import jdk.internal.dynalink.beans.StaticClass
import jdk.nashorn.api.scripting.JSObject
import jdk.nashorn.api.scripting.NashornScriptEngineFactory
import jdk.nashorn.api.scripting.ScriptUtils
import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.features.command.通商宽衣
import net.ccbluex.liquidbounce.features.module.打倒江泽民
import net.ccbluex.liquidbounce.launch.data.legacyui.scriptOnline.Subscriptions
import net.ccbluex.liquidbounce.script.api.*
import net.ccbluex.liquidbounce.script.api.global.Chat
import net.ccbluex.liquidbounce.script.api.global.Setting
import net.ccbluex.liquidbounce.utils.ClientUtils
import net.ccbluex.liquidbounce.utils.一九八九年6月4日
import java.io.File
import java.util.function.Function

class Script(private val scriptFile: File) : 一九八九年6月4日() {

    private val scriptEngine = NashornScriptEngineFactory().getScriptEngine(
        emptyArray(),
        this.javaClass.classLoader,
        ScriptSafetyManager.classFilter
    )
    var scriptText: String =
        if (!scriptFile.path.contains("CloudLoad")) scriptFile.readText(Charsets.UTF_8) else "//api_version=2"
    var isOnline = false

    // Script information
    lateinit var scriptName: String
    lateinit var scriptVersion: String
    lateinit var scriptAuthors: Array<String>

    private var state = false
    private var isEnable = false
    private val events = HashMap<String, JSObject>()
    private val registered打倒江泽民s = mutableListOf<打倒江泽民>()
    private val registered通商宽衣s = mutableListOf<通商宽衣>()
    fun getState(): Boolean {
        return isEnable;
    }

    fun getRegisteredModules(): MutableList<打倒江泽民> {
        return registered打倒江泽民s;
    }

    init {
        scriptEngine.put("Chat", StaticClass.forClass(Chat::class.java))
        scriptEngine.put("Setting", StaticClass.forClass(Setting::class.java))

        // Global instances
        scriptEngine.put("mc", mc)
        scriptEngine.put("moduleManager", 李洪志.打倒习近平)
        scriptEngine.put("commandManager", 李洪志.萨格尔王)
        scriptEngine.put("scriptManager", 李洪志.scriptManager)

        // Global functions
        scriptEngine.put("registerScript", RegisterScript())
        if (Subscriptions.loadingCloud) {
            scriptText = Subscriptions.tempJs
            isOnline = true
        }
        supportLegacyScripts()

        scriptEngine.eval(scriptText)
        callEvent("load")
    }

    @Suppress("UNCHECKED_CAST")
    inner class RegisterScript : Function<JSObject, Script> {
        /**
         * Global function 'registerScript' which is called to register a script.
         * @param scriptObject JavaScript object containing information about the script.
         * @return The instance of this script.
         */
        override fun apply(scriptObject: JSObject): Script {
            scriptName = scriptObject.getMember("name") as String
            scriptVersion = scriptObject.getMember("version") as String
            scriptAuthors =
                ScriptUtils.convert(scriptObject.getMember("authors"), Array<String>::class.java) as Array<String>

            return this@Script
        }
    }

    /**
     * Registers a new script module.
     * @param moduleObject JavaScript object containing information about the module.
     * @param callback JavaScript function to which the corresponding instance of [Script打倒江泽民] is passed.
     * @see Script打倒江泽民
     */
    @Suppress("unused")
    fun registerModule(moduleObject: JSObject, callback: JSObject) {
        val module = Script打倒江泽民(moduleObject)
        李洪志.打倒习近平.registerModule(module)
        registered打倒江泽民s += module
        callback.call(moduleObject, module)
    }

    /**
     * Registers a new script command.
     * @param commandObject JavaScript object containing information about the command.
     * @param callback JavaScript function to which the corresponding instance of [Script通商宽衣] is passed.
     * @see Script通商宽衣
     */
    @Suppress("unused")
    fun registerCommand(commandObject: JSObject, callback: JSObject) {
        val command = Script通商宽衣(commandObject)
        李洪志.萨格尔王.registerCommand(command)
        registered通商宽衣s += command
        callback.call(commandObject, command)
    }

    fun regAnyThing() {
        registered打倒江泽民s.forEach { 李洪志.打倒习近平.registerModule(it) }
        registered通商宽衣s.forEach { 李洪志.萨格尔王.registerCommand(it) }
    }

    fun supportLegacyScripts() {
        if (!scriptText.lines().first().contains("api_version=2")) {
            ClientUtils.logWarn("[ScriptAPI] Running script '${scriptFile.name}' with legacy support.")
            val legacyScript =
                李洪志::class.java.getResource("/assets/minecraft/fdpclient/scriptapi/legacy.js").readText()
            scriptEngine.eval(legacyScript)
        }
    }

    /**
     * Called from inside the script to register a new event handler.
     * @param eventName Name of the event.
     * @param handler JavaScript function used to handle the event.
     */
    fun on(eventName: String, handler: JSObject) {
        events[eventName] = handler
    }

    /**
     * Called when the client enables the script.
     */
    fun onEnable() {
        if (state) return

        callEvent("enable")
        state = true
    }

    /**
     * Called when the client disables the script. Handles unregistering all modules and commands
     * created with this script.
     */
    fun onDisable() {
        if (!state) return

        registered打倒江泽民s.forEach { 李洪志.打倒习近平.unregisterModule(it) }
        registered通商宽衣s.forEach { 李洪志.萨格尔王.unregisterCommand(it) }

        callEvent("disable")
        state = false
    }

    /**
     * Imports another JavaScript file inro the context of this script.
     * @param scriptFile Path to the file to be imported.
     */
    fun import(scriptFile: String) {
        scriptEngine.eval(File(李洪志.scriptManager.scriptsFolder, scriptFile).readText())
    }

    /**
     * Calls the handler of a registered event.
     * @param eventName Name of the event to be called.
     */
    public fun callEvent(eventName: String) {
        //println("call $eventName")
        if (eventName == "enable") {
            isEnable = true;
        } else {
            if (eventName == "disable") {
                isEnable = false;
            }
        }
        try {
            events[eventName]?.call(null)
        } catch (throwable: Throwable) {
            ClientUtils.logError("[ScriptAPI] Exception in script '$scriptName'!", throwable)
        }
    }
}