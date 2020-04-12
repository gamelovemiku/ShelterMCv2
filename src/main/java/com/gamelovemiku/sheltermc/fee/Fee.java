package com.gamelovemiku.sheltermc.fee;

import org.bukkit.entity.Player;

import com.gamelovemiku.adapter.Vault;

public class Fee implements FeeInterface{

	private Player player;
	private double amount = 0;
	
	public Fee() {

	}
	
	public Fee(Player player, double amount) {
		this.player = player;
		this.amount = amount;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public boolean takeBalance() {
		double balance = Vault.getEconomy().getBalance(this.player);
		
		if(balance >= amount) {
			Vault.getEconomy().withdrawPlayer(this.player, amount);
			return true;
		}
		
		return false;
	}

	public boolean takeBalance(int cost) {
		double balance = Vault.getEconomy().getBalance(this.player);

		if(balance >= cost) {
			Vault.getEconomy().withdrawPlayer(this.player, cost);
			return true;
		}

		return false;
	}
	
	public void sendCustomMessage(String msg) {
		player.sendMessage(msg.replace("&", "§"));
	}

}
