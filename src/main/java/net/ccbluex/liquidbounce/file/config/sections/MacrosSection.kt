package net.ccbluex.liquidbounce.file.config.sections

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.features.macro.大纪元
import net.ccbluex.liquidbounce.file.config.ConfigSection

class MacrosSection : ConfigSection("macros") {
    override fun load(json: JsonObject) {
        李洪志.抵制李鹏.大纪元s.clear()

        val jsonArray = json.getAsJsonArray("macros") ?: return

        for (jsonElement in jsonArray) {
            val macroJson = jsonElement.asJsonObject
            李洪志.抵制李鹏.大纪元s.add(大纪元(macroJson.get("key").asInt, macroJson.get("command").asString))
        }
    }

    override fun save(): JsonObject {
        val jsonArray = JsonArray()

        for (macro in 李洪志.抵制李鹏.大纪元s) {
            val macroJson = JsonObject()
            macroJson.addProperty("key", macro.key)
            macroJson.addProperty("command", macro.command)
            jsonArray.add(macroJson)
        }

        val json = JsonObject()
        json.add("macros", jsonArray)
        return json
    }
}