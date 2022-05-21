package me.bzvol.mobwars

import me.bzvol.mobwars.command.PluginCommand
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.reflections.Reflections

@Suppress("unused")
class MobWarsPlugin : JavaPlugin() {
    private lateinit var gameManager: GameManager
    lateinit var messenger: PluginLogger

    override fun onEnable() {
        this.gameManager = GameManager(this)
        this.messenger = PluginLogger(this, "[MobWars]")

        registerCommands()
        registerListeners()
    }

    private fun registerCommands() {
        val packageName = this::class.java.packageName
        for (clazz: Class<out PluginCommand> in Reflections("$packageName.commands")
            .getSubTypesOf(PluginCommand::class.java)) {
            val pluginCommand = clazz.getDeclaredConstructor(GameManager::class.java).newInstance(this.gameManager)
            getCommand(pluginCommand.commandInfo.name)?.setExecutor(pluginCommand)
            logger.info("Registered command: /${pluginCommand.commandInfo.name}")
        }
    }

    private fun registerListeners() {
        val packageName = this::class.java.packageName
        for (clazz: Class<out Listener> in Reflections("$packageName.listeners")
            .getSubTypesOf(Listener::class.java)) {
            val listener = clazz.getDeclaredConstructor(GameManager::class.java).newInstance(this.gameManager)
            server.pluginManager.registerEvents(listener, this)
        }
    }
}