package com.gamelovemiku.adapter;

import org.bukkit.plugin.java.JavaPlugin;

import skinsrestorer.bukkit.SkinsRestorer;
import skinsrestorer.bukkit.SkinsRestorerBukkitAPI;

public class SkinRestorer {

    private static SkinsRestorer skinsRestorer;
    private static SkinsRestorerBukkitAPI skinsRestorerBukkitAPI;
	
	public static SkinsRestorerBukkitAPI api() {
		skinsRestorer = JavaPlugin.getPlugin(SkinsRestorer.class);
		skinsRestorerBukkitAPI = skinsRestorer.getSkinsRestorerBukkitAPI();
		
		return skinsRestorerBukkitAPI;
	}

}
