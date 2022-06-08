package me.bzvol.mobwars.model

import me.bzvol.mobwars.util.ItemUtil
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.inventory.ItemStack

class Kit : ConfigurationSerializable {
    var name: String
    var armor: Armor
    var items: MutableList<ItemStack>

    constructor(name: String, armor: Armor, items: MutableList<ItemStack>) {
        this.name = name
        this.armor = armor
        this.items = items
    }

    constructor(name: String) {
        this.name = name
        this.armor = Armor()
        this.items = mutableListOf()
    }

    override fun serialize(): MutableMap<String, Any> = mutableMapOf(
        "kitName" to this.name,
        "armor" to this.armor.serialize(),
        "items" to this.items.map(ItemStack::serialize)
    )

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun deserialize(map: Map<String, Any>): Kit = Kit(
            map["kitName"] as String,
            Armor.deserialize(map["armor"] as Map<String, Any>),
            (map["items"] as List<Map<String, Any>>).map(ItemStack::deserialize).toMutableList()
        )
    }
}
