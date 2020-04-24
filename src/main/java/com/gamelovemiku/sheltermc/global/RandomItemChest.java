package com.gamelovemiku.sheltermc.global;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import com.gamelovemiku.sheltermc.tasks.PermissionTimeOutCountdownTask;
import com.lithium3141.shellparser.ShellParser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
        this.materialList.add(Material.GOLD_ORE);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        chestLocationList.clear();
        registerChestLocation();
        putIntoChestThatAlreadySet();

        sender.sendMessage("=======================================");
        sender.sendMessage("ARRAY SIZE: " + chestLocationList.size());
        sender.sendMessage("BLOCK: " + chestLocationList.get(0).getBlock().getType().toString());

        return true;
    }

    @EventHandler
    public void onClickedBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        player.sendMessage("CLICKED: " + block.getType().toString() + " at X" + block.getLocation().getX() + " Y" + block.getLocation().getY() + " Z" + block.getLocation().getZ());

        if(block.getType().equals(Material.CHEST)) {
            if(isOpened(block) == false) {
                chestLocationOpenedList.add(block.getLocation());
                Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
                    putIntoChest(block);
                    removeOpened(block);
                    player.sendMessage("REFILLED!");
                }, new ShelterMCHelper().secondToTick(10));
            }

        }
    }

    public void putIntoChest(Block block) {
        if(block.getType().equals(Material.CHEST)) {
            ShelterMCHelper helper = new ShelterMCHelper();
            Chest chest = (Chest) block.getState();
            Inventory inventory = chest.getInventory();
            inventory.clear();
            for (int i = 0; i < helper.randomNumber(materialList.size()); i++) {
                int index = helper.randomNumber(27);
                if(inventory.getItem(index) == null) {
                    inventory.setItem(index, new ItemStack(materialList.get(helper.randomNumber(materialList.size())), helper.randomNumber(4)));
                }
            }
        }
    }

    public boolean putIntoChestThatAlreadySet() {
        for (int i = 0; i < chestLocationList.size(); i++) {
            Block block = chestLocationList.get(i).getBlock();
            if(block.getType().equals(Material.CHEST)) {
                ShelterMCHelper helper = new ShelterMCHelper();
                Chest chest = (Chest) block.getState();
                Inventory inventory = chest.getInventory();
                inventory.clear();
                for (int j = 0; j < helper.randomNumber(materialList.size()); j++) {
                    int index = helper.randomNumber(27);
                    if(inventory.getItem(index) == null) {
                        inventory.setItem(index, new ItemStack(materialList.get(helper.randomNumber(materialList.size())), helper.randomNumber(4)));
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean isOpened(Block block) {
        for (int i = 0; i < chestLocationOpenedList.size(); i++) {
            if(block.getLocation().equals(chestLocationOpenedList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean removeOpened(Block block) {
        for (int i = 0; i < chestLocationOpenedList.size(); i++) {
            if(block.getLocation().equals(chestLocationOpenedList.get(i))) {
                chestLocationOpenedList.remove(i);
                return true;
            }
        }
        return false;
    }


    public void registerChestLocation() {
        chestLocationList.add(new Location(Bukkit.getWorld("world_ascotcity"), 254,189,-461));
    }

}
