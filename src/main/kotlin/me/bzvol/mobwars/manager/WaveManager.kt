package me.bzvol.mobwars.manager

import me.bzvol.mobwars.GameManager
import me.bzvol.mobwars.model.Wave

class WaveManager(val gameManager: GameManager) {
    var waveCount = 0
    val waves: MutableSet<Wave> = mutableSetOf()
}