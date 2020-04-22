package com.gamelovemiku.sheltermc.achievement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.plugin.Plugin;
import world.bentobox.bentobox.api.events.island.IslandEvent;

public class DaydreamAchievement extends Achievement implements Listener {

    public DaydreamAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eฝันกลางวัน");
        this.setPermission("sheltermc.achi.daydreaming");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        if(player.getWorld().equals(Bukkit.getWorld("world_dreamyland"))) {
            checkAndUnlocked(player);
            sendUnlockedMessage(player);
        }

    }

}
