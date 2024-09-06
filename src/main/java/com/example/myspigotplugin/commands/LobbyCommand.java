package com.example.myspigotplugin.commands;

import com.example.myspigotplugin.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand implements CommandExecutor {

    private final Main plugin;

    public LobbyCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can teleport to the lobby.");
            return true;
        }

        Player player = (Player) sender;
        Location lobbyLocation = plugin.getLobbyLocation();
        if (lobbyLocation != null) {
            player.teleport(lobbyLocation);
            player.sendMessage("§f[§aHYPE§bMC §dCORE§f] §aYou Have been teleported to Lobby!");
        } else {
            player.sendMessage("Lobby location is not set.");
        }
        return true;
    }
}