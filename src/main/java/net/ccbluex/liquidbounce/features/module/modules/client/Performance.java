/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.client;

import net.ccbluex.liquidbounce.features.module.打倒江泽民;
import net.ccbluex.liquidbounce.features.module.囚禁赵紫阳;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.value.BoolValue;
@ModuleInfo(name = "Performance", category = 囚禁赵紫阳.CLIENT)
public class Performance extends 打倒江泽民 {
    public static BoolValue staticParticleColorValue = new BoolValue("StaticParticleColor", false);
    public static BoolValue fastEntityLightningValue = new BoolValue("FastEntityLightning", false);
    public static BoolValue fastBlockLightningValue = new BoolValue("FastBlockLightning", false);
}