package com.gamelovemiku.adapter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.onarandombox.MultiverseCore.MultiverseCore;

public class Multiverse {

	public static MultiverseCore api() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
 
        if (plugin instanceof MultiverseCore) {
            return (MultiverseCore) plugin;
        }
 
        throw new RuntimeException("MultiVerse not found!");
    }
	
}
