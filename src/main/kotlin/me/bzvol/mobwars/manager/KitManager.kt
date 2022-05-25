package me.bzvol.mobwars.manager

import me.bzvol.mobwars.GameManager
import me.bzvol.mobwars.PluginMessenger
import me.bzvol.mobwars.config.KitConfig
import me.bzvol.mobwars.gui.KitSelectorGUI
import me.bzvol.mobwars.model.Kit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class KitManager(private val gameManager: GameManager) {
    val kitSet: MutableSet<Kit> = mutableSetOf()
    val kitSelectorGUI = KitSelectorGUI(this)

    private val kitConfig = KitConfig()

    init {
        for (kitName in kitConfig.getKitNames())
            kitSet.add(kitConfig.loadKit(kitName))
    }

    fun createEmptyKit(name: String) = kitSet.add(Kit(name))

    fun setKitArmor(name: String, armorPart: String, item: ItemStack) {
        val armor = kitSet.find { it.name == name }?.armor
        when (armorPart) {
            "helmet" -> armor?.helmet
            "chestplate" -> armor?.chestplate
            "leggings" -> armor?.leggings
            "boots" -> armor?.boots
        }
    }

    fun addKitItem(name: String, item: ItemStack) {
        kitSet.find { it.name == name }?.items?.add(item)
    }

    fun saveAll() {
        TODO()
    }

    fun giveKitToPlayer(player: Player, kit: Kit) {
        player.inventory.helmet = kit.armor.helmet
        player.inventory.chestplate = kit.armor.chestplate
        player.inventory.leggings = kit.armor.leggings
        player.inventory.boots = kit.armor.boots

        val startSlot = 3.coerceAtLeast(9 - kit.items.size)
        kit.items.forEachIndexed { index, item ->
            player.inventory.setItem(startSlot + index, item)
        }

        PluginMessenger.sendPlayerMessage("You selected kit ${kit.name}!", player)
    }
}
