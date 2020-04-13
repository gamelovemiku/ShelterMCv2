package com.gamelovemiku.sheltermc.perk;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import org.apache.commons.lang.StringUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import world.bentobox.bentobox.listeners.flags.protection.BreakBlocksListener;
import world.bentobox.bentobox.lists.Flags;

public class CropMasterPerk extends Perk implements Listener {

    Player player = null;
    ShelterMCHelper helper = new ShelterMCHelper();

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        player = event.getPlayer();
        Block block = event.getBlock();

        int[] change = new int[] {
                0,
                4,
                9,
                15,
                24,
                29,
                32,
                35,
        };

        BreakBlocksListener bb = new BreakBlocksListener();
        bb.onBlockBreak(event);

        if(player.getWorld() == Bukkit.getWorld("world_shelter") && bb.checkIsland(event, player, block.getLocation(), Flags.BREAK_BLOCKS)) {
            player.sendMessage(block.getType().toString() + " !!!!");
            switch (block.getType().toString()) {
                case "WHEAT":
                case "CARROTS":
                case "POTATOES":
                    if (player.hasPermission("sheltermc.perk.cropmaster.lv0")) {
                        for (int i = 7; i >= 0; i--) {
                            if (player.hasPermission("sheltermc.perk.cropmaster.lv" + i)) {
                                doAction(block, change[i], Particle.CLOUD);
                                break;
                            }
                        }
                    } else {
                        doAction(block, change[0], null);
                    }
                    break;
                default:
                    break;
            }
        }

    }

    public boolean doAction(Block block, int change, Particle particle) {
        switch (block.getType().toString()) {
            case "WHEAT":
                if (isFullyGrown(block)) {
                    if (randomDropItem(block, change, Material.POTATO, 7)) {
                        block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.WHEAT_SEEDS, helper.randomNumber(1)));
                        player.getWorld().spawnParticle(particle, player.getLocation(), 3);
                    }
                }
                break;
            case "CARROTS":
                if (isFullyGrown(block)) {
                    if (isFullyGrown(block)) {
                        if (randomDropItem(block, change, Material.CARROT, 7)) {
                            player.getWorld().spawnParticle(particle, player.getLocation(), 3);
                        }
                    }
                }
                break;
            case "POTATOES":
                if (isFullyGrown(block)) {
                    if (randomDropItem(block, change, Material.POTATO, 7)) {
                        player.getWorld().spawnParticle(particle, player.getLocation(), 3);
                    }
                }
                break;
        }
        return true;
    }

    public boolean randomDropItem(Block block, int change, Material material, int max) {
        ShelterMCHelper helper = new ShelterMCHelper();
        int amount = helper.randomNumber(max);
        if(helper.randomNumber(100) < change) {
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(material, amount));
            block.setType(Material.AIR);
            player.sendMessage(helper.formatInGameColor("&e# &8[&ePerk&8] &7ได้รับ &e" + StringUtils.capitalize(material.toString().toLowerCase()) + "&7 เพิ่ม &e" + amount + " ชิ้น &7จากความสามารถพิเศษ /" + change + "%" ));
            return true;
        }
        return false;
    }

    public boolean isFullyGrown(Block block) {
        if(block.getBlockData() instanceof Ageable) {
            Ageable crop = (Ageable) block.getBlockData();
            return (crop.getMaximumAge() == crop.getAge());
        }
        return false;
    }

}
