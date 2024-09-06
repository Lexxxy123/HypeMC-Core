package com.example.myspigotplugin.commands;

import com.example.myspigotplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobbyCommand implements CommandExecutor {

    private final Main plugin;

    public SetLobbyCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can set the lobby.");
            return true;
        }

        Player player = (Player) sender;
        plugin.setLobbyLocation(player);
        player.sendMessage("§f[§aHYPE§bMC §dCORE§f] §aYour Lobby has been set at the Location!");
        return true;
    }
}