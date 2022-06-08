package me.bzvol.mobwars.manager

import me.bzvol.mobwars.GameManager
import me.bzvol.mobwars.PluginMessenger
import org.bukkit.entity.Player

class MobManager(val gameManager: GameManager) {
    fun addMob(p: Player, mobName: String) {
        PluginMessenger.sendPlayerMessage("Click the mob that you want to register.", p)
    }
}