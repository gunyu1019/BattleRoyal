package kr.yhs.battleroyal.commands

import com.github.monun.kommand.KommandBuilder
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.GameRule
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.block.Chest
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import kotlin.math.round
import kotlin.random.Random

object Game {
    fun play(builder: KommandBuilder, plugin: JavaPlugin) {
        val logger = Bukkit.getLogger()
        builder.apply {
            executes { Bukkit.broadcast(Component.text("명령어 사용법: =game <map/start>")) }
            then("map") {
                val world: World? = Bukkit.getWorld("world")
                if (world != null) {
                    logger.info("BattleRoyal - Set the GameRule for minecraft:overworld.")
                    logger.info("BattleRoyal - SHOW_DEATH_MESSAGES: ${world.getGameRuleValue(GameRule.SHOW_DEATH_MESSAGES)} -> False")
                    logger.info("BattleRoyal - DO_IMMEDIATE_RESPAWN: ${world.getGameRuleValue(GameRule.DO_IMMEDIATE_RESPAWN)} -> True")
                    logger.info("BattleRoyal - DO_INSOMNIA: ${world.getGameRuleValue(GameRule.DO_INSOMNIA)} -> False")
                    world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false)
                    world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true)
                    world.setGameRule(GameRule.DO_INSOMNIA, false)

                    logger.info("BattleRoyal - Setting the magnetic field (worldboarder).")
                    world.worldBorder.setCenter(0.0, 0.0)
                    world.worldBorder.setSize(1500.0, 1)
                    world.worldBorder.damageAmount = 1.0
                    world.worldBorder.damageBuffer = 0.5
                } else {
                    logger.warning("World not found.")
                }
            }
        }
    }
}