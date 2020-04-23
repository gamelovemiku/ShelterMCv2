package com.gamelovemiku.sheltermc.command;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAddon implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        new ShelterMCHelper().getServerTime();

        String value = PlaceholderAPI.setPlaceholders(player, "%vault_eco_balance%");
        double val = 0;

        try {
            val = Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        player.sendMessage("DEBUG: You have " + value + " QL!!!!!!!!!!!!!!!!!!!!!");

		return true;
	}
	
}
