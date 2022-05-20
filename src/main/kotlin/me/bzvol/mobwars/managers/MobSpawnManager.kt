package me.bzvol.mobwars.managers

import me.bzvol.mobwars.GameManager
import org.bukkit.Location

class MobSpawnManager(val gameManager: GameManager) {
    val locations: MutableMap<String, Location> = mutableMapOf()
}