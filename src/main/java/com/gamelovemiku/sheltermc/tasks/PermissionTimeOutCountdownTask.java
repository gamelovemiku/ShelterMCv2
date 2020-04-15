package com.gamelovemiku.sheltermc.tasks;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PermissionTimeOutCountdownTask extends BukkitRunnable {

    private Plugin plugin = null;
    private Player player = null;

    private String permission = "";
    private String message = "# Countdown.. %time second left!";

    private int time = 0;

    private ShelterMCHelper helper = new ShelterMCHelper();

    public PermissionTimeOutCountdownTask(Plugin plugin, Player player, String permission, String message, int time) {
        this.plugin = plugin;
        this.player = player;
        this.permission = permission;
        this.message = message;
        this.time = time;
    }

    @Override
    public void run() {
        time--;
        player.sendMessage(helper.formatInGameColor(message.replace("%time", String.valueOf(time))));
        if(time == 0) {
            this.cancel();
        }
    }
}
