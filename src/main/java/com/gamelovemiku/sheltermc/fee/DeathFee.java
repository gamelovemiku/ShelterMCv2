package com.gamelovemiku.sheltermc.fee;

import org.bukkit.entity.Player;

public class DeathFee extends Fee implements FeeInterface{

	public DeathFee(Player player, double amount) {
		super(player, amount);
	}
	
}
