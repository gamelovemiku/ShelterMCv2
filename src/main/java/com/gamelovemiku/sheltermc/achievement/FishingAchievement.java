package com.gamelovemiku.sheltermc.achievement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.plugin.Plugin;

public class FishingAchievement extends Achievement implements Listener {

    public FishingAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eปลานอกทำเนียบ");
        this.setPermission("sheltermc.achi.fishing");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(PlayerFishEvent event) {
        Player player = (Player) event.getPlayer();

        switch (event.getState()) {
            case CAUGHT_FISH:
                checkAndUnlocked(player);
                break;
        }
    }
}
