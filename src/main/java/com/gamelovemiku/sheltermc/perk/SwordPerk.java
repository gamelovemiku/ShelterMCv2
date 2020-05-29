package com.gamelovemiku.sheltermc.perk;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwordPerk extends Perk implements Listener {

    private Player killer = null;
    private ShelterMCHelper helper = new ShelterMCHelper();

    @EventHandler
    public boolean hasPerk(EntityDeathEvent event) {
        int[] change = new int[] {
                42,
                31,
                24,
                19,
                16,
                12,
                9,
                3,
        };

        Entity entity = event.getEntity();
        killer = event.getEntity().getKiller();

        if(killer instanceof Player) {
            switch (entity.getType()) {
                case ZOMBIE:
                case SPIDER:
                case CREEPER:
                case SKELETON:
                    if(killer.hasPermission("sheltermc.perk.sword.lv0")) {
                        for (int i = 7; i >= 0 ; i--) {
                            if(killer.hasPermission("sheltermc.perk.sword.lv" + i)) {
                                doAction(entity, change[i]);
                                entity.getWorld().spawnParticle(Particle.LAVA, entity.getLocation(), i*4);
                                break;
                            }
                        }
                    } else {
                        doAction(entity, change[0]);
                    }
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    public boolean doAction(Entity entity, int change) {
        switch (entity.getType()) {
            case ZOMBIE:
                if (helper.randomNumber(100) < change) {
                    this.spawnBabyZombie(entity.getLocation());
                }
                break;
            case SKELETON:
                this.spawnEntityByAmountWithChange(entity.getWorld(), entity.getLocation(), EntityType.SILVERFISH, change, 5);
                break;
            case CREEPER:
                this.spawnEntityByAmountWithChange(entity.getWorld(), entity.getLocation(), EntityType.PRIMED_TNT, change, 2);
                break;
            case SPIDER:
                if (helper.randomNumber(100) < change) {
                    this.spawnCaveSpider(entity.getLocation());
                }
                break;
            default:
                //do nothing
                break;
        }
        return true;
    }

    public boolean spawnEntityByAmountWithChange(World world, Location location, EntityType entity, int change, int maxspawn) {
        if(helper.randomNumber(100) < change) {
            for(int i = 0; i < helper.randomNumber(maxspawn); i++) {
                world.spawnEntity(location, entity);
                killer.sendTitle("", helper.formatInGameColor("&6&lWARNING: &fMonster ขยายตัว!"), 15, 35,15);
            }
            helper.sendSubtitle(killer, null, ChatColor.RED + "☠ " + ChatColor.BOLD + "Monster นี้ได้รับการวิวัฒนาการมาจากสารพิษ!");
        }
        return true;
    }

    public Zombie spawnBabyZombie(Location location) {
        Zombie zombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        zombie.setBaby(true);
        zombie.setRemoveWhenFarAway(true);
        zombie.setCustomName(ChatColor.RED + "₪ " + ChatColor.BOLD + "Revenger Kid");
        zombie.setHealth(10);
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, helper.secondToTick(15), 3));
        return zombie;
    }

    public CaveSpider spawnCaveSpider(Location location) {
        CaveSpider cavespider = (CaveSpider) location.getWorld().spawnEntity(location, EntityType.CAVE_SPIDER);
        cavespider.setRemoveWhenFarAway(true);
        cavespider.setCustomName(ChatColor.AQUA + " " + ChatColor.BOLD + "Angry Spider");
        cavespider.setHealth(6);
        cavespider.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, helper.secondToTick(15), 6));
        cavespider.setTicksLived(helper.secondToTick(30));
        return cavespider;
    }

}
