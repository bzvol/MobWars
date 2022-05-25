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

    fun setKitArmor(name: String, item: ItemStack) {
        val armor = kitSet.find { it.name == name }?.armor
        val armorType = item.type
        when {
            armorType.name.endsWith("HELMET") -> armor?.helmet
            armorType.name.endsWith("CHESTPLATE") -> armor?.chestplate
            armorType.name.endsWith("LEGGINGS") -> armor?.leggings
            armorType.name.endsWith("BOOTS") -> armor?.boots
            else -> throw IllegalArgumentException("The item to set is not an armor")
        }
    }

    fun addKitItem(name: String, item: ItemStack) {
        kitSet.find { it.name == name }?.items?.add(item)
    }

    fun saveAll() {
        kitConfig.clear()
        kitSet.forEach(kitConfig::saveKit)
        kitConfig.save()
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
