package com.gamelovemiku.sheltermc.perk;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class NoLimboPerk implements Listener {

    Player player = null;
    ShelterMCHelper helper = new ShelterMCHelper();

    @EventHandler()
    public boolean hasPerk(PlayerRespawnEvent event) {
        int[] change = new int[] {
                100,
                91,
                82,
                74,
                66,
                58,
                45,
                37,
        };

        player = event.getPlayer();

        if(player instanceof Player) {
            if(!player.getWorld().equals(Bukkit.getWorld("world_limbo"))) {
                if(player.hasPermission("sheltermc.perk.nolimbo.lv0")) {
                    for (int i = 7; i >= 0 ; i--) {
                        if(player.hasPermission("sheltermc.perk.nolimbo.lv" + i)) {
                            int num = helper.randomNumber(100);
                            if(num < change[i]) {
                                //event.getPlayer().sendMessage(helper.formatInGameColor("DEBUG (random:" + num + " < " + change[i] + "): #" + i));
                                spawnInLimbo(event);
                                return true;
                            } else {
                                //event.getPlayer().sendMessage(helper.formatInGameColor("DEBUG (random:" + num + " < " + change[i] + "): #" + i));
                                player.sendMessage("");
                                player.sendMessage(helper.formatInGameColor("&a&l✔ Perk Activated &eโกงความตาย"));
                                player.sendMessage(helper.formatInGameColor("&fด้วยความสามารถ &eโกงความตาย " + (100-change[i]) + "% &fทำให้คุณไม่ต้องไป &6&lLimbo &fในครั้งนี้"));
                                player.sendMessage("");
                                player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1,1);
                                event.setRespawnLocation(new Location(Bukkit.getWorld("world"), -65.50, 65, -103.50, 0, 3));
                                return true;
                            }
                        }
                    }
                } else {
                    this.spawnInLimbo(event);
                }
            } else {
                event.getPlayer().sendMessage(helper.formatInGameColor("&c&m----------------------------------------------------"));
                event.getPlayer().sendMessage(helper.formatInGameColor("&cคุณจำต้องทำอะไรบางอย่างเพื่อออกจาก &6&lLIMBO &cลองติดต่อคนแถวนี้ดูนะ"));
                event.getPlayer().sendMessage(helper.formatInGameColor("&cหรือถ้ามี Life พอละก็ ไปเกิดใหม่ที่บ้านหลังใหญ่กลางเกาะ Limbo ได้เลย"));
                event.getPlayer().sendMessage(helper.formatInGameColor("&c&m----------------------------------------------------"));
                this.spawnInLimbo(event);
            }
        }
        return true;
    }

    public void spawnInLimbo(PlayerRespawnEvent event) {
        event.setRespawnLocation(new Location(Bukkit.getWorld("world_limbo"), 59.5, 42, 156.5, 90, 1.5f));
        event.getPlayer().sendMessage(helper.formatInGameColor("&c&lINTO THE DEATH &7You are dead! now you are in Limbo"));
    }
}
