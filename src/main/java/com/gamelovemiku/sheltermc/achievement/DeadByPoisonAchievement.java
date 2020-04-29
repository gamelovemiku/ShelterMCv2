package com.gamelovemiku.sheltermc.achievement;

import com.gamelovemiku.sheltermc.ShelterMC;
import com.gamelovemiku.sheltermc.ShelterMCHelper;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class DeadByPoisonAchievement extends Achievement implements Listener {

    public DeadByPoisonAchievement(Plugin plugin) {
        this.setPlugin(plugin);
        this.setName("&eพื้นที่ต้องห้าม");
        this.setPermission("sheltermc.achi.deadbypoison");
        this.setWorld("world");
    }

    @EventHandler
    public void doAction(PlayerDeathEvent event) {
        Player player = (Player) event.getEntity();

        if(player.getLocation().getY() >= 160 && player.getWorld().equals(Bukkit.getWorld("world_shelter"))) {
            checkAndUnlocked(player);
            Bukkit.getScheduler().scheduleSyncDelayedTask(this.getPlugin(), () -> {
                checkAndUnlocked(player);
            }, new ShelterMCHelper().secondToTick(15)); //6h
        }
    }

}
