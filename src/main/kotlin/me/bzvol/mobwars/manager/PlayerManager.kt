package me.bzvol.mobwars.manager

import me.bzvol.mobwars.GameManager
import me.bzvol.mobwars.model.PlayerSave
import org.bukkit.GameMode
import org.bukkit.entity.Player
import java.util.*

class PlayerManager(val gameManager: GameManager) {
    val players: MutableSet<UUID> = mutableSetOf()
    val spectators: MutableSet<UUID> = mutableSetOf()

    val playerSaves: MutableMap<UUID, PlayerSave> = mutableMapOf()

    fun addPlayer(p: Player) {
        players.add(p.uniqueId)
        playerSaves[p.uniqueId] = PlayerSave(p.inventory, p.location, p.gameMode)
        p.inventory.clear()
        p.gameMode = GameMode.ADVENTURE
        p.teleport(gameManager.lobbyLoc)
    }

    fun addSpectator(p: Player) {
        spectators.add(p.uniqueId)
        playerSaves[p.uniqueId] = PlayerSave(p.inventory, p.location, p.gameMode)
        p.inventory.clear()
        p.gameMode = GameMode.CREATIVE
        p.isInvisible = true
        p.teleport(gameManager.specLoc)
    }

    fun removePlayer(p: Player) {
        if (players.contains(p.uniqueId)) {
            players.remove(p.uniqueId)
            PlayerSave.resetData(p, playerSaves[p.uniqueId]!!)
        }
        else if (spectators.contains(p.uniqueId)) {
            spectators.remove(p.uniqueId)
            PlayerSave.resetData(p, playerSaves[p.uniqueId]!!)
        }
        else gameManager.plugin.messenger.sendPlayerMessage("You are not participating in a game.", p)
    }

    fun setPlayerInventory() {}
}
