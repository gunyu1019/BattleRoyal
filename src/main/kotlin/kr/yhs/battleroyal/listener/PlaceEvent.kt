package kr.yhs.battleroyal.listener

import kr.yhs.battleroyal.Main
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent

class PlaceEvent(private val plugin: Main) : Listener {
    @EventHandler
    fun onPlace(event: BlockPlaceEvent) {
        if (event.block == Material.TNT) {

        }
    }
}