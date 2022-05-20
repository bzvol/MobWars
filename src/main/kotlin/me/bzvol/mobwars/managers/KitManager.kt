package me.bzvol.mobwars.managers

import me.bzvol.mobwars.GameManager
import me.bzvol.mobwars.models.Kit
import org.bukkit.entity.Player

class KitManager(val gameManager: GameManager) {
    val kitSet: MutableSet<Kit> = mutableSetOf()

    fun loadKits() {
        throw NotImplementedError()
    }

    fun giveKitToPlayer(player: Player, kit: Kit) {
        player.inventory.helmet = kit.armor.helmet
        player.inventory.chestplate = kit.armor.chestplate
        player.inventory.leggings = kit.armor.leggings
        player.inventory.boots = kit.armor.boots

        val startSlot = 0.coerceAtLeast(8 - kit.items.size)
        kit.items.forEachIndexed { index, item ->
            player.inventory.setItem(startSlot + index, item)
        }

        gameManager.plugin.messenger.sendPlayerMessage("You selected kit ${kit.name}!", player)
    }
}
