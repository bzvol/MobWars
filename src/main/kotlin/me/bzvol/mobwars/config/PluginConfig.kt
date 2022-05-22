package me.bzvol.mobwars.config

import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

abstract class PluginConfig(val fileName: String) {

    private val file = File(
        Bukkit.getServer().pluginManager.getPlugin("MobWars")?.dataFolder,
        fileName
    )
    val config: FileConfiguration

    init {
        if (!file.exists())
            file.createNewFile()

        config = YamlConfiguration.loadConfiguration(file)
    }

    fun save() = config.save(file)
}