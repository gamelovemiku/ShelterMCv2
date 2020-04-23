package com.gamelovemiku.sheltermc.achievement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class WhereWeBelongAchievement extends Achievement implements Listener {

    public WhereWeBelongAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eกลับไปไม่ได้อีกแล้ว");
        this.setPermission("sheltermc.achi.wherewebelong");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(player.getLocation().getY() >= 160 && player.getWorld().equals(Bukkit.getWorld("world_shelter"))) {
            checkAndUnlocked(player);
        }
    }

}
