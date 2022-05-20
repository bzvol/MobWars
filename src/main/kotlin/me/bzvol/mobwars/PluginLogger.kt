package me.bzvol.mobwars

import org.bukkit.entity.Player

class PluginLogger(private val plugin: MobWarsPlugin, private val loggerPrefix: String) {
    fun broadcast(message: String, prefix: String = loggerPrefix)
        = plugin.server.broadcastMessage("$prefix $message")

    fun sendPlayerMessage(message: String, player: Player, prefix: String = loggerPrefix)
        = player.sendMessage("$prefix $message")
}