package com.gamelovemiku.sheltermc.achievement;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class FirstbloodAchievement extends Achievement implements Listener {

    public FirstbloodAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eต้องไม่ตายดี");
        this.setPermission("sheltermc.achi.firstblood");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(EntityDeathEvent event) {
        try {
            Player killer = (Player) event.getEntity().getKiller();

            switch (event.getEntity().getType()) {
                case ZOMBIE:
                case SKELETON:
                case CREEPER:
                case SPIDER:
                case CAVE_SPIDER:
                case SILVERFISH:
                    checkAndUnlocked(killer);
                    break;
            }
        } catch (NullPointerException error) {
            //
        }

    }
}
