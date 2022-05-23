package me.bzvol.mobwars.model

import me.bzvol.mobwars.util.ItemUtil
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.inventory.ItemStack

class Armor(val helmet: ItemStack, val chestplate: ItemStack, val leggings: ItemStack, val boots: ItemStack) :
    ConfigurationSerializable {

    override fun serialize(): MutableMap<String, Any> = mutableMapOf(
        "helmet" to ItemUtil.itemToConfig(helmet),
        "chestplate" to ItemUtil.itemToConfig(chestplate),
        "leggings" to ItemUtil.itemToConfig(leggings),
        "boots" to ItemUtil.itemToConfig(boots)
    )

    companion object {
        fun deserialize(map: Map<String, Any>): Armor = TODO()
    }
}
