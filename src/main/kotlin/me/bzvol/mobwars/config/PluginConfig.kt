package me.bzvol.mobwars.config

import me.bzvol.mobwars.MobWarsPlugin
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

abstract class PluginConfig(fileName: String) {

    private val file = File(
        MobWarsPlugin.instance.dataFolder,
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