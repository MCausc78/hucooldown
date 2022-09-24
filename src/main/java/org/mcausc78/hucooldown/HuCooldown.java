package org.mcausc78.hucooldown;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class HuCooldown extends JavaPlugin {
    private static HuCooldown instance;
    private static HashMap<String, Long> storage;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new CmdListener(), this);
    }

    @Override
    public void onDisable() {}

    public static HuCooldown getInstance() {
        return instance;
    }

    public static HashMap<String, Long> getStorage() {
        return storage;
    }
}
