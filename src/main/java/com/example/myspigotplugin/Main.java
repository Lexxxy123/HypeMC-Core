package com.example.myspigotplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

// Corrected command imports
import com.example.myspigotplugin.commands.LobbyCommand;
import com.example.myspigotplugin.commands.RanksListCommand;
import com.example.myspigotplugin.commands.SetLobbyCommand;
import com.example.myspigotplugin.commands.SetRankCommand;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        getCommand("setlobby").setExecutor(new SetLobbyCommand(this));
        getCommand("lobby").setExecutor(new LobbyCommand(this));
        getCommand("rankslist").setExecutor(new RanksListCommand());
        getCommand("ranklist").setExecutor(new RanksListCommand());
        getCommand("setrank").setExecutor(new SetRankCommand(this));
        getLogger().info("MySpigotPlugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MySpigotPlugin disabled!");
    }

    public void setLobbyLocation(Player player) {
        Location location = player.getLocation();
        FileConfiguration config = getConfig();
        config.set("lobby.world", location.getWorld().getName());
        config.set("lobby.x", location.getX());
        config.set("lobby.y", location.getY());
        config.set("lobby.z", location.getZ());
        config.set("lobby.yaw", location.getYaw());
        config.set("lobby.pitch", location.getPitch());
        saveConfig();
    }

    public Location getLobbyLocation() {
        FileConfiguration config = getConfig();
        String worldName = config.getString("lobby.world");
        if (worldName == null) return null;
        return new Location(
                Bukkit.getWorld(worldName),
                config.getDouble("lobby.x"),
                config.getDouble("lobby.y"),
                config.getDouble("lobby.z"),
                (float) config.getDouble("lobby.yaw"),
                (float) config.getDouble("lobby.pitch")
        );
    }
}