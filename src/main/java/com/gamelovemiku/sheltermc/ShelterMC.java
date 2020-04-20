package com.gamelovemiku.sheltermc;

import com.gamelovemiku.adapter.Vault;
import com.gamelovemiku.sheltermc.command.CommandAddon;
import com.gamelovemiku.sheltermc.global.Mining;
import com.gamelovemiku.sheltermc.global.Natural;
import com.gamelovemiku.sheltermc.gameplay.PlayerGameplay;
import com.gamelovemiku.sheltermc.perk.*;
import com.gamelovemiku.sheltermc.perk.extras.PlanterPerk;
import com.gamelovemiku.sheltermc.worldpatch.AscotCityWorldPatch;
import com.gamelovemiku.sheltermc.worldpatch.LimboWorldPatch;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ShelterMC extends JavaPlugin {

    private Player player;
    private ShelterMCHelper helper = new ShelterMCHelper();

    @Override
    public void onEnable() {
        helper.logMessage(ChatColor.LIGHT_PURPLE + "[ShelterMCv2]" + ChatColor.YELLOW + " Patching now..");
        helper.logMessage(ChatColor.LIGHT_PURPLE + "[ShelterMCv2]" + ChatColor.YELLOW + " Reserved to ShelterMC | Developed by gamelovemiku");
        helper.logMessage(ChatColor.LIGHT_PURPLE + "[ShelterMCv2]" + ChatColor.YELLOW + " Address: play.sheltermc.net");

        Vault.setupEconomy();

        Bukkit.getServer().getPluginManager().registerEvents(new Mining(), (Plugin)this);
        Bukkit.getServer().getPluginManager().registerEvents(new Natural(this), (Plugin)this);

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerGameplay(), (Plugin)this);

        Bukkit.getServer().getPluginManager().registerEvents(new SwordPerk(), (Plugin)this);
        Bukkit.getServer().getPluginManager().registerEvents(new CropMasterPerk(), (Plugin)this);
        Bukkit.getServer().getPluginManager().registerEvents(new NoLimboPerk(), (Plugin)this);
        Bukkit.getServer().getPluginManager().registerEvents(new FlowerPowerPerk(), (Plugin)this);
        Bukkit.getServer().getPluginManager().registerEvents(new AnimalsGodPerk(), (Plugin)this);

        Bukkit.getServer().getPluginManager().registerEvents(new PlanterPerk(), (Plugin)this);

        Bukkit.getServer().getPluginManager().registerEvents(new AscotCityWorldPatch(this), (Plugin)this);
        Bukkit.getServer().getPluginManager().registerEvents(new LimboWorldPatch(this), (Plugin)this);

        this.getCommand("testpl").setExecutor(new CommandAddon());
        this.getCommand("ascotcity").setExecutor(new AscotCityWorldPatch(this));
        this.getCommand("limbo").setExecutor(new LimboWorldPatch(this));
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getLogger().info(ChatColor.LIGHT_PURPLE + "[ShelterMC]" + ChatColor.RED + " Stopped!");
    }

    @EventHandler
    public void setPlayer(PlayerJoinEvent player) {
        this.player = player.getPlayer();
    }

    public void sendMessageToPlayer(String message) {
        player.sendMessage(message);
    }
}
