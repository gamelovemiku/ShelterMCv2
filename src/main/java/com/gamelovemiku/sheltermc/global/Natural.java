package com.gamelovemiku.sheltermc.global;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import com.gamelovemiku.sheltermc.tasks.AlertOnCloseRoofTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

public class Natural implements Listener{

	private Plugin plugin;
	private boolean running = false;
	private BukkitTask task;

	public Natural(Plugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onTopBedrock(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		ShelterMCHelper helper = new ShelterMCHelper();

		if(player.getWorld().equals(Bukkit.getWorld("world_shelter"))) {
			if(player.getLocation().getY() >= 160) {
				if (!running) {
					running = true;
					task = new AlertOnCloseRoofTask(this.plugin, player).runTaskTimer(plugin, 0, 100);
					//player.sendMessage("TASK IS ---> " + task.isCancelled());
				}
			}

			if(player.getLocation().getY() < 160 && running) {
				running = false;
				task.cancel();
				player.sendMessage("CANCELED IS ---> " + task.isCancelled());
			}
		}
	}
	
	@EventHandler
	public void onBottomBedrock(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		ShelterMCHelper helper = new ShelterMCHelper();

		if(player.getWorld().equals(Bukkit.getWorld("world_shelter"))) {
			if(player.getLocation().getY() <= 30) {
				if (!running) {
					running = true;
					task = new AlertOnCloseRoofTask(this.plugin, player).runTaskTimer(plugin, 0, 100);
					//player.sendMessage("TASK IS ---> " + task.isCancelled());
				}
			}

			if(player.getLocation().getY() < 160 && running) {
				running = false;
				task.cancel();
				player.sendMessage("CANCELED IS ---> " + task.isCancelled());
			}
		}
	}

	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(player.getWorld().equals(Bukkit.getWorld("world_shelter"))) {
			if(player.getLocation().getY() >= 160) {
				if (!running) {
					running = true;
					task = new AlertOnCloseRoofTask(this.plugin, player).runTaskTimer(plugin, 0, 100);
					//player.sendMessage("TASK IS ---> " + task.isCancelled());
				}
			}

			if(player.getLocation().getY() < 160 && running) {
				running = false;
				task.cancel();
				player.sendMessage("CANCELED IS ---> " + task.isCancelled());
			}
		}
	}

	public void onLeave(PlayerQuitEvent event) {
		running = false;
		task.cancel();
	}

	public void onDeath(PlayerDeathEvent event) {
		running = false;
		task.cancel();
	}
	
	public int secondToTick(int second) {
		return 20 * second;
	}
	
}
