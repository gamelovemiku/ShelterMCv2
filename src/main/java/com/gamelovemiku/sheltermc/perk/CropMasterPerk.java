package com.gamelovemiku.sheltermc.perk;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class CropMasterPerk extends Perk implements Listener {

    Player player = null;
    ShelterMCHelper helper = new ShelterMCHelper();

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        player = event.getPlayer();
        Block block = event.getBlock();

        if(player.getWorld() == Bukkit.getWorld("world_shelter")) {
            if (block.getType().equals(Material.WHEAT)) {
                if (isFullyGrown(block)) {
                    int amount = helper.randomNumber(7);
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.WHEAT, amount));
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.WHEAT_SEEDS, helper.randomNumber(2)));
                    block.setType(Material.AIR);
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
