package com.gamelovemiku.sheltermc.achievement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class TrueStoriesAchievement extends Achievement implements Listener {

    public TrueStoriesAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eเรื่องจริงไม่ใช่นิยาย");
        this.setPermission("sheltermc.achi.truestories");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        checkAndUnlocked(player);
    }

}
