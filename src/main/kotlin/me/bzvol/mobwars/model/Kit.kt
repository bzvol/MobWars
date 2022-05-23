package me.bzvol.mobwars.model

import me.bzvol.mobwars.util.ItemUtil
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.inventory.ItemStack

class Kit(val name: String, val armor: Armor, val items: List<ItemStack>) : ConfigurationSerializable {

    override fun serialize(): MutableMap<String, Any> = mutableMapOf(
        "kitName" to this.name,
        "armor" to this.armor.serialize(),
        "items" to this.items.map(ItemUtil.Companion::itemToConfig)
    )

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun deserialize(map: Map<String, Any>): Kit = Kit(
            map["kitName"] as String,
            Armor.deserialize(map["armor"] as Map<String, Any>),
            (map["items"] as List<Map<String, Any>>).map(ItemUtil.Companion::configToItem)
        )
    }

}
