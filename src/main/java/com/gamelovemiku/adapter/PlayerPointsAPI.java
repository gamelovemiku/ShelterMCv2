package com.gamelovemiku.adapter;

import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class PlayerPointsAPI {
    private static PlayerPoints pp;
    private PlayerPoints playerPoints;

    public boolean hookPlayerPoints() {
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("PlayerPoints");
        playerPoints = PlayerPoints.class.cast(plugin);
        return playerPoints != null;
    }

    public PlayerPoints getPlayerPoints() {
        return playerPoints;
    }
}
