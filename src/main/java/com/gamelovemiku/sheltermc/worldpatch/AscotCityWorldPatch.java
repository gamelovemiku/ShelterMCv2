package com.gamelovemiku.sheltermc.worldpatch;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import com.gamelovemiku.sheltermc.tasks.PermissionTimeOutCountdownTask;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class AscotCityWorldPatch implements Listener, CommandExecutor {

    private Plugin plugin = null;
    private ShelterMCHelper helper = new ShelterMCHelper();

    public AscotCityWorldPatch(Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            if(args[0].equalsIgnoreCase("oilver")) {
                if(args[1] != null) {
                    sender.sendMessage("Make " + args[2] + " can do.. " + args[0] + " for " + args[1] + " sec!");
                    new PermissionTimeOutCountdownTask(
                            plugin,
                            Bukkit.getPlayer(args[2]),
                            "sheltermc.temp.oilverfarm.break",
                            "&fคุณเหลือเวลาอีก &e&l%time &eวินาที &fในการเก็บเกี่ยว",
                            Integer.valueOf(args[1]))
                            .runTaskTimerAsynchronously(plugin, 0, helper.secondToTick(1));
                    return true;
                }
            }
        }
        return true;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        Block block = event.getBlock();

        int delay = 120;

        if (player.getWorld().equals(Bukkit.getWorld("world_ascotcity"))) {
            event.setCancelled(true);
            if(player.hasPermission("sheltermc.temp.oilverfarm.break")) {
                switch(block.getType().toString()) {
                    case "WHEAT":
                        event.setCancelled(false);
                        block.setType(Material.AIR);
                        block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.WHEAT, 1));
                        if(helper.randomNumber(20) < 2) {
                            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.WHEAT_SEEDS, 1));
                        }
                        setMaterial(block, Material.WHEAT, delay);
                        break;
                    case "POTATOES":
                        event.setCancelled(false);
                        setMaterial(block, Material.POTATOES, delay);
                        break;
                    case "CARROTS":
                        event.setCancelled(false);
                        setMaterial(block, Material.CARROTS, delay);
                        break;
                    case "BEETROOTS":
                        event.setCancelled(false);
                        block.setType(Material.AIR);
                        block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.BEETROOT, 1));
                        if(helper.randomNumber(20) < 6) {
                            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.BEETROOT_SEEDS, 1));
                        }
                        setMaterial(block, Material.BEETROOTS, delay);
                        break;
                    default:
                        if(!player.isOp()) {
                            player.sendMessage(helper.formatInGameColor("&4!!! &8[&cAscotGuard&8] &cคุณไม่สามารถกระทำการใด ๆ ใน Ascot City ได้ นอกเหนือจากที่กำหนด"));
                        } else {
                            event.setCancelled(false);
                        }
                        break;
                }
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(event.getPlayer().getWorld().equals(Bukkit.getWorld("world_ascotcity"))) {
            if(event.getPlayer().isOp()) {
                event.setCancelled(false);
            }
            event.setCancelled(true);
            event.getPlayer().sendMessage(helper.formatInGameColor("&4!!! &8[&cAscotGuard&8] &cคุณไม่สามารถกระทำการใด ๆ ใน Ascot City ได้ นอกเหนือจากที่กำหนด"));
        }
    }

    public void setMaterial(Block block, Material mat, int time) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
            block.setType(mat);
            Ageable ageable = (Ageable) block.getBlockData();
            ageable.setAge(ageable.getMaximumAge());
            block.setBlockData(ageable);
            block.getWorld().playSound(block.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
            block.getWorld().spawnParticle(Particle.COMPOSTER, block.getLocation().add(0, 1, 0), 30, 0.65,0.5,0.65,0.5);
            block.getWorld().spawnParticle(Particle.HEART, block.getLocation().add(0, 1, 0), 5);
        }, helper.secondToTick(helper.randomNumber(time)));
    }

}
