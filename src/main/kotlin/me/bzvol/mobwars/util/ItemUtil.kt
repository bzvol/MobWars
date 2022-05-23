package me.bzvol.mobwars.util

import org.bukkit.Material
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

        // Enchants, flags, unbreakable, display name (+ lore)
        fun itemToConfig(item: ItemStack): Map<String, Any> {
            val key = item.data?.itemType?.key?.key
            val enchants = item.enchantments
            val flags = item.itemMeta?.itemFlags
            val unbreakable = item.itemMeta?.isUnbreakable
            val displayName = item.itemMeta?.displayName
            val lore = item.itemMeta?.lore

            val armorColor =
                if (item.itemMeta is LeatherArmorMeta) (item.itemMeta as LeatherArmorMeta).color.toString() else null

            val namespacedEnchants = enchants.map { it.key.key.key to it.value }.toMap()
            val namespacedFlags = flags?.map { it.name }

            val itemConfig = mutableMapOf<String, Any>(
                "enchants" to namespacedEnchants
            )

            when {
                key != null -> itemConfig["name"] = key
                namespacedFlags != null -> itemConfig["flags"] = namespacedFlags
                unbreakable != null -> itemConfig["unbreakable"] = unbreakable
                displayName != null -> itemConfig["displayName"] = displayName
                lore != null -> itemConfig["lore"] = lore
                armorColor != null -> itemConfig["color"] = armorColor
            }

            return itemConfig
        }

        fun configToItem(map: Map<String, Any>): ItemStack {
            TODO()
        }
    }
}