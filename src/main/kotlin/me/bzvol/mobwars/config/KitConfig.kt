package me.bzvol.mobwars.config

import me.bzvol.mobwars.model.Kit

class KitConfig : PluginConfig("kits.yml") {
    fun loadKit(name: String): Kit {
        return config.getObject(name, Kit::class.java)!!
    }

    fun saveKit(kit: Kit) {
        config.set(kit.name, kit.serialize())
        this.save()
    }

    fun clear() = config.set("kits", listOf<Map<String, Any>>())

    fun getKitNames(): Set<String> = config.getKeys(false)
}