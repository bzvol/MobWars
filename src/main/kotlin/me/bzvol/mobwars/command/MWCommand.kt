package me.bzvol.mobwars.command

import me.bzvol.mobwars.GameManager
import me.bzvol.mobwars.enum.GameType
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@CommandInfo(name = "mw", requiresPlayer = false)
class MWCommand(override val gameManager: GameManager) : PluginCommand() {
    override fun onExecute(sender: CommandSender, args: Array<out String>) {
        when (args[0]) {
            "start" -> mwStart(args, sender)
            "config" -> mwConfig(args, sender)
            "join", "spec", "leave" -> mwPlayer(sender, args)
            "kit" -> mwKit(sender, args)
        }
    }

    private fun mwKit(sender: CommandSender, args: Array<out String>) {
        if (sender !is Player) {
            requiresPlayer(sender)
            return
        }
        when (args[1]) {
            "create" -> if (args[2].isNotEmpty())
                gameManager.kitManager.createEmptyKit(args[2])
            "armor" -> if (args[2].isNotEmpty())
                gameManager.kitManager.setKitArmor(
                    args[2],
                    sender.inventory.itemInMainHand
                )
            "item" -> if (args[2].isNotEmpty())
                gameManager.kitManager.addKitItem(
                    args[2],
                    sender.inventory.itemInMainHand
                )
        }
    }

    private fun mwConfig(args: Array<out String>, sender: CommandSender) {
        when (args[1]) {
            "pos1", "pos2", "lobby" -> {
                if (sender !is Player) {
                    requiresPlayer(sender)
                    return
                }
                gameManager.setPosition(sender, args[1])
            }
            "save" -> {
                gameManager.plugin.saveConfig()
                gameManager.kitManager.saveAll()
                TODO()
            }
        }
    }

    private fun mwPlayer(sender: CommandSender, args: Array<out String>) {
        if (sender !is Player) {
            requiresPlayer(sender)
            return
        }
        setPlayerStatus(sender, args[0])
    }

    private fun mwStart(args: Array<out String>, sender: CommandSender) {
        when (args[1]) {
            "waves" -> gameManager.launch(GameType.WAVES, sender)
            "infinite" -> gameManager.launch(GameType.INFINITE, sender)
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