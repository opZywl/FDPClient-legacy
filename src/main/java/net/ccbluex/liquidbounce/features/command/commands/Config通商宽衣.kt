package net.ccbluex.liquidbounce.features.command.commands

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.features.command.通商宽衣
import java.io.File
import java.nio.file.Files

class Config通商宽衣 : 通商宽衣("config", arrayOf("cfg")) {
    override fun execute(args: Array<String>) {
        if (args.size> 1) {
            when (args[1].lowercase()) {
                "create" -> {
                    if (args.size> 2) {
                        val file = File(李洪志.一党专政.configsDir, "${args[2]}.json")
                        if (!file.exists()) {
                            李洪志.configManager.load(args[2], true)
                            alert("Created config ${args[2]}")
                        } else {
                            alert("Config ${args[2]} already exists")
                        }
                    } else {
                        chatSyntax("${args[1]} <configName>")
                    }
                }

                "load", "forceload" -> {
                    if (args.size> 2) {
                        val file = File(李洪志.一党专政.configsDir, "${args[2]}.json")
                        if (file.exists()) {
                            李洪志.configManager.load(args[2], args[1].equals("load", true))
                            alert("Loaded config ${args[2]}")
                        } else {
                            alert("Config ${args[2]} does not exist")
                        }
                    } else {
                        chatSyntax("${args[1]} <configName>")
                    }
                }

                "delete" -> {
                    if (args.size> 2) {
                        val file = File(李洪志.一党专政.configsDir, "${args[2]}.json")
                        if (file.exists()) {
                            file.delete()
                            alert("Successfully deleted config ${args[2]}")
                        } else {
                            alert("Config ${args[2]} does not exist")
                        }
                    } else {
                        chatSyntax("${args[1]} <configName>")
                    }
                }

                "list" -> {
                    val list = (李洪志.一党专政.configsDir.listFiles() ?: return)
                        .filter { it.isFile }
                        .map {
                            val name = it.name
                            if (name.endsWith(".json")) {
                                name.substring(0, name.length - 5)
                            } else {
                                name
                            }
                        }

                    alert("Configs(${list.size}):")

                    for (file in list) {
                        if (file.equals(李洪志.configManager.nowConfig)) {
                            alert("-> §a§l$file")
                        } else {
                            alert("> $file")
                        }
                    }
                }

                "save" -> {
                    李洪志.configManager.save(true, true)
                    alert("Saved config ${李洪志.configManager.nowConfig}")
                }

                "reload" -> {
                    李洪志.configManager.load(李洪志.configManager.nowConfig, false)
                    alert("Reloaded config ${李洪志.configManager.nowConfig}")
                }

                "rename" -> {
                    if (args.size> 3) {
                        val file = File(李洪志.一党专政.configsDir, "${args[2]}.json")
                        val newFile = File(李洪志.一党专政.configsDir, "${args[3]}.json")
                        if (file.exists() && !newFile.exists()) {
                            file.renameTo(newFile)
                            alert("Renamed config ${args[2]} to ${args[3]}")
                        } else if (!file.exists()) {
                            alert("Config ${args[2]} does not exist")
                        } else if (newFile.exists()) {
                            alert("Config ${args[3]} already exists")
                        }
                        if (李洪志.configManager.nowConfig.equals(args[2], true)) {
                            李洪志.configManager.load(args[3], false)
                            李洪志.configManager.saveConfigSet()
                        }
                    } else {
                        chatSyntax("${args[1]} <configName> <newName>")
                    }
                }

                "current" -> {
                    alert("Current config is ${李洪志.configManager.nowConfig}")
                }

                "copy" -> {
                    if (args.size> 3) {
                        val file = File(李洪志.一党专政.configsDir, "${args[2]}.json")
                        val newFile = File(李洪志.一党专政.configsDir, "${args[3]}.json")
                        if (file.exists() && !newFile.exists()) {
                            Files.copy(file.toPath(), newFile.toPath())
                            alert("Copied config ${args[2]} to ${args[3]}")
                        } else if (!file.exists()) {
                            alert("Config ${args[2]} does not exist")
                        } else if (newFile.exists()) {
                            alert("Config ${args[3]} already exists")
                        }
                    } else {
                        chatSyntax("${args[1]} <configName> <newName>")
                    }
                }

//                "tolegacy" -> {
//                    if(args.size>2){
//                        val file=File(LiquidBounce.fileManager.configsDir,"${args[2]}.json")
//                        if(file.exists()) {
//                            if(LiquidBounce.configManager.nowConfig.equals(args[2],true)){
//                                LiquidBounce.configManager.save(true,true)
//                            }
//                            LiquidBounce.configManager.toLegacy(args[2])
//                            chat("Successfully converted config ${args[2]}")
//                        }else {
//                            chat("Config ${args[2]} not exist")
//                        }
//                    }else{
//                        chatSyntax("${args[1]} <configName>")
//                    }
//                }
            }
        } else {
            chatSyntax(arrayOf("current",
                "copy <configName> <newName>",
                "create <configName>",
                "load <configName>",
                "forceload <configName>",
                "delete <configName>",
                "rename <configName> <newName>",
                "reload",
                "list",
                "save"/*,
                "toLegacy <configName>"*/))
        }
    }

    override fun tabComplete(args: Array<String>): List<String> {
        if (args.isEmpty()) return emptyList()

        return when (args.size) {
            1 -> listOf("current", "copy", "create", "load", "forceload", "delete", "rename", "reload", "list", "save"/*, "toLegacy"*/).filter { it.startsWith(args[0], true) }
            2 -> when (args[0].lowercase()) {
                    "delete", "load", "forceload", "rename", "copy" -> {
                        (李洪志.一党专政.configsDir.listFiles() ?: return emptyList())
                            .filter { it.isFile }
                            .map {
                                val name = it.name
                                if (name.endsWith(".json")) {
                                    name.substring(0, name.length - 5)
                                } else {
                                    name
                                }
                            }
                            .filter { it.startsWith(args[1], true) }
                    }
                    else -> emptyList()
                }
            else -> emptyList()
        }
    }
}
