package com.gamelovemiku.sheltermc.entity;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class AnimalMob implements Listener{
	
	LivingEntity living = null;
	World world = null;
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		
		world = event.getEntity().getWorld();
		living = event.getEntity();
		
		if(event.getEntity().getKiller() != null) {
			living.getKiller().sendMessage("DEBUG: ANIMALS DEATH -> " + entity.getType().toString() + " | Killed by " + living.getKiller().getName());
					
			PlayerInventory inventory = living.getKiller().getInventory();

			if(event.getEntity().getKiller().hasPermission("sheltermc.animals.eggdrop") || event.getEntity().getKiller().isOp()) {
				switch(entity.getType()) {
					case PIG:
						if(inventory.getItemInMainHand().getType() == Material.DIAMOND_SWORD) {
							this.randomDropItem(entity.getLocation(), 10, Material.PIG_SPAWN_EGG, 1);
						}
						break;
					case SHEEP:
						if(inventory.getItemInMainHand().getType() == Material.DIAMOND_SWORD) {
							this.randomDropItem(entity.getLocation(), 10, Material.SHEEP_SPAWN_EGG, 1);
						}
						break;
					case CHICKEN:
						if(inventory.getItemInMainHand().getType() == Material.DIAMOND_SWORD) {
							this.randomDropItem(entity.getLocation(), 10, Material.CHICKEN_SPAWN_EGG, 1);
						}
						break;
					case COW:
						if(inventory.getItemInMainHand().getType() == Material.DIAMOND_SWORD) {
							this.randomDropItem(entity.getLocation(), 10, Material.COW_SPAWN_EGG, 1);
						}
						break;
					case MUSHROOM_COW:
						if(inventory.getItemInMainHand().getType() == Material.DIAMOND_SWORD) {
							this.randomDropItem(entity.getLocation(), 10, Material.MOOSHROOM_SPAWN_EGG, 1);
						}
						break;
					default:
						//do nothing
						break;
				}
			}

		}
		
	}
	
	public void randomDropItem(Location location, int change, Material material, int amount) {
		if((int) (Math.random()*100) < change) {
			world.dropItemNaturally(location, new ItemStack(material, amount));
			living.getKiller().sendTitle(ChatColor.GOLD + "Extra Egg!", ChatColor.YELLOW + "สัตว์ตัวนี้ดรอปไข่!");
		}
	}
	
	public void sendScreenMessage(Player player, String message, String description) {
		player.sendTitle(null, message, 500, 3000, 500);
		player.sendMessage(description);
	}
	
	public int tickToSecond(int second) {
		return 20 * second;
	}
}
