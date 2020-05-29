package com.gamelovemiku.sheltermc.gameplay;

import com.gamelovemiku.adapter.SkinRestorer;
import com.gamelovemiku.sheltermc.ShelterMCHelper;
import com.gamelovemiku.sheltermc.fee.DeathFee;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerGameplay implements Listener {

    private Player player;
    private ShelterMCHelper h = new ShelterMCHelper();

    public PlayerGameplay() {

    }

    public PlayerGameplay(Player player) {
        this.player = player;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        player = (Player) event.getEntity();
        DeathFee fee = new DeathFee(player, 8);

        if(player.hasPermission("sheltermc.deathfee.5")) {
            fee.setAmount(5.5);
        }

        if(fee.takeBalance()) {
            event.setKeepLevel(true);
            event.setKeepInventory(true);
            event.getDrops().clear();
            fee.getPlayer().sendMessage(h.formatInGameColor("&6$ &8[&9Fee&8] &7ค่าธรรมเนียมการตาย &9" + fee.getAmount() + " Cubix &7ถูกหักออกจากบัญชีของคุณแล้ว!"));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        player = event.getPlayer();

        if(player.hasPermission("sheltermc.addheart.normal")) {
            player.setHealthScale(20.0);
        }

        if (player.hasPermission("sheltermc.addheart.2")) {
            player.setHealthScale(24.0);
        }

        if(player.hasPermission("sheltermc.addheart.3")) {
            player.setHealthScale(26.0);
        }

        if(player.hasPermission("sheltermc.addheart.4")) {
            player.setHealthScale(28.0);
        }

    }

    @EventHandler
    public void onBed(PlayerBedEnterEvent event) {
        event.setCancelled(true);
        player = event.getPlayer();
        if (player.getWorld().getTime() > 14000) {
            sendCustomMessage("&a&lDREAMLAND &aยังไม่พร้อมให้เล่นในช่วงนี้");
        } else {
            sendCustomMessage("&cDREAMLAND กำลังอยู่ในช่วงพัฒนา");
        }
    }

    @EventHandler
    public void changeSkin(PlayerChangedWorldEvent event) {
        World world = event.getFrom();
        Player player = event.getPlayer();

        if (player.getWorld() == Bukkit.getWorld("world_limbo")) { //Out from limbo
            //Bukkit.broadcastMessage("DEBUG: You come from -> " + world.getName());
            setSkin(player, "Skull");
        }

        if(event.getFrom() == Bukkit.getWorld("world_limbo")){
            //Bukkit.broadcastMessage("DEBUG: You come from -> " + world.getName());
            //Bukkit.broadcastMessage("DEBUG: Removed skin of -> " + player.getName());
            Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "skin clear " + player.getName().toString());
        }
    }

    public void setSkin(Player player, String skinname) {
        SkinRestorer.api().setSkinName(player.getName(), skinname);
        SkinRestorer.api().applySkin(player);
    }

    public void sendCustomMessage(String msg) {
        player.sendMessage(msg.replace("&", "§"));
    }

}
