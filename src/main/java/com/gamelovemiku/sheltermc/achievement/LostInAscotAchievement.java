package com.gamelovemiku.sheltermc.achievement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.plugin.Plugin;
import world.bentobox.bentobox.api.events.island.IslandEvent;

public class LostInAscotAchievement extends Achievement implements Listener {

    public LostInAscotAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eพื้นหญ้าสุดท้าย");
        this.setPermission("sheltermc.achi.lostinascot");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        if (player.getWorld().equals(Bukkit.getWorld("world_ascotcity"))) {
            checkAndUnlocked(player);
            sendUnlockedMessage(player);
        }
    }

}
