package me.bzvol.mobwars.commands

import me.bzvol.mobwars.GameManager
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import java.util.*

abstract class PluginCommand : TabExecutor {
    val commandInfo: CommandInfo = this::class.java.getDeclaredAnnotation(CommandInfo::class.java)

    abstract val gameManager: GameManager

    init {
        Objects.requireNonNull(this.commandInfo, "Commands must have CommandInfo annotations")
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (commandInfo.permission.isNotEmpty()) {
            if (!sender.hasPermission(commandInfo.permission)) {
                noPermission(sender)
                return true
            }
        }

        if (commandInfo.requiresPlayer) {
            if (sender !is Player) {
                requiresPlayer(sender)
                return true
            }

            onExecute(sender, args)
        }

        onExecute(sender, args)
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? {
        if (commandInfo.permission.isNotEmpty() && !sender.hasPermission(commandInfo.permission))
            return null
        if (commandInfo.requiresPlayer) {
            if (sender !is Player)
                return null

            return onTab(sender, args)
        }

        return onTab(sender, args)
    }

    open fun onExecute(player: Player, args: Array<out String>) {}
    open fun onExecute(sender: CommandSender, args: Array<out String>) {}

    open fun onTab(player: Player, args: Array<out String>): MutableList<String>? = null
    open fun onTab(sender: CommandSender, args: Array<out String>): MutableList<String>? = null

    fun noPermission(sender: CommandSender) =
        sender.sendMessage("${ChatColor.RED}You don't have permission to execute this command.")

    fun requiresPlayer(sender: CommandSender) =
        sender.sendMessage("${ChatColor.RED}You must be a player to execute this command.")
}