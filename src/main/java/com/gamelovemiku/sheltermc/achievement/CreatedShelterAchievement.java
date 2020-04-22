package com.gamelovemiku.sheltermc.achievement;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import world.bentobox.bentobox.api.events.island.IslandEvent;

public class CreatedShelterAchievement extends Achievement implements Listener {

    public CreatedShelterAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eก้าวแรกของความหวัง");
        this.setPermission("sheltermc.achi.createdshelter");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(IslandEvent.IslandEnterEvent event) {
        Player player = Bukkit.getPlayer(event.getPlayerUUID());

        checkAndUnlocked(player);
        sendUnlockedMessage(player);
    }

}
