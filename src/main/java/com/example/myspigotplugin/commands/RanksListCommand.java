package com.example.myspigotplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RanksListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("rankslist")) {
            sender.sendMessage("§a---------------------");
            sender.sendMessage("§f[§aHype§bMC §dRanks§f]");
            sender.sendMessage("§c[OWNER] §aRank!");
            sender.sendMessage("§c[CO-OWNER] §aRank!");
            sender.sendMessage("§c[ADMIN] §aRank!");
            sender.sendMessage("§b[DEV] §aRank!");
            sender.sendMessage("§2[GM] §aRank!");
            sender.sendMessage("§2[MODERATOR] §aRank!");
            sender.sendMessage("§9[HELPER] §aRank!");
            sender.sendMessage("§6You Can use /ranklist 2 For Second Page!");
            sender.sendMessage("§a---------------------");
        } else if (label.equalsIgnoreCase("ranklist") && args.length == 1 && args[0].equals("2")) {
            sender.sendMessage("§a---------------------");
            sender.sendMessage("§f[§aHype§bMC §dRanks§f] §aPage 2");
            sender.sendMessage("§d[SPONSOR] §aRank!");
            sender.sendMessage("§6[MVP§c++§6] §aRank!");
            sender.sendMessage("§b[MVP§c+§b] §aRank!");
            sender.sendMessage("§b[MVP] §aRank!");
            sender.sendMessage("§a[VIP§6+§a] §aRank!");
            sender.sendMessage("§a[VIP] §aRank!");
            sender.sendMessage("§7Default §aRank!");
            sender.sendMessage("§a---------------------");
        }
        return true;
    }
}