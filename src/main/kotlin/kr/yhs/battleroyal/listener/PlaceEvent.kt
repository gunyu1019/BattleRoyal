package kr.yhs.battleroyal.listener

import kr.yhs.battleroyal.Main
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent


class PlaceEvent(private val plugin: Main) : Listener {
    @EventHandler
    fun onPlace(event: BlockPlaceEvent) {
        val block: Block = event.block
        if (block.type == Material.TNT) {
            block.type = Material.AIR
            val tnt = block.world.spawn(block.location, TNTPrimed::class.java)
            tnt.yield = 0F
        }
    }
}