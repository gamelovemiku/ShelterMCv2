package com.gamelovemiku.sheltermc.global;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Natural implements Listener{
	
	@EventHandler
	public void onTopBedrock(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		if(player.getWorld().equals(Bukkit.getWorld("world_shelter"))) {
			if(player.getLocation().getY() > 160) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, this.secondToTick(300), 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, this.secondToTick(300), 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, this.secondToTick(60), 3));
				player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, this.secondToTick(300), 3));

				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10, 1);

				player.sendTitle(null, ChatColor.GOLD + "⚠️ You are at " + player.getLocation().getY(), 500, 3000, 500);
				player.sendMessage(ChatColor.GOLD + "☠ " + ChatColor.BOLD + "WARNING " + ChatColor.YELLOW + "You are Infected. Go down for your safety! - " + player.getLocation().getBlockY());
			}else if(player.getLocation().getY() > 140) {
				player.sendTitle(null, ChatColor.GOLD + "⚠️ You are at " + player.getLocation().getY(), 500, 3000, 500);
				player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, this.secondToTick(300), 1));
				player.sendMessage(ChatColor.GOLD + "☠ " + ChatColor.BOLD + "WARNING " + ChatColor.YELLOW + "You are too close to the top! There are many infection chemicals.");
			}
		}

		
	}
	
	@EventHandler
	public void onBottomBedrock(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		if(player.getWorld().equals(Bukkit.getWorld("world_shelter"))) {
			if(player.getLocation().getY() < 30) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, this.secondToTick(30), 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, this.secondToTick(60), 2));

				player.sendMessage(ChatColor.GOLD + "⚠ " + ChatColor.BOLD + "WARNING " + ChatColor.YELLOW + " You are too close to the bottom! -" + player.getLocation().getBlockY());
			}
		}
	}
	
	public int secondToTick(int second) {
		return 20 * second;
	}
	
}
