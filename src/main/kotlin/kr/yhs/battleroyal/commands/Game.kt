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

                    logger.info("BattleRoyal - Installing boxes for farming to random values.")
                    val range: Int = plugin.config.getInt("range")
                    for (y: Int in 7..13) {
                        for (x: Int in (-1 * range)..range) {
                            for (z: Int in (-1 * range)..range) {
                                val random: Int = Random.nextInt(200)
                                if (random == 10) {
                                    val randomItem: Double = Random.nextDouble(100.0)
                                    val block: Block = world.getBlockAt(x, y, z)
                                    block.type = Material.CHEST

                                    val chest: Chest = block.state as Chest
                                    when(randomItem) {
                                        in 0.0..35.0 -> {
                                            when (Random.nextInt(6)) {
                                                0 -> chest.inventory.addItem(ItemStack(Material.IRON_AXE, 1))
                                                1 -> chest.inventory.addItem(ItemStack(Material.IRON_SWORD, 1))
                                                2 -> chest.inventory.addItem(ItemStack(Material.IRON_BOOTS, 1))
                                                3 -> chest.inventory.addItem(ItemStack(Material.IRON_CHESTPLATE, 1))
                                                4 -> chest.inventory.addItem(ItemStack(Material.IRON_LEGGINGS, 1))
                                                5 -> chest.inventory.addItem(ItemStack(Material.IRON_HELMET, 1))
                                            }
                                        } in 35.0..53.5 -> {
                                            when (Random.nextInt(6)) {
                                                0 -> chest.inventory.addItem(ItemStack(Material.DIAMOND_AXE, 1))
                                                1 -> chest.inventory.addItem(ItemStack(Material.DIAMOND_SWORD, 1))
                                                2 -> chest.inventory.addItem(ItemStack(Material.DIAMOND_BOOTS, 1))
                                                3 -> chest.inventory.addItem(ItemStack(Material.DIAMOND_CHESTPLATE, 1))
                                                4 -> chest.inventory.addItem(ItemStack(Material.DIAMOND_LEGGINGS, 1))
                                                5 -> chest.inventory.addItem(ItemStack(Material.DIAMOND_HELMET, 1))
                                            }
                                        } in 53.5..57.5 -> {
                                            when (Random.nextInt(6)) {
                                                0 -> chest.inventory.addItem(ItemStack(Material.NETHERITE_AXE, 1))
                                                1 -> chest.inventory.addItem(ItemStack(Material.NETHERITE_SWORD, 1))
                                                2 -> chest.inventory.addItem(ItemStack(Material.NETHERITE_BOOTS, 1))
                                                3 -> chest.inventory.addItem(
                                                    ItemStack(
                                                        Material.NETHERITE_CHESTPLATE,
                                                        1
                                                    )
                                                )
                                                4 -> chest.inventory.addItem(ItemStack(Material.NETHERITE_LEGGINGS, 1))
                                                5 -> chest.inventory.addItem(ItemStack(Material.NETHERITE_HELMET, 1))
                                            }
                                        } in 57.5..72.5 -> {
                                            val amount: Int = Random.nextInt(5)
                                            chest.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, amount + 1))
                                        } in 72.5..76.5 -> {
                                            val amount: Int = Random.nextInt(3)
                                            chest.inventory.addItem(
                                                ItemStack(
                                                    Material.ENCHANTED_GOLDEN_APPLE,
                                                    amount + 1
                                                )
                                            )
                                        } in 76.5..81.0 -> {
                                            val amount: Int = Random.nextInt(4)
                                            chest.inventory.addItem(ItemStack(Material.NETHERITE_INGOT, amount + 1))
                                        } in 81.0..84.8 -> {
                                            chest.inventory.addItem(ItemStack(Material.ELYTRA, 1))
                                        } in 84.8..89.9 -> {
                                            val amount: Int = Random.nextInt(3)
                                            chest.inventory.addItem(ItemStack(Material.ENDER_EYE, amount + 1))
                                        } in 89.9..92.0 -> {
                                            val amount: Int = Random.nextInt(10, 30)
                                            chest.inventory.addItem(ItemStack(Material.FIREWORK_ROCKET, amount + 1))
                                            chest.inventory.addItem(ItemStack(Material.ELYTRA, 1))
                                        } in 92.0..96.5 -> {
                                            val amount: Int = Random.nextInt(10)
                                            chest.inventory.addItem(ItemStack(Material.EXPERIENCE_BOTTLE, amount + 1))
                                        } in 96.5..100.0 -> {
                                            val amount: Int = Random.nextInt(5)
                                            chest.inventory.addItem(ItemStack(Material.TNT, amount + 1))
                                        }
                                    }
                                }
                            }
                        }
                        val percent: Double = (y.toDouble() - 6.0) * (100.0 / 7.0)
                        logger.info("BattleRoyal - Map is being modified : ${round(percent)}%")
                    }
                } else {
                    logger.warning("World not found.")
                }
            }
        }
    }
}