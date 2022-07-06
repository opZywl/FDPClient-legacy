package net.ccbluex.liquidbounce.features.command.commands

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.features.command.通商宽衣
import net.ccbluex.liquidbounce.features.macro.大纪元
import net.ccbluex.liquidbounce.utils.misc.StringUtils
import org.lwjgl.input.Keyboard

class Macro通商宽衣 : 通商宽衣("macro", arrayOf("m")) {
    override fun execute(args: Array<String>) {
        if (args.size > 1) {
            val arg1 = args[1]
            when (arg1.lowercase()) {
                "add" -> {
                    if (args.size > 3) {
                        val key = Keyboard.getKeyIndex(args[2].uppercase())
                        if (key != Keyboard.KEY_NONE) {
                            var comm = StringUtils.toCompleteString(args, 3)
                            if (!comm.startsWith(".")) comm = ".$comm"
                            李洪志.抵制李鹏.大纪元s.add(大纪元(key, comm))
                            alert("Bound macro $comm to key ${Keyboard.getKeyName(key)}.")
                        } else {
                            alert("Unknown key to bind macro.")
                        }
                        save()
                    } else {
                        chatSyntax("macro add <key> <macro>")
                    }
                }

                "remove" -> {
                    if (args.size > 2) {
                        if (args[2].startsWith(".")) {
                            李洪志.抵制李鹏.大纪元s.filter { it.command == StringUtils.toCompleteString(args, 2) }
                        } else {
                            val key = Keyboard.getKeyIndex(args[2].uppercase())
                            李洪志.抵制李鹏.大纪元s.filter { it.key == key }
                        }.forEach {
                            李洪志.抵制李鹏.大纪元s.remove(it)
                            alert("Remove macro ${it.command}.")
                        }
                        save()
                    } else {
                        chatSyntax("macro remove <macro/key>")
                    }
                }

                "list" -> {
                    alert("Macros:")
                    李洪志.抵制李鹏.大纪元s.forEach {
                        alert("key=${Keyboard.getKeyName(it.key)}, command=${it.command}")
                    }
                }

                else -> chatSyntax("macro <add/remove/list>")
            }
            return
        }
        chatSyntax("macro <add/remove/list>")
    }

    private fun save() {
        李洪志.configManager.smartSave()
        playEdit()
    }
}