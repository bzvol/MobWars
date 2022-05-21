package me.bzvol.mobwars.gui

import me.bzvol.mobwars.manager.KitManager
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta

class KitSelectorGUI(private val kitManager: KitManager) : Listener {
    private val inv: Inventory = Bukkit.createInventory(null, 9, "[MobWars] Kit Selector")

    init {
        setItems()
    }

    private fun setItems() {
        kitManager.kitSet.forEach { kit ->
            val item = kit.armor.chestplate
            val meta = item.itemMeta
            meta?.setDisplayName(kit.name)
            item.itemMeta = meta
            inv.addItem(item)
        }
    }

    fun openFor(p: Player) {
        p.openInventory(this.inv)
    }
}