package me.bzvol.mobwars.command

import me.bzvol.mobwars.GameManager
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@CommandInfo(name = "mw", requiresPlayer = false)
class MWCommand(override val gameManager: GameManager) : PluginCommand() {
    override fun onExecute(sender: CommandSender, args: Array<out String>) {
        when (args[0]) {
            "start" -> when(args[1]) {
                "waves" -> gameManager.launchWaves(sender)
                "infinite" -> gameManager.launchInfinite(sender)
            }
            "config" -> when(args[1]) {
                "pos1", "pos2", "lobby" -> {
                    if (sender !is Player) {
                        requiresPlayer(sender)
                        return
                    }
                    gameManager.setPosition(sender, args[1])
                }
            }
            "join", "spec", "leave" -> {
                if (sender !is Player) {
                    requiresPlayer(sender)
                    return
                }
                setPlayerStatus(sender, args[0])
            }
        }
    }

    private fun setPlayerStatus(p: Player, arg: String) {
        when (arg) {
            "join" -> gameManager.playerManager.addPlayer(p)
            "spec" -> gameManager.playerManager.addSpectator(p)
            "leave" -> gameManager.playerManager.removePlayer(p)
        }
    }

    override fun onTab(sender: CommandSender, args: Array<out String>): MutableList<String>? {
        return null
    }
}