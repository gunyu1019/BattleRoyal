package kr.yhs.battleroyal.listener

import kr.yhs.battleroyal.Main
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent

class DeathEvent(private val plugin: Main) : Listener {
    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        if (event.entityType != EntityType.PLAYER) return

        val killer: Player? = event.entity.killer
        val player: Player = event.entity.player!!

        if (killer == null) {
            val ede: EntityDamageEvent = event.entity.lastDamageCause!!
            val dc: EntityDamageEvent.DamageCause = ede.cause
            val mat: Material = player.eyeLocation.block.type

            if (dc == EntityDamageEvent.DamageCause.SUFFOCATION && mat == Material.AIR) {
                var message: Component = Component.text("${player.name}이(가) 자기장 밖에서 질식하였습니다.")
                Bukkit.broadcast(message)
            }
        } else {
            var message: Component = Component.text("${player.name}이 ${killer.name}에 의해 살해당했습니다.")
            Bukkit.broadcast(message)
        }
        player.sendMessage("${ChatColor.RED}${ChatColor.BOLD}아쉽게도, 탈락하셨습니다. (자동으로 관전모드로 전환됩니다.)")
        player.gameMode = GameMode.SPECTATOR
    }
}