package me.bzvol.mobwars.model

import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.inventory.ItemStack

class Armor : ConfigurationSerializable {
    lateinit var helmet: ItemStack
    lateinit var chestplate: ItemStack
    lateinit var leggings: ItemStack
    lateinit var boots: ItemStack

    constructor(helmet: ItemStack, chestplate: ItemStack, leggings: ItemStack, boots: ItemStack) {
        this.helmet = helmet
        this.chestplate = chestplate
        this.leggings = leggings
        this.boots = boots
    }

    constructor()

    override fun serialize(): MutableMap<String, Any> = mutableMapOf(
        "helmet" to helmet.serialize(),
        "chestplate" to chestplate.serialize(),
        "leggings" to leggings.serialize(),
        "boots" to boots.serialize()
    )

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun deserialize(map: Map<String, Any>): Armor = Armor(
            ItemStack.deserialize(map["helmet"] as Map<String, Any>),
            ItemStack.deserialize(map["chestplate"] as Map<String, Any>),
            ItemStack.deserialize(map["leggings"] as Map<String, Any>),
            ItemStack.deserialize(map["boots"] as Map<String, Any>)
        )
    }
}
