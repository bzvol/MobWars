package me.bzvol.mobwars.config

import me.bzvol.mobwars.MobWarsPlugin
import me.bzvol.mobwars.model.Kit

class KitConfig : PluginConfig("kits.yml") {
    fun loadKit(name: String): Kit {
        MobWarsPlugin.instance.logger.info("Loading kit $name")
        return config.getObject(name, Kit::class.java)!!
    }

    fun saveKit(kit: Kit) {
        config.set(kit.name, kit.serialize())
        this.save()
    }

    fun clear() = config.getKeys(false).forEach { config.set(it, null) }

    fun getKitNames(): Set<String> = config.getKeys(false)
}