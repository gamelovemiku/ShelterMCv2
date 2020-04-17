package com.gamelovemiku.sheltermc.tasks;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PermissionTimeOutCountdownTask extends BukkitRunnable {

    private Plugin plugin = null;
    private Player player = null;

    private String permission = "";
    private String message = "# Countdown.. %time second left!";
    private String world = "world";

    private int time = 0;
    private int time_start = 0;

    private ShelterMCHelper helper = new ShelterMCHelper();

    public PermissionTimeOutCountdownTask(Plugin plugin, Player player, String permission, String world, String message, int time) {
        this.plugin = plugin;
        this.player = player;
        this.permission = permission;
        this.world = world;
        this.message = message;
        this.time = time + 5;
        this.time_start = time;

        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
            helper.runOnConsole("lp user "+ player.getName()  + " permission settemp " + permission + " true " + time + "s world=" + world);
        }, helper.secondToTick(5));
    }

    @Override
    public void run() {
        try {
            if(time <= time_start) {
                player.sendTitle("", helper.formatInGameColor(message.replace("%time", String.valueOf(time))), 0,50,15);
                if(time == 0) {
                    player.sendTitle(helper.formatInGameColor(("&c&lTIME OUT!")), helper.formatInGameColor(("&eหมดเวลาแล้ว!")), 15,50,15);
                    this.cancel();
                }
            } else {
                player.sendTitle(
                        helper.formatInGameColor(("&f&l%time").replace("%time", String.valueOf(time-time_start))),
                        helper.formatInGameColor(("&aเตรียมพร้อม!")),
                        0,
                        50,
                        15);
                player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 1,1);
            }
            time--;
        }catch (Exception error) {
            helper.log("###### PermissionTimeOutCountdownTask getting error...!");
            this.cancel();
        }
    }
}
