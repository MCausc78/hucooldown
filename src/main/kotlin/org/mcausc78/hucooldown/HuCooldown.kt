package org.mcausc78.hucooldown

import org.bukkit.plugin.java.JavaPlugin

class HuCooldown : JavaPlugin() {
    override fun onEnable() {
        instance = this
        saveDefaultConfig()
        server.pluginManager.registerEvents(HuListener(), this)
    }

    override fun onDisable() {}

    companion object {
        var instance: HuCooldown? = null
            private set
        val storage: HashMap<String, Long>? = null
    }
}