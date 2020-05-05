package com.gamelovemiku.sheltermc.command;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import me.clip.placeholderapi.PlaceholderAPI;
import me.vagdedes.mysql.database.MySQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class CommandAddon implements CommandExecutor, Listener {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

		sender.sendMessage(PlaceholderAPI.setPlaceholders(player, "STATUS PLACEHOLDER EGG: %shelterstock_stock_egg%"));
		sender.sendMessage(PlaceholderAPI.setPlaceholders(player, "STATUS PLACEHOLDER WHEAT: %shelterstock_stock_wheat%"));

		return true;
	}
	
}
