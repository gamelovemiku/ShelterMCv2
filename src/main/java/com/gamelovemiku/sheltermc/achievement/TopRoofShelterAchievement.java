package com.gamelovemiku.sheltermc.achievement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import world.bentobox.bentobox.api.events.island.IslandEvent;

public class TopRoofShelterAchievement extends Achievement implements Listener {

    public TopRoofShelterAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eพื้นที่ต้องห้าม");
        this.setPermission("sheltermc.achi.toproof");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(player.getLocation().getY() >= 180 && player.getWorld().equals(Bukkit.getWorld("world_shelter"))) {
            checkAndUnlocked(player);
        }
    }

}
