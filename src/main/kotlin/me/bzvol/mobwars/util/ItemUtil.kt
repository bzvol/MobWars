package me.bzvol.mobwars.util

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta

class ItemUtil {
    companion object {
        fun enchantedNamedItem(material: Material, name: String): ItemStack {
            val stack = ItemStack(material)
            val meta = stack.itemMeta

            meta?.addItemFlags(ItemFlag.HIDE_ENCHANTS)
            meta?.setDisplayName(name)

            stack.addUnsafeEnchantment(Enchantment.LURE, 1)
            stack.itemMeta = meta

            return stack
        }
    }
}