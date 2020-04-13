package com.gamelovemiku.sheltermc.perk;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class AnimalsGodPerk implements Listener {

    Player killer = null;
    World world = null;

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        world = event.getEntity().getWorld();
        killer = event.getEntity().getKiller();

        int[] change = new int[] {
                0, //No Drop
                2,
                5,
                7,
                9,
                12,
                15,
                18, //18% change for godofanimals lv.7
        };

        if(event.getEntity().getKiller() != null) {
            if(killer instanceof Player) {
                switch (entity.getType()) {
                    case PIG:
                    case SHEEP:
                    case CHICKEN:
                    case COW:
                    case MUSHROOM_COW:
                        if (killer.hasPermission("sheltermc.perk.godofanimals.lv0")) {
                            for (int i = 7; i >= 0; i--) {
                                //killer.sendMessage("DEBUG: Searching: #" + i + " change:" + change[i]);
                                if (killer.hasPermission("sheltermc.perk.godofanimals.lv" + i)) {
                                    doAction(entity, change[i], Particle.FLASH);
                                   // killer.sendMessage("DEBUG: ANIMAL SPAWNED -> change:" + change[i]);
                                    break;
                                }
                            }
                        } else {
                            doAction(entity, change[0], null);
                        }
                        break;
                    default:
                        break;
                }
            }

        }

    }

    public boolean doAction(Entity entity, int change, Particle particle) {
        switch(entity.getType()) {
            case PIG:
                if(this.randomDropItem(entity.getLocation(), change, Material.PIG_SPAWN_EGG, 1)) {
                    entity.getWorld().spawnParticle(particle, entity.getLocation(), change * 2);
                }
                break;
            case SHEEP:
                if(this.randomDropItem(entity.getLocation(), change, Material.SHEEP_SPAWN_EGG, 1)) {
                    entity.getWorld().spawnParticle(particle, entity.getLocation(), change * 2);
                }
                break;
            case CHICKEN:
                if(this.randomDropItem(entity.getLocation(), change, Material.CHICKEN_SPAWN_EGG, 1)) {
                    entity.getWorld().spawnParticle(particle, entity.getLocation(), change * 2);
                }
                break;
            case COW:
                if(this.randomDropItem(entity.getLocation(), change, Material.COW_SPAWN_EGG, 1)) {
                    entity.getWorld().spawnParticle(particle, entity.getLocation(), change * 2);
                }
                break;
            case MUSHROOM_COW:
                if(this.randomDropItem(entity.getLocation(), change, Material.MOOSHROOM_SPAWN_EGG, 1)) {
                    entity.getWorld().spawnParticle(particle, entity.getLocation(), change * 2);
                }
                break;
            default:
                //do nothing
                break;
        }
        return false;
    }

    public boolean randomDropItem(Location location, int change, Material material, int amount) {
        ShelterMCHelper helper = new ShelterMCHelper();
        if(helper.randomNumber(100) < change) {
            world.dropItemNaturally(location, new ItemStack(material, amount));
            killer.sendMessage(ChatColor.YELLOW + "สัตว์ตัวนี้ดรอปไข่!");
            return true;
        }
        return false;
    }

    public void sendScreenMessage(Player player, String message, String description) {
        player.sendTitle(null, message, 500, 3000, 500);
        player.sendMessage(description);
    }

}
