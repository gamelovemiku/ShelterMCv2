package com.gamelovemiku.sheltermc.achievement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class WeAreCommunityAchievement extends Achievement implements Listener {

    public WeAreCommunityAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eโดดเดี่ยวแต่ไม่เดียวดาย");
        this.setPermission("sheltermc.achi.wearecommunity");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        checkAndUnlocked(player);
    }

}
