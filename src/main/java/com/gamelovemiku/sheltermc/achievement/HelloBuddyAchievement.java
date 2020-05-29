package com.gamelovemiku.sheltermc.achievement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import world.bentobox.bentobox.api.events.island.IslandEvent;
import world.bentobox.bentobox.api.events.team.TeamEvent;

public class HelloBuddyAchievement extends Achievement implements Listener {

    public HelloBuddyAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eเพื่อนซี้ปึ๊ก");
        this.setPermission("sheltermc.achi.hellobuddy");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(TeamEvent.TeamJoinedEvent event) {
        Player player = Bukkit.getPlayer(event.getPlayerUUID());

        checkAndUnlocked(player);
        checkAndUnlocked(Bukkit.getPlayer(event.getOwner()));
    }

}
