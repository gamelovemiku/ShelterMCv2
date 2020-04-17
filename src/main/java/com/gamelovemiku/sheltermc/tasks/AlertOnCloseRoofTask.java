package com.gamelovemiku.sheltermc.tasks;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class AlertOnCloseRoofTask extends BukkitRunnable {

    private final Plugin plugin;
    private Player player;

    ShelterMCHelper helper = new ShelterMCHelper();

    public AlertOnCloseRoofTask(Plugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    @Override
    public void run() {
        if (player.getLocation().getY() >= 160) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, helper.secondToTick(150), 2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, helper.secondToTick(150), 2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, helper.secondToTick(8), 3));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, helper.secondToTick(150), 3));

            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10, 1);

            player.sendTitle(helper.formatInGameColor("&c&lDANGER!"), helper.formatInGameColor("&fมีสารเคมีเข้มข้นสูงบริเวณนี้! โปรดหลีกเลี่ยง!"), 10, 35, 10);
        } else if(player.getLocation().getY() <= 30) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, helper.secondToTick(150), 2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, helper.secondToTick(150), 2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, helper.secondToTick(150), 3));

            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10, 1);

            player.sendTitle(helper.formatInGameColor("&r"), helper.formatInGameColor("&c&lDANGER! &fแรงดันใต้โลกสูง!"), 10, 35, 10);
        } else {
            this.cancel();
        }
    }
}