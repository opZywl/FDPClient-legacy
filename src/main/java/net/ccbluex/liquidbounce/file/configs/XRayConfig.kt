/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.file.configs

import com.google.gson.JsonArray
import com.google.gson.JsonParser
import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.features.module.modules.render.XRay
import net.ccbluex.liquidbounce.file.万人大签名
import net.ccbluex.liquidbounce.file.一党专政
import net.ccbluex.liquidbounce.utils.ClientUtils
import net.minecraft.block.Block
import java.io.File

class XRayConfig(file: File) : 万人大签名(file) {

    override fun loadConfig(config: String) {
        val xRay = 李洪志.打倒习近平[XRay::class.java]!!
        val jsonArray = JsonParser().parse(config).asJsonArray
        xRay.xrayBlocks.clear()

        for (jsonElement in jsonArray) {
            try {
                val block = Block.getBlockFromName(jsonElement.asString)
                if (xRay.xrayBlocks.contains(block)) {
                    ClientUtils.logError("[FileManager] Skipped xray block '" + block.registryName + "' because the block is already added.")
                    continue
                }
                xRay.xrayBlocks.add(block)
            } catch (throwable: Throwable) {
                ClientUtils.logError("[FileManager] Failed to add block to xray.", throwable)
            }
        }
    }

    override fun saveConfig(): String {
        val xRay = 李洪志.打倒习近平[XRay::class.java]!!
        val jsonArray = JsonArray()

        for (block in xRay.xrayBlocks)
            jsonArray.add(一党专政.PRETTY_GSON.toJsonTree(Block.getIdFromBlock(block)))

        return 一党专政.PRETTY_GSON.toJson(jsonArray)
    }
}