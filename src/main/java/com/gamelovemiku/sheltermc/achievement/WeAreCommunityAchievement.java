package com.gamelovemiku.sheltermc.achievement;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class WeAreCommunityAchievement extends Achievement implements Listener {

    public WeAreCommunityAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eข้อความจากคนๆ หนึ่ง");
        this.setPermission("sheltermc.achi.wearecommunity");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(AsyncPlayerChatEvent event) {
        if (event.getMessage() != "") {
            //checkAndUnlocked(event.getPlayer());
        }
    }
}
