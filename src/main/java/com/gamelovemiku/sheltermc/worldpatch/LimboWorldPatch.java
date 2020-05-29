package com.gamelovemiku.sheltermc.worldpatch;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import com.gamelovemiku.sheltermc.tasks.PermissionTimeOutCountdownTask;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
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
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class LimboWorldPatch implements Listener, CommandExecutor {

    private Plugin plugin;
    private ShelterMCHelper helper = new ShelterMCHelper();

    public LimboWorldPatch(Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            if(args[0].equalsIgnoreCase("release")) {
                if(args[1] != null) {
                    sender.sendMessage("[Limbo] Releasing " + args[1] + " from limbo!");

                    Player player = Bukkit.getPlayer(args[1]);

                    player.teleport(new Location(Bukkit.getWorld("world"), -65.50, 65, -103.50, 0, 0));
                    player.sendMessage(helper.formatInGameColor("&r"));
                    player.sendMessage(helper.formatInGameColor("&4# &8[&cLimbo&8] &7คุณได้รับการปลดปล่อยจาก &6Limbo &7แล้ว!"));
                    player.sendTitle("", helper.formatInGameColor("&fคุณได้รับการปลดปล่อยจาก &6&lLimbo"), 15, 50, 15);
                    return true;
                }
            }
            if(args[0].equalsIgnoreCase("damonfarm")) {
                if(args[1] != null) {
                    sender.sendMessage("Make " + args[2] + " can do.. " + args[0] + " for " + args[1] + " sec!");
                    new PermissionTimeOutCountdownTask(
                            plugin,
                            Bukkit.getPlayer(args[2]),
                            "sheltermc.temp.damonfarm.break",
                            "world_limbo",
                            "&fคุณเหลือเวลาอีก &e&l%time &eวินาที &fในการเก็บเกี่ยว",
                            Integer.valueOf(args[1]))
                            .runTaskTimerAsynchronously(plugin, 0, helper.secondToTick(1));
                    return true;
                }
            }
        }
        return true;
    }

    public int getLife(Player player) {
        String raw = PlaceholderAPI.setPlaceholders(player, "%playerpoints_points%");
        return Integer.parseInt(raw);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        Block block = event.getBlock();

        int delay = 120;

        if (player.getWorld().equals(Bukkit.getWorld("world_limbo"))) {
            event.setCancelled(true);
            if(player.hasPermission("sheltermc.temp.damonfarm.break")) {
                switch(block.getType().toString()) {
                    case "NETHER_WART":
                        event.setCancelled(false);
                        block.setType(Material.AIR);
                        block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.NETHER_WART, 1));
                        setMaterial(block, Material.NETHER_WART, delay);
                        break;
                    default:
                        if(!player.isOp()) {
                            player.sendMessage(helper.formatInGameColor("&4!!! &8[&cLimbo&8] &cคุณไม่สามารถกระทำการใด ๆ ใน Limbo ได้ นอกเหนือจากที่กำหนด"));
                        } else {
                            event.setCancelled(false);
                        }
                        break;
                }
            } else {
                player.sendMessage(helper.formatInGameColor("&4!!! &8[&cLimbo&8] &cคุณไม่สามารถกระทำการใด ๆ ใน Limbo ได้ นอกเหนือจากที่กำหนด"));
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(event.getPlayer().getWorld().equals(Bukkit.getWorld("world_limbo"))) {
            if(event.getPlayer().isOp()) {
                event.setCancelled(false);
            } else {
                event.setCancelled(true);
                event.getPlayer().sendMessage(helper.formatInGameColor("&4!!! &8[&cLimbo&8] &cคุณไม่สามารถกระทำการใด ๆ ใน Limbo ได้ นอกเหนือจากที่กำหนด"));
            }
        }
    }

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        if(event.getPlayer().getWorld().equals(Bukkit.getWorld("world_limbo"))) {
            new ShelterMCHelper().runOnConsole("lp user " + event.getPlayer().getName() + " parent set limbo world=world_limbo");
        }
    }

    public void setMaterial(Block block, Material mat, int time) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
            block.setType(mat);
            Ageable ageable = (Ageable) block.getBlockData();
            ageable.setAge(ageable.getMaximumAge());
            block.setBlockData(ageable);
            block.getWorld().playSound(block.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
            block.getWorld().spawnParticle(Particle.LAVA, block.getLocation().add(0, 1, 0), 30, 0.65,0.5,0.65,0.5);
            block.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, block.getLocation().add(0, 1, 0), 5);
        }, helper.secondToTick(helper.randomNumber(time)));
    }
}
