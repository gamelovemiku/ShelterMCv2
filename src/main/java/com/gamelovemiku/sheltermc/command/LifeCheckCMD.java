package com.gamelovemiku.sheltermc.command;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class LifeCheckCMD implements CommandExecutor, Listener {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

		if (sender instanceof Player) {
			if(player.hasPermission("sheltermc.upgraded.lv1")) {
				String text = PlaceholderAPI.setPlaceholders(player, "&c❤ &8[&cLife&8] &7พลังชีวิตของคุณเหลืออยู่ &e%playerpoints_points% Life");
				sender.sendMessage(text);
				return true;
			}
		}
		return false;
	}
	
}
