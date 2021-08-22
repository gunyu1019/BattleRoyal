package kr.yhs.battleroyal.utils

import com.google.common.collect.ImmutableMap
import kr.yhs.battleroyal.Main
import org.bukkit.Bukkit
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.util.*

class Process(
    private val plugin: Main,
    private val processFile: File,
    reload: Boolean = false
) {
    private var players: Map<UUID, Player> = mapOf()

    init {
        players = ImmutableMap.copyOf(
            if (!reload) {
                Bukkit.getOnlinePlayers()
                    .filter {
                        it.gameMode.value == 0
                    }
                    .associate { p ->
                        p.uniqueId to Player(this, p.uniqueId, p.name).apply {
                            player = p
                        }
                    }
            } else {
                val yaml = YamlConfiguration.loadConfiguration(processFile)
                val players = HashMap<UUID, Player>()

                for ((name, value) in yaml.getValues(false).filter { it.value is ConfigurationSection }) {
                    val section = value as ConfigurationSection
                    val uuid = UUID.fromString(name)
                    val player = Player(this, uuid, section.getString("name")!!).apply {
                        player = Bukkit.getPlayer(uuid)!!
                        rank = section.getInt("rank")
                        kills = section.getInt("kills")
                    }
                    players[uuid] = player
                }
                players
            }
        )
    }

    fun player(uuid: UUID) = players[uuid]

    fun save() {
        val yaml = YamlConfiguration()
        for ((uuid, player) in players) {
            yaml.createSection(uuid.toString()).let { section ->
                section["name"] = player.name
                section["rank"] = player.rank
                section["kills"] = player.kills
            }
        }

        yaml.save(processFile.also { it.parentFile.mkdirs() })
    }
}