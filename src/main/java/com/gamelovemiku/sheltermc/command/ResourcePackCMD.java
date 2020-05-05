package com.gamelovemiku.sheltermc.command;

import com.gamelovemiku.sheltermc.tasks.PermissionTimeOutCountdownTask;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ResourcePackCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        //downloader rsp cyantopinkz https://.............
        if (sender instanceof ConsoleCommandSender) {
            if(args[0].equalsIgnoreCase("rsp")) {
                if(args[1] != null) {
                    Player player = Bukkit.getPlayer(args[1]);
                    player.setResourcePack(args[2]);
                    sender.sendMessage("Set! resorcepack.. OK");
                    return true;
                }
            }
        }
        return false;
    }
}
