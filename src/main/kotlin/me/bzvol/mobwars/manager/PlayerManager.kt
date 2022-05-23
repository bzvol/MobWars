package me.bzvol.mobwars.manager

import me.bzvol.mobwars.GameManager
import me.bzvol.mobwars.PluginMessenger
import me.bzvol.mobwars.model.PlayerSave
import me.bzvol.mobwars.util.ItemUtil
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*

class PlayerManager(val gameManager: GameManager) {
    val players: MutableSet<UUID> = mutableSetOf()
    val spectators: MutableSet<UUID> = mutableSetOf()

    private val playerSaves: MutableMap<UUID, PlayerSave> = mutableMapOf()

    fun addPlayer(p: Player) {
        if (isParticipant(p)) return

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
        } else if (spectators.contains(p.uniqueId)) {
            spectators.remove(p.uniqueId)
            PlayerSave.resetData(p, playerSaves[p.uniqueId]!!)
        } else PluginMessenger.sendPlayerMessage("You are not participating in a game.", p)
    }

    @Suppress("LocalVariableName")
    fun getParticipants(): List<Player?> {
        val players_ = players.map { Bukkit.getPlayer(it) }
        val spectators_ = spectators.map { Bukkit.getPlayer(it) }
        return players_.plus(spectators_)
    }

    fun isParticipant(p: Player): Boolean = players.contains(p.uniqueId) || spectators.contains(p.uniqueId)

    fun setPlayerInventory(p: Player) {
        val kitSelectItem = ItemUtil.enchantedNamedItem(Material.LEATHER_CHESTPLATE, "Select kit!")
        val startItem = ItemUtil.enchantedNamedItem(Material.EMERALD, "Start wars!")

        p.inventory.addItem(kitSelectItem, startItem)
    }
}
