@file:Suppress("unused")

package me.bzvol.mobwars

import me.bzvol.mobwars.enum.GameMode
import me.bzvol.mobwars.enum.GameState
import me.bzvol.mobwars.manager.*
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GameManager(val plugin: MobWarsPlugin) {
    val playerManager = PlayerManager(this)
    val kitManager = KitManager(this)
    val waveManager = WaveManager(this)
    val mobSpawnManager = MobSpawnManager(this)
    val mobManager = MobManager(this)

    var gameState: GameState = GameState.NO_GAME
        private set
    var gameMode: GameMode = GameMode.WAVES
        private set

    private lateinit var arenaPos1: Location
    private lateinit var arenaPos2: Location
    lateinit var lobbyLoc: Location
    lateinit var specLoc: Location

    fun launchWaves(sender: CommandSender) {
        PluginMessenger.broadcast("Game is starting! Join with /mw join")

        if (sender is Player) {
            playerManager.addPlayer(sender)
        }
    }

    fun launchInfinite(sender: CommandSender) {

    }

    fun start() {}

    fun setPosition(sender: Player, positionArg: String) {
        when (positionArg) {
            "pos1" -> this.arenaPos1 = sender.location
            "pos2" -> this.arenaPos2 = sender.location
            "lobby" -> this.lobbyLoc = sender.location
        }
    }
}