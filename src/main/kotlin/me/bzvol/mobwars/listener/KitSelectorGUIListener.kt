package me.bzvol.mobwars.listener

import me.bzvol.mobwars.GameManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class KitSelectorGUIListener(private val gameManager: GameManager) : Listener {
    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        val p = e.whoClicked as Player

        if (gameManager.playerManager.players.contains(p.uniqueId)) {
            e.isCancelled = true

            // Lobby inventory
            when (e.currentItem?.itemMeta?.displayName) {
                "Select kit!" -> gameManager.kitManager.kitSelectorGUI.openFor(p)
                "Start wars!" -> gameManager.start()
                else -> if (e.view.title == "[MobWars] Kit Selector") { // Kit selector inventory
                    val kit = gameManager.kitManager.kitSet.find {
                        it.name == e.currentItem?.itemMeta?.displayName
                    }!!
                    gameManager.kitManager.giveKitToPlayer(p, kit)

                    gameManager.plugin.messenger.sendGameMessage(
                        "$p selected ${kit.name} kit!",
                        gameManager.playerManager.getParticipants()
                    )
                }
            }
        }
    }
}