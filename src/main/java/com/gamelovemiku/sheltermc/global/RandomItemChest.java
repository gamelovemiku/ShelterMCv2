package com.gamelovemiku.sheltermc.global;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import com.gamelovemiku.sheltermc.tasks.PermissionTimeOutCountdownTask;
import com.lithium3141.shellparser.ShellParser;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class RandomItemChest implements CommandExecutor, Listener {

    private Plugin plugin;
    private List<Material> materialList = new ArrayList<Material>();
    private List<Location> chestLocationList = new ArrayList<Location>();
    private List<Location> chestLocationOpenedList = new ArrayList<Location>();

    public RandomItemChest(Plugin plugin) {
        this.plugin = plugin;

        this.materialList.add(Material.COBBLESTONE);
        this.materialList.add(Material.WHEAT);
        this.materialList.add(Material.WHEAT_SEEDS);
        this.materialList.add(Material.OAK_LOG);
        this.materialList.add(Material.WOODEN_PICKAXE);
        this.materialList.add(Material.IRON_NUGGET);
        this.materialList.add(Material.GOLD_NUGGET);
        this.materialList.add(Material.MELON_SEEDS);
        this.materialList.add(Material.STICK);
        this.materialList.add(Material.STRING);
        this.materialList.add(Material.ROTTEN_FLESH);
        this.materialList.add(Material.FEATHER);
        this.materialList.add(Material.CHARCOAL);
        this.materialList.add(Material.COD);
        this.materialList.add(Material.SALMON);
        this.materialList.add(Material.EXPERIENCE_BOTTLE);
        this.materialList.add(Material.BREAD);
        this.materialList.add(Material.MELON_SLICE);
        this.materialList.add(Material.GUNPOWDER);
        this.materialList.add(Material.FLINT_AND_STEEL);
        this.materialList.add(Material.SUGAR_CANE);
        this.materialList.add(Material.COCOA_BEANS);
        this.materialList.add(Material.SUGAR);
        this.materialList.add(Material.BOWL);
        this.materialList.add(Material.ARROW);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        chestLocationList.clear();

        sender.sendMessage("=======================================");
        sender.sendMessage("ARRAY SIZE: " + chestLocationList.size());
        sender.sendMessage("BLOCK: " + chestLocationList.get(0).getBlock().getType().toString());

        return true;
    }

    @EventHandler
    public void onClickedBlock(PlayerInteractEvent event) {
        try {
            Player player = event.getPlayer();
            Block block = event.getClickedBlock();
            if (block.getWorld().equals(Bukkit.getWorld("world_ascotcity"))) {
                //player.sendMessage("CLICKED: " + block.getType().toString() + " at X" + block.getLocation().getX() + " Y" + block.getLocation().getY() + " Z" + block.getLocation().getZ());
                if (block.getType().equals(Material.CHEST)) {
                    if (isOpened(block) == false) {
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
                        putIntoChest(block);
                        chestLocationOpenedList.add(block.getLocation());
                        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
                            block.getWorld().spawnParticle(Particle.LAVA, block.getLocation().add(0, 1, 0), 30, 0.65, 0.5, 0.65, 0.5);
                            removeOpened(block);
                        }, new ShelterMCHelper().secondToTick(10)); //6h
                    } else {
                        player.sendMessage(new ShelterMCHelper().formatInGameColor("&7# &8[&6Chest&8] &7ดูเหมือนว่าจะมีคนเอาของจากล่องนี้ไปแล้ว ลองมาดูใหม่ทีหลังนะ"));
                    }
                }
            }
        } catch (NullPointerException error) {
            for (Player playerOperater : Bukkit.getServer().getOnlinePlayers()) {
                if (playerOperater.isOp()) {
                    event.getPlayer().sendMessage(new ShelterMCHelper().formatInGameColor(
                            "&c&lERROR WATCHDOGS: &6Failed to put item in chest at " + event.getClickedBlock().getLocation().toString()));
                }
            }
        }

    }

    public void putIntoChest(Block block) {
        if (block.getType().equals(Material.CHEST)) {
            ShelterMCHelper helper = new ShelterMCHelper();
            Chest chest = (Chest) block.getState();
            Inventory inventory = chest.getInventory();
            inventory.clear();
            for (int i = 0; i < helper.randomNumber(materialList.size()); i++) {
                int index = helper.randomNumber(27);
                if (inventory.getItem(index) == null) {
                    inventory.setItem(index, new ItemStack(materialList.get(helper.randomNumber(materialList.size())), helper.randomNumber(4)));
                }
            }
        }
    }

    public boolean isOpened(Block block) {
        for (int i = 0; i < chestLocationOpenedList.size(); i++) {
            if (block.getLocation().equals(chestLocationOpenedList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean removeOpened(Block block) {
        for (int i = 0; i < chestLocationOpenedList.size(); i++) {
            if (block.getLocation().equals(chestLocationOpenedList.get(i))) {
                chestLocationOpenedList.remove(i);
                return true;
            }
        }
        return false;
    }

}
