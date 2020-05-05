package com.gamelovemiku.sheltermc.tradingstock;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import com.gamelovemiku.sheltermc.tasks.PermissionTimeOutCountdownTask;
import me.vagdedes.mysql.database.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class StockCommand implements CommandExecutor{
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof ConsoleCommandSender || sender instanceof Player) {
            if (args[0].equalsIgnoreCase("add")) {
                if (args[1] != null && args[2] != null) {
                    new StockDispatcher().addItemStock(args[1], Integer.parseInt(args[2]));
                    sender.sendMessage("Added!");
                    return true;
                }
            }

            if (args[0].equalsIgnoreCase("take")) {
                if (args[1] != null && args[2] != null) {
                    new StockDispatcher().removeItemStock(args[1], Integer.parseInt(args[2]));
                    sender.sendMessage("Taken!");
                    return true;
                }
            }

            if (args[0].equalsIgnoreCase("set")) {
                if (args[1] != null && args[2] != null) {
                    new StockDispatcher().setItemStock(args[1], Integer.parseInt(args[2]));
                    sender.sendMessage("Set!");
                    return true;
                }
            }
        }
        return true;
    }
}