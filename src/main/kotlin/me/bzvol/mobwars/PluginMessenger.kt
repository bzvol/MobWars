package me.bzvol.mobwars

import org.bukkit.entity.Player

class PluginMessenger {
    companion object {
        lateinit var loggerPrefix: String

        fun broadcast(message: String, prefix: String = loggerPrefix)
                = MobWarsPlugin.instance.server.broadcastMessage("$prefix $message")

        fun sendPlayerMessage(message: String, player: Player, prefix: String = loggerPrefix)
                = player.sendMessage("$prefix $message")

        fun sendGameMessage(message: String, players: List<Player?>, prefix: String = loggerPrefix)
                = players.forEach { player -> player?.sendMessage("$prefix $message") }
    }
}