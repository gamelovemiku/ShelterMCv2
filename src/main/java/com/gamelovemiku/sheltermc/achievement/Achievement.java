package com.gamelovemiku.sheltermc.achievement;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class Achievement implements Listener {

    private Plugin plugin;
    private String name = "Default";
    private String permission = "sheltermc.achi.default";
    private String world = "world";

    public Plugin getPlugin() {
        return plugin;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public void checkAndUnlocked(Player player) {
        if(!player.hasPermission(getPermission())) {
            new ShelterMCHelper().runOnConsole("lp user " + player.getName() + " permission set " + getPermission() + " true world=" + world);
            new ShelterMCHelper().runOnConsole("lp user " + player.getName() + " permission set " + getPermission() + " true world=world_limbo");
            sendUnlockedMessage(player);
        }

    }

    public void sendUnlockedMessage(Player player) {
        ShelterMCHelper helper = new ShelterMCHelper();

        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
            player.sendTitle(helper.formatInGameColor("&r"), helper.formatInGameColor("&a&lAchievement Unlocked!"),15,45,5);
            player.sendMessage(helper.formatInGameColor("&r"));
            player.sendMessage(helper.formatInGameColor("&a&lAchievement Unlocked! &7: " + getName()));
            player.sendMessage(helper.formatInGameColor("&r"));
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        }, helper.secondToTick(2));

        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
            player.sendTitle(helper.formatInGameColor("&r"), helper.formatInGameColor(getName()),15,20,15);
        }, helper.secondToTick(6));
    }

}
