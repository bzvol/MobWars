package me.bzvol.mobwars.model

import me.bzvol.mobwars.GameManager
import org.bukkit.entity.Mob

class Wave(val waveId: UByte, var mobCount: Int, val gameManager: GameManager) {
    val mobs: List<Mob> = emptyList()
}