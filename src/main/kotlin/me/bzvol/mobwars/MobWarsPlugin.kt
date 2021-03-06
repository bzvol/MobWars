package me.bzvol.mobwars

import me.bzvol.mobwars.command.PluginCommand
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.reflections.Reflections

@Suppress("unused")
class MobWarsPlugin : JavaPlugin() {
    private lateinit var gameManager: GameManager
    lateinit var messenger: PluginMessenger

    override fun onEnable() {
        instance = this

        if (!this.dataFolder.exists())
            this.dataFolder.mkdir()

        this.gameManager = GameManager(this)
        PluginMessenger.loggerPrefix = "[MobWars]"

        registerCommands()
        registerListeners()
    }

    private fun registerCommands() {
        val packageName = this::class.java.packageName
        for (clazz: Class<out PluginCommand> in Reflections("$packageName.command")
            .getSubTypesOf(PluginCommand::class.java)) {
            val pluginCommand = clazz.getDeclaredConstructor(GameManager::class.java).newInstance(this.gameManager)
            getCommand(pluginCommand.commandInfo.name)?.setExecutor(pluginCommand)
            logger.info("Registered command: /${pluginCommand.commandInfo.name}")
        }
    }

    private fun registerListeners() {
        val packageName = this::class.java.packageName
        for (clazz: Class<out Listener> in Reflections("$packageName.listener")
            .getSubTypesOf(Listener::class.java)) {
            val listener = clazz.getDeclaredConstructor(GameManager::class.java).newInstance(this.gameManager)
            server.pluginManager.registerEvents(listener, this)
        }
    }

    companion object {
        lateinit var instance: MobWarsPlugin
    }
}