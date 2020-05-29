package com.gamelovemiku.sheltermc.achievement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.plugin.Plugin;

public class LevelForFutureAchievement extends Achievement implements Listener {

    public LevelForFutureAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eเลเวลนี้เพื่ออนาคต");
        this.setPermission("sheltermc.achi.levelforfuture");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(PlayerLevelChangeEvent event) {
        Player player = (Player) event.getPlayer();

        switch (event.getNewLevel()) {
            case 30:
                checkAndUnlocked(player);
                break;
        }
    }
}
