package com.gamelovemiku.sheltermc.command;

import com.gamelovemiku.sheltermc.ShelterMCHelper;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;

public class CommandAddon implements CommandExecutor, Listener {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

		Song song = NBSDecoder.parse(new File("songs/Papermoon.nbs"));
		RadioSongPlayer rsp = new RadioSongPlayer(song);

        if(rsp.isPlaying()) {
        	rsp.setPlaying(false);
		} else {
			rsp.addPlayer(((Player) sender).getPlayer());
			rsp.getSong().getTitle();
			sender.sendMessage(new ShelterMCHelper().formatInGameColor("&aNow Playing: ") + rsp.getSong().getTitle());
			rsp.setPlaying(true);
		}

		return true;
	}
	
}
