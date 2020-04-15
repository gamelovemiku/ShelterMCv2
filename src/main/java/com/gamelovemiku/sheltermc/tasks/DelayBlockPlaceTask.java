package com.gamelovemiku.sheltermc.tasks;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DelayBlockPlaceTask extends BukkitRunnable {

    private Plugin plugin = null;
    private Block block = null;

    private int change = 0;

    private ShelterMCHelper helper = new ShelterMCHelper();

    public DelayBlockPlaceTask(Plugin plugin, Block block, int change) {
        this.plugin = plugin;
        this.block = block;
        this.change = change;
    }

    @Override
    public void run() {
        int rnd = helper.randomNumber(change);
        Bukkit.broadcastMessage("REGEN... change:" + String.valueOf(rnd) + "=" + (change-1) + " to replant");
        if(rnd == (change-1)) {
            Bukkit.broadcastMessage("PLANTED!");
            block.setType(Material.WHITE_WOOL);
            this.cancel();
        }
    }

    public void setCropAsFullyGrown(Block block) {
        BlockData data = block.getBlockData();
        if (data instanceof Ageable) {
            Ageable crop = (Ageable) data;
            crop.setAge(crop.getMaximumAge());
            block.setBlockData(crop);
        }
    }
}
