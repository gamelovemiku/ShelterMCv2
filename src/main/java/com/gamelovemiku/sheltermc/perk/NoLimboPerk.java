package com.gamelovemiku.sheltermc.perk;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class NoLimboPerk implements Listener {

    Player player = null;
    ShelterMCHelper helper = new ShelterMCHelper();

    @EventHandler()
    public boolean hasPerk(PlayerRespawnEvent event) {
        int[] change = new int[] {
                100,
                82,
                65,
                40,
                33,
                25,
                19,
                7,
        };

        player = event.getPlayer();

        if(player instanceof Player) {
            if(player.hasPermission("sheltermc.perk.nolimbo.lv0")) {
                for (int i = 7; i >= 0 ; i--) {
                    if(player.hasPermission("sheltermc.perk.nolimbo.lv" + i)) {
                        int num = helper.randomNumber(100);
                        if(num < change[i]) {
                            event.getPlayer().sendMessage(helper.formatInGameColor("DEBUG (random:" + num + " < " + change[i] + "): #" + i));
                            event.getPlayer().sendMessage(helper.formatInGameColor("GO LIMBOOOOOOOOOOOOOOOOO" + i));
                            spawnInLimbo(event);
                            return true;
                        } else {
                            event.getPlayer().sendMessage(helper.formatInGameColor("DEBUG (random:" + num + " < " + change[i] + "): #" + i));
                            event.setRespawnLocation(new Location(Bukkit.getWorld("world"), -65.5, 65, -103.5, 0, 3));
                            return true;
                        }
                    }
                }
            } else {
                this.spawnInLimbo(event);
            }
        }
        return true;
    }

    public void spawnInLimbo(PlayerRespawnEvent event) {
        event.setRespawnLocation(new Location(Bukkit.getWorld("world_limbo"), 93.5, 37, 180, -180, 3));
        event.getPlayer().sendMessage(helper.formatInGameColor("&c&lINTO THE DEATH &7You are dead! now you are in Limbo"));
    }
}
