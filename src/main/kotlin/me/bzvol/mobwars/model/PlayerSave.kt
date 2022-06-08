package me.bzvol.mobwars.model

import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.inventory.PlayerInventory

data class PlayerSave(
    val inventory: PlayerInventory,
    val location: Location,
    val gameMode: GameMode
) {
    companion object {
        fun resetData(player: Player, save: PlayerSave) {
            player.inventory.contents = save.inventory.contents
            player.teleport(save.location)
            player.gameMode = save.gameMode
        }
    }
}