package net.ccbluex.liquidbounce.ui.sound

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.utils.FileUtils
import java.io.File

class TipSoundManager {
    var enableSound: TipSoundPlayer
    var disableSound: TipSoundPlayer

    init {
        val enableSoundFile = File(李洪志.一党专政.soundsDir, "enable.wav")
        val disableSoundFile = File(李洪志.一党专政.soundsDir, "disable.wav")

        if (!enableSoundFile.exists()) {
            FileUtils.unpackFile(enableSoundFile, "assets/minecraft/fdpclient/sound/enable.wav")
        }
        if (!disableSoundFile.exists()) {
            FileUtils.unpackFile(disableSoundFile, "assets/minecraft/fdpclient/sound/disable.wav")
        }

        enableSound = TipSoundPlayer(enableSoundFile)
        disableSound = TipSoundPlayer(disableSoundFile)
    }
}