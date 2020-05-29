package com.gamelovemiku.sheltermc.global;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import com.gamelovemiku.sheltermc.tasks.AlertOnCloseRoofTask;
import com.sk89q.worldguard.bukkit.event.block.BreakBlockEvent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitTask;

public class Natural implements Listener {

	private Plugin plugin;
	private boolean running = false;
	private BukkitTask task;

	public Natural(Plugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onTopBedrock(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		ShelterMCHelper helper = new ShelterMCHelper();

		if(player.getWorld().equals(Bukkit.getWorld("world_shelter"))) {
			if(player.getLocation().getY() >= 160) {
				if (!running) {
					running = true;
					task = new AlertOnCloseRoofTask(this.plugin, player).runTaskTimer(plugin, 0, 100);
				}
			}

			if(player.getLocation().getY() < 160 && running) {
				reset(event.getPlayer());

				for (PotionEffect effect : player.getActivePotionEffects()) {
					player.removePotionEffect(effect.getType());
				}

				player.sendTitle(helper.formatInGameColor("&a&lPewww!"), helper.formatInGameColor("&fคุณปลอดภัยแล้ว!"), 10, 35, 10);
			}


		}
	}
	
	@EventHandler
	public void onBottomBedrock(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		ShelterMCHelper helper = new ShelterMCHelper();
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		ShelterMCHelper helper = new ShelterMCHelper();

		if(player.getWorld().equals(Bukkit.getWorld("world_shelter"))) {
			if(player.getLocation().getY() >= 180) {
				Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
					helper.runOnConsole("spawn " + player.getName());
					player.sendMessage("");
					player.sendMessage(helper.formatInGameColor("&d&lSAFETY LOGIN: &eอ้าวเห้ยยย! ทำไมมาเกิดตรงนี้ละ"));
					player.sendMessage(helper.formatInGameColor("&fครั้งที่แล้วคุณได้ตัดการเชื่อมต่อจากเซิร์ฟเวอร์ไป และเมื่อเชื่อมต่อเข้ามาอีกครั้ง"));
					player.sendMessage(helper.formatInGameColor("&fระบบพบว่าคุณได้อยู่ในพื้นที่อันตราย อาจส่งผลกระทบกับการเข้าสู่ระบบได้"));
					player.sendMessage("");
					reset(player);
				}, helper.secondToTick(3));
			}
		}
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		if(running) {
			reset(event.getPlayer());
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		if(event.getEntity() instanceof Player) {
			reset(event.getEntity());
		}
	}

	public void reset(Player player) {
		try {
			running = false;
			task.cancel();
			player.getActivePotionEffects().clear();

		} catch(NullPointerException error) {
			//do nothing
		}
	}

	@EventHandler
	public void onComposterFull(PlayerInteractEvent event) {
		try {
			Block block = event.getClickedBlock();
			if(block.getType().equals(Material.COMPOSTER) && block.getWorld().equals(Bukkit.getWorld("world_shelter"))) {
				Levelled level = (Levelled) block.getBlockData();
				if(level.getLevel() == 8) {
					level.setLevel(0);
					block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.PODZOL, new ShelterMCHelper().randomNumber(3)));
					block.getWorld().spawnParticle(Particle.SNEEZE, block.getLocation().add(0, 1, 0), 10);
				}
			}
		} catch (NullPointerException error) {
			//do nothing
		}
	}

	public int secondToTick(int second) {
		return 20 * second;
	}
	
}
