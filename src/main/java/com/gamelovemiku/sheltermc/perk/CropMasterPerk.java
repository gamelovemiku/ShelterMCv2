package com.gamelovemiku.sheltermc.perk;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import world.bentobox.bentobox.api.events.IslandBaseEvent;
import world.bentobox.bentobox.listeners.flags.protection.BreakBlocksListener;

import java.util.ArrayList;
import java.util.List;

public class CropMasterPerk extends Perk implements Listener {

    Player player = null;
    ShelterMCHelper helper = new ShelterMCHelper();

    @EventHandler
    public void onBreak(BlockBreakEvent event, BreakBlocksListener bb) {
        player = event.getPlayer();
        Block block = event.getBlock();

        if(player.getWorld() != Bukkit.getWorld("world_shelter")) {
            if (block.getType().equals(Material.WHEAT)) {
                if (isFullyGrown(block)) {
                    int amount = helper.randomNumber(10);
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.WHEAT, amount));
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.WHEAT_SEEDS, helper.randomNumber(2)));
                    player.sendMessage(helper.formatInGameColor("&a+ &e[Perk] &7ได้รับข้าวสาลี &9" + amount + " ชิ้น &7จากการอัพเกรดความสามารถ"));
                }
            }

            if (block.getType().equals(Material.CARROTS)) {
                if(isFullyGrown(block)) {
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.CARROT, helper.randomNumber(10)));
                    player.sendMessage("CARROTTTTTTTTTTTTTT");
                }
            }

            if (block.getType().equals(Material.POTATOES)) {
                if(isFullyGrown(block)) {
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.POTATO, helper.randomNumber(10)));
                    player.sendMessage("POTATOSSSSSSSS");
                }
            }
        }

    }

    public boolean isFullyGrown(Block block) {
        if(block.getBlockData() instanceof Ageable) {
            Ageable crop = (Ageable) block.getBlockData();
            return (crop.getMaximumAge() == crop.getAge());
        }
        return false;
    }

}
