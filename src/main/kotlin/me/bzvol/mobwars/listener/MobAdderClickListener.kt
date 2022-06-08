package me.bzvol.mobwars.listener

import me.bzvol.mobwars.GameManager
import org.bukkit.entity.Mob
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import java.util.*

class MobAdderClickListener(private val gameManager: GameManager) : Listener {
    @EventHandler
    fun onMobClick(e: PlayerInteractEntityEvent) {
        if (!playersToListen.contains(e.player.uniqueId)) return
        if (e.rightClicked !is Mob) return

        playersToListen.remove(e.player.uniqueId)
    }

    companion object {
        private val playersToListen = mutableSetOf<UUID>()

        fun addPlayerToListen(p: Player) = playersToListen.add(p.uniqueId)
    }
}