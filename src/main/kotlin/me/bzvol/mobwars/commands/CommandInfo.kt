package me.bzvol.mobwars.commands

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class CommandInfo(val name: String, val permission: String = "", val requiresPlayer: Boolean)
