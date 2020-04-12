package com.gamelovemiku.sheltermc.fee;

import org.bukkit.entity.Player;

public interface FeeInterface {
	public Player getPlayer();
	public void setPlayer(Player player);
	public double getAmount();
	public void setAmount(double amount);
	public boolean takeBalance();
}
