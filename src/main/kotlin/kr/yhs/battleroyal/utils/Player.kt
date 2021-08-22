package kr.yhs.battleroyal.utils

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

class Player(
    private val process: Process,
    val playerId: UUID,
    name: String
) {
    var name: String = name
        get() {
            player.let { field = it.name }
            return field
        }

    var player: Player = Bukkit.getPlayer(playerId)!!
    var kills = 0
    var rank = -1
    val isOnline
        get() = player.isOnline

}