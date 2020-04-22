package com.gamelovemiku.sheltermc.achievement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.plugin.Plugin;
import world.bentobox.bentobox.api.events.island.IslandEvent;

public class LostInLimboAchievement extends Achievement implements Listener {

    public LostInLimboAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eเวียนว่ายตายเกิด");
        this.setPermission("sheltermc.achi.lostinlimbo");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        if (player.getWorld().equals(Bukkit.getWorld("world_limbo"))) {
            checkAndUnlocked(player);
            sendUnlockedMessage(player);
        }
    }

}
