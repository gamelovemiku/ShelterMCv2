package com.gamelovemiku.sheltermc.achievement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.plugin.Plugin;

public class PureDiamondAchievement extends Achievement implements Listener {

    public PureDiamondAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eเพชรหรอ.. ห๊ะอะไรนะนี้มันเพชร");
        this.setPermission("sheltermc.achi.purediamond");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(EntityPickupItemEvent event) {
        if(event.getEntity() instanceof Player) {
            Player picker = (Player) event.getEntity();
            switch (event.getItem().getItemStack().getType()) {
                case DIAMOND:
                    checkAndUnlocked(picker);
                    break;
            }
        }

    }
}
