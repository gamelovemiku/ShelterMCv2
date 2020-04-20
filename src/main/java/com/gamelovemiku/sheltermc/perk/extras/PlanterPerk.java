package com.gamelovemiku.sheltermc.perk.extras;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import com.gamelovemiku.sheltermc.perk.Perk;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import world.bentobox.bentobox.listeners.flags.protection.BreakBlocksListener;
import world.bentobox.bentobox.lists.Flags;

public class PlanterPerk extends Perk implements Listener {

    Player player = null;
    ShelterMCHelper helper = new ShelterMCHelper();

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        player = event.getPlayer();
        Block block = event.getBlock();

        int[] change = new int[] {
                0,
                3,
                5,
                9,
                12,
                16,
                20,
                24,
        };

        BreakBlocksListener bb = new BreakBlocksListener();
        bb.onBlockBreak(event);

        if(player.getWorld() == Bukkit.getWorld("world_shelter") && bb.checkIsland(event, player, block.getLocation(), Flags.BREAK_BLOCKS)) {
            player.sendMessage(block.getType().toString() + " !!!!");
            switch (block.getType().toString()) {
                case "GRASS_BLOCK":
                    if (player.hasPermission("sheltermc.perk.planter.lv0")) {
                        for (int i = 7; i >= 0; i--) {
                            if (player.hasPermission("sheltermc.perk.planter.lv" + i)) {
                                doAction(block, change[i], Particle.CLOUD);
                                break;
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }

    }

    public boolean doAction(Block block, int change, Particle particle) {
        switch (block.getType().toString()) {
            case "GRASS_BLOCK":
                if(helper.randomNumber(100) < change) {
                    int rnd = helper.randomNumber(100);
                    player.sendMessage(rnd + "/100 !!!!");
                    if (rnd <= 20) {
                        block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.WHEAT_SEEDS, 1));
                    } else if(rnd <= 40) {
                        block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.BEETROOT_SEEDS, 1));
                    } else if(rnd <= 60) {
                        block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.CARROT, 1));
                    } else if(rnd <= 80) {
                        block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.POTATO, 1));
                    } else if(rnd <= 100) {
                        block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.COCOA_BEANS, 1));
                    }
                }

                break;
        }
        return true;
    }

}
