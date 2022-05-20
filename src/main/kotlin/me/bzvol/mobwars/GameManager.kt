@file:Suppress("unused")

package me.bzvol.mobwars

import me.bzvol.mobwars.enums.GameMode
import me.bzvol.mobwars.enums.GameState
import me.bzvol.mobwars.managers.*
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

    fun startWaves(sender: CommandSender) {
        plugin.messenger.broadcast("Game is starting! Join with /mw join")

        if (sender is Player) {

        }
    }

    fun startInfinite(sender: CommandSender) {}

    fun setPosition(sender: Player, positionArg: String) {
        when (positionArg) {
            "pos1" -> this.arenaPos1 = sender.location
            "pos2" -> this.arenaPos2 = sender.location
            "lobby" -> this.lobbyLoc = sender.location
        }
    }
}