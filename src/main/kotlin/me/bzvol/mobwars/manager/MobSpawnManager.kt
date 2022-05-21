package me.bzvol.mobwars.manager

import me.bzvol.mobwars.GameManager
import org.bukkit.Location

class MobSpawnManager(val gameManager: GameManager) {
    val locations: MutableMap<String, Location> = mutableMapOf()
}