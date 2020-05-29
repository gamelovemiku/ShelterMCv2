package com.gamelovemiku.sheltermc.gameplay;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FoodPower implements Listener {

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        ItemStack itemStack = event.getItem();
        Player player = event.getPlayer();
        ShelterMCHelper helper = new ShelterMCHelper();

        //player.sendMessage("EATING ::::::::: " + itemStack.getType());

        switch (itemStack.getType()) {
            case COOKED_RABBIT:
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, helper.secondToTick(3), 3));
                player.sendTitle("", helper.formatInGameColor("&d+ Regeneration III"), 15,35,15);
                break;
            case BEETROOT_SOUP:
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, helper.secondToTick(300), 2));
                player.sendTitle("", helper.formatInGameColor("&d+ Haste II"), 15,35,15);
                break;
            case APPLE:
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, helper.secondToTick(10), 1));
                player.sendTitle("", helper.formatInGameColor("&d+ Strength I"), 15,35,15);
                break;
            case RABBIT_STEW:
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, helper.secondToTick(3), 3));
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, helper.secondToTick(300), 2));
                player.sendTitle("", helper.formatInGameColor("&d+ Regeneration III, Haste II"), 15,35,15);
                break;
            case GOLDEN_APPLE:
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, helper.secondToTick(120), 3));
                player.sendTitle("", helper.formatInGameColor("&d+ Regeneration III"), 15,35,15);
                break;
            case ENCHANTED_GOLDEN_APPLE:
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, helper.secondToTick(600), 3));
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, helper.secondToTick(600), 2));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, helper.secondToTick(480), 2));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, helper.secondToTick(240), 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, helper.secondToTick(120), 1));
                player.sendTitle("", helper.formatInGameColor("&e+&e&lGOLDEN APPLE EFFECT!"), 15,35,15);
                break;
            case SWEET_BERRIES:
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, helper.secondToTick(15), 1));
                player.sendTitle("", helper.formatInGameColor("&d+ Night Vision I"), 15,35,15);
                break;
        }
    }

}
