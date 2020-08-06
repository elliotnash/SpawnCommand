package org.elliotnash;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Spawn extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("spawn").setExecutor(new SpawnCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
