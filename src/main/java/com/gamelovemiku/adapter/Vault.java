package com.gamelovemiku.adapter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;

public class Vault {

	private static Economy economy = null;
	
	public static Economy getEconomy() {
		return economy;
	}

	public static boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }else {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[ShelterMC]" + ChatColor.RED + ChatColor.RED + " Vault not found!" + ChatColor.YELLOW + "Economy will not support!");
        } 
        
        return (economy != null);
    }
	
	
}
