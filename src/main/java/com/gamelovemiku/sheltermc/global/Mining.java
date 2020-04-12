package com.gamelovemiku.sheltermc.global;

import com.gamelovemiku.sheltermc.ShelterMC;
import com.gamelovemiku.sheltermc.ShelterMCHelper;
import com.gamelovemiku.sheltermc.perk.CropMasterPerk;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Mining implements Listener {

	Player player = null;
	ShelterMCHelper helper = new ShelterMCHelper();
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {

		player = event.getPlayer();
		
		PlayerInventory inventory = player.getInventory();
		Material handItem = inventory.getItemInMainHand().getType();
		
		if(event.getBlock().getType().equals(Material.STONE) && !player.getGameMode().equals(GameMode.CREATIVE)) {
			
			World world = event.getBlock().getWorld();
			Location atBlockLocation = event.getBlock().getLocation();
			
			player.sendMessage("DEBUG: Using -> " + handItem);
			
			switch(handItem) {
				case DIAMOND_PICKAXE:
					this.spawnParticle(world, atBlockLocation, Particle.HEART, 5);

					//add drop
					this.dropWithChange(world, atBlockLocation, Material.DIAMOND, 10);
					this.dropWithChange(world, atBlockLocation, Material.EMERALD, 60);
					this.dropWithChange(world, atBlockLocation, Material.IRON_INGOT, 100);
					this.dropWithChange(world, atBlockLocation, Material.GOLD_INGOT, 200);
					this.dropWithChange(world, atBlockLocation, Material.COAL, 100);

					break;
				case IRON_PICKAXE:
					this.spawnParticle(world, atBlockLocation, Particle.BUBBLE_POP, 5);

					//add drop
					this.dropWithChange(world, atBlockLocation, Material.DIAMOND, 3);
					this.dropWithChange(world, atBlockLocation, Material.EMERALD, 30);
					this.dropWithChange(world, atBlockLocation, Material.IRON_INGOT, 40);
					this.dropWithChange(world, atBlockLocation, Material.GOLD_INGOT, 200);
					this.dropWithChange(world, atBlockLocation, Material.COAL, 100);
					if(this.spawnEntityWithChange(world, atBlockLocation, EntityType.PRIMED_TNT, 30) == true) {
						this.sendBombMsg();
					}
					break;
				case GOLDEN_PICKAXE:
					this.spawnParticle(world, atBlockLocation, Particle.CRIT_MAGIC, 5);

					//add drop
					this.dropWithChange(world, atBlockLocation, Material.IRON_INGOT, 100);
					this.dropWithChange(world, atBlockLocation, Material.GOLD_INGOT, 50);
					this.dropWithChange(world, atBlockLocation, Material.COAL, 100);
					if(this.spawnEntityWithChange(world, atBlockLocation, EntityType.PRIMED_TNT, 90) == true) {
						this.sendBombMsg();
					}
					break;
				case STONE_PICKAXE:
					this.spawnParticle(world, atBlockLocation, Particle.SPELL_WITCH, 5);

					//add drop
					this.dropWithChange(world, atBlockLocation, Material.IRON_INGOT, 30);
					this.dropWithChange(world, atBlockLocation, Material.GOLD_INGOT, 100);
					this.dropWithChange(world, atBlockLocation, Material.COAL, 100);
					if(this.spawnEntityWithChange(world, atBlockLocation, EntityType.PRIMED_TNT, 270) == true) {
						this.sendBombMsg();
					}
					break;
				case WOODEN_PICKAXE:
					this.spawnParticle(world, atBlockLocation, Particle.FIREWORKS_SPARK, 5);

					//add drop
					this.dropWithChange(world, atBlockLocation, Material.IRON_NUGGET, 20);
					this.dropWithChange(world, atBlockLocation, Material.GOLD_INGOT, 100);
					this.dropWithChange(world, atBlockLocation, Material.COAL, 100);
					if(this.spawnEntityWithChange(world, atBlockLocation, EntityType.PRIMED_TNT, 400) == true) {
						this.sendBombMsg();
					}
					break;
				case AIR:
					player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, helper.secondToTick(3), 1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.HARM, helper.secondToTick(3), 1));
					break;
				default:
					//do nothing
					break;
				
			}
		}
	}

	public void sendBombMsg() {
		this.sendScreenMessage(player, null, ChatColor.RED + "!!!!!!!!!!!! Get away from here !!!!!!!!!!!!");
	}

	public void dropWithChange(World world, Location location, Material material, int change) {
		Random rand = new Random();
		int sum = rand.nextInt(1000);
		if(sum < change) {
			if(material == Material.DIAMOND) {
				Random rand2 = new Random();
				int sum2 = rand2.nextInt(100);
				if(sum2 < 6) {
					this.player.sendMessage(ChatColor.LIGHT_PURPLE + "DEBUG: addiction for diamond:" + sum2 + " | change:4");
					this.player.sendTitle(ChatColor.AQUA + "You found Diamond!", "", 1000, 3000, 2000);
					world.dropItemNaturally(location, new ItemStack(material));
				}
			} else {
				world.dropItemNaturally(location, new ItemStack(material));
			}
			this.player.sendMessage(ChatColor.GREEN + "DEBUG: random:" + sum + " | change:" + change + " for " + material.toString());
		} else {
			this.player.sendMessage(ChatColor.RED + "DEBUG: random:" + sum + " | change: <" + change + " for " + material.toString());
		}
	}
	
	public void spawnParticle(World world, Location location, Particle particle, int amount) {
		world.spawnParticle(particle, location.getX(), location.getY()+1, location.getZ(), amount);
	}

	
	public boolean spawnEntityWithChange(World world, Location location, EntityType entity, int change) {
		Random rand = new Random();
		int sum = rand.nextInt(1000);
		if(sum < change) {
			world.spawnEntity(location, entity);
			return true;
		}
		return false;
	}

	@EventHandler
	public void onDragBackHacking(AntiAuraAPI.DragBackEvent event) {
		//event.setCancelled(true);
		Player player = event.getPlayer();

		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (p.isOp()) {
				p.sendMessage(helper.formatInGameColor("&c!! &4[&cAntiCheat&4] &c" + player.getName()) + " ถูกดึกกลับมาที่เดิมจากการใช้ " + event.getHack());
			}
		}

	}

	@EventHandler
	public void onDetectedHacking(AntiAuraAPI.ViolationEvent event) {
		event.setCancelALL(true);
		Player player = event.getPlayer();

		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (p.isOp()) {
				p.sendMessage(helper.formatInGameColor("&c!! &4[&cAntiCheat&4] &c" + player.getName()) + " กำลังถูกสงสัยว่าจะใช้ " + event.getHack());
			}
		}

	}

	public void sendScreenMessage(Player player, String message, String description) {
		player.sendTitle(null, message, 500, 3000, 500);
		player.sendMessage(description);
	}
	
	
	@EventHandler
	public void onEnchant(EnchantItemEvent enchant) {
		
		Player player = enchant.getEnchanter();
		
		player.sendMessage("DEBUG: Enchant Level Needed -> " + enchant.getExpLevelCost());
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent j) {
		
		Player player = j.getPlayer();
		player.sendMessage("Joined! -> " + player.getName().toString());
		
	}
	
}
