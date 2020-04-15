package com.gamelovemiku.sheltermc.worldpatch;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import com.gamelovemiku.sheltermc.tasks.AlertOnTopRoofTask;
import com.gamelovemiku.sheltermc.tasks.DelayBlockPlaceTask;
import com.gamelovemiku.sheltermc.tasks.PermissionTimeOutCountdownTask;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class AscotCityWorldPatch implements Listener, CommandExecutor {

    private Plugin plugin = null;
    private BukkitTask task;
    private ShelterMCHelper helper = new ShelterMCHelper();

    public AscotCityWorldPatch(Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (player instanceof Player) {
            this.task = new PermissionTimeOutCountdownTask(
                    this.plugin,
                    player,
                    "sheltermc.temp.oilverdeal",
                    "# คุณเหลือเวลาอีก %time วินาทีในการเก็บเกี่ยว",
                    20)
                    .runTaskTimer(plugin, 0, helper.secondToTick(1));
        }
        return true;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        Block block = event.getBlock();

        player.sendMessage("BREAKING OUTSIDE ---> " + block.getType().toString());

        if (player.getWorld().equals(Bukkit.getWorld("world_ascotcity"))) {
            event.setCancelled(true);
            switch(block.getType().toString()) {
                case "WHEAT":
                    event.setCancelled(false);
                    block.setType(Material.WHEAT);
                    setMaterial(block, Material.WHEAT, 10);
                    break;
                case "POTATOES":
                    event.setCancelled(false);
                    setMaterial(block, Material.POTATOES, 10);
                    break;
                case "CARROTS":
                    event.setCancelled(false);
                    setMaterial(block, Material.CARROTS, 10);
                    break;
                case "BEETROOTS":
                    event.setCancelled(false);
                    setMaterial(block, Material.BEETROOTS, 10);
                    break;

            }
        } else {
            player.sendMessage(helper.formatInGameColor("&4!!! &8[&cAscotGuard&8] &cคุณไม่สามารถกระทำการใด ๆ ใน Ascot City ได้ นอกเหนือจากที่กำหนด"));
        }
    }

    public void setMaterial(Block block, Material mat, int time) {
        block.setType(mat);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
            Bukkit.broadcastMessage("PLANTEDDD! | " + mat.toString());
            block.setType(mat);
            Ageable ageable = (Ageable) block.getBlockData();
            ageable.setAge(ageable.getMaximumAge());
            block.setBlockData(ageable);
        }, helper.secondToTick(helper.randomNumber(time)));
    }

}
