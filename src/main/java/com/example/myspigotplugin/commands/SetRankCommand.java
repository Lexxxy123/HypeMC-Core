package com.example.myspigotplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class SetRankCommand implements CommandExecutor, Listener {

    private final Map<String, String> ranks = new HashMap<>(); // Rank to prefix mapping
    private final Map<Player, String> playerRanks = new HashMap<>(); // Player to rank prefix mapping

    public SetRankCommand(JavaPlugin plugin) {
        // Register the event listener
        Bukkit.getPluginManager().registerEvents(this, plugin);

        // Define ranks and their prefixes
        ranks.put("owner", "&c[OWNER] ");
        ranks.put("co-owner", "&c[CO-OWNER] ");
        ranks.put("admin", "&c[ADMIN] ");
        ranks.put("dev", "&b[DEV] ");
        ranks.put("gm", "&2[GM] ");
        ranks.put("mod", "&2[MODERATOR] ");
        ranks.put("helper", "&9[HELPER] ");
        ranks.put("sponsor", "&d[SPONSOR]");
        ranks.put("mvp++", "&6[MVP&c++&6] ");
        ranks.put("mvp+", "&b[MVP&c+&b] ");
        ranks.put("mvp", "&b[MVP] ");
        ranks.put("vip+", "&a[VIP&6+&a] ");
        ranks.put("vip", "&a[VIP] ");
        ranks.put("default", "&7"); // Default prefix for players without a specific rank
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /setrank <playername> <rank>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        String rank = args[1].toLowerCase();

        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        if (!ranks.containsKey(rank)) {
            sender.sendMessage(ChatColor.RED + "Invalid rank.");
            return true;
        }

        // Set the player's rank
        String prefix = ChatColor.translateAlternateColorCodes('&', ranks.get(rank));
        playerRanks.put(target, prefix); // Store the rank prefix for the player

        // Set the player's display name with the new prefix
        target.setDisplayName(prefix + " " + ChatColor.stripColor(target.getName()));

        // Inform the command sender
        sender.sendMessage(ChatColor.GREEN + "Set rank " + rank + " for " + target.getName());

        return true;
    }

    // Event listener to modify chat messages according to the player's rank
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String prefix = playerRanks.getOrDefault(player, "&7"); // Default to no specific rank prefix if none is set

        // Set the chat format to include the rank prefix, player's name, and message
        event.setFormat(ChatColor.translateAlternateColorCodes('&', prefix) + ChatColor.stripColor(player.getName()) + ChatColor.WHITE + " : " + event.getMessage());
    }
}