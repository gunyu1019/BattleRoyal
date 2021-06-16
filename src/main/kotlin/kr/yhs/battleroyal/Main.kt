package kr.yhs.battleroyal

import com.github.monun.kommand.kommand
import kr.yhs.battleroyal.commands.Game
import kr.yhs.battleroyal.listener.DeathEvent
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin(), Listener {
    companion object {
        var instance: Main? = null
    }

    override fun onEnable() {
        instance = this
        if (!dataFolder.exists()) {
            this.saveDefaultConfig()
            Bukkit.getLogger().info("BattleRoyal - Initialized configuration!")
        }
        
        Bukkit.getLogger().info("BattleRoyal - Succeed enabled!")
        kommand {
            register("game") { Game.play(this, this@Main) }
        }

        server.pluginManager.apply {
            registerEvents(DeathEvent(this@Main), this@Main)
        }
        Bukkit.getLogger().info("BattleRoyal - Plugin load done.")
    }

    override fun onDisable() {
        Bukkit.getLogger().info("BattleRoyal - Succeed disabled!")
    }
}
