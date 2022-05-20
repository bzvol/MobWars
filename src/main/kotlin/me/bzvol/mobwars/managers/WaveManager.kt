package me.bzvol.mobwars.managers

import me.bzvol.mobwars.GameManager
import me.bzvol.mobwars.models.Wave

class WaveManager(val gameManager: GameManager) {
    var waveCount = 0
    val waves: MutableSet<Wave> = mutableSetOf()
}