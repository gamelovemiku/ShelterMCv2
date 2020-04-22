package com.gamelovemiku.sheltermc.achievement;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import world.bentobox.bentobox.api.events.island.IslandEvent;

public class NightmareAchievement extends Achievement implements Listener {

    public NightmareAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eฝันร้าย");
        this.setPermission("sheltermc.achi.nightmare");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(PlayerInteractEvent event, PlayerChangedWorldEvent event2) {
        Player player = event.getPlayer();

        switch(event.getClickedBlock().getType()) {
            case BLUE_BED:
            case BLACK_BED:
            case BROWN_BED:
            case CYAN_BED:
            case GRAY_BED:
            case GREEN_BED:
            case LIGHT_BLUE_BED:
            case LIGHT_GRAY_BED:
            case LIME_BED:
            case MAGENTA_BED:
            case ORANGE_BED:
            case PINK_BED:
            case PURPLE_BED:
            case RED_BED:
            case WHITE_BED:
            case YELLOW_BED:

                break;
        }

        checkAndUnlocked(player);
        sendUnlockedMessage(player);
    }

}
