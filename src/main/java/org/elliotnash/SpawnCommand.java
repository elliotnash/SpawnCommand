package org.elliotnash;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpawnCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length==0){
            spawnSelf(sender);
            return true;
        } else if (args.length==1){
            spawnOther(sender, args[0]);
            return true;
        } else {
            return false;
        }
    }
    void spawnSelf(CommandSender sender){
        if (sender instanceof Player){
            Player player = (Player) sender;
            player.teleport(player.getWorld().getSpawnLocation());
            sender.sendMessage(ChatColor.AQUA+"You have been sent to spawn");
        } else {
            sender.sendMessage(ChatColor.RED+"This command must be run by a player");
        }
    }
    void spawnOther(CommandSender sender, String playerName){

        if (sender.hasPermission("spawn.other")) {
            Player player = Bukkit.getPlayer(playerName);
            if (player!=null){
                player.teleport(player.getWorld().getSpawnLocation());
                sender.sendMessage(ChatColor.RED+playerName+ChatColor.AQUA+" has been sent to spawn");
            } else {
                sender.sendMessage(ChatColor.RED+"This player is offline");
            }
        } else {
            sender.sendMessage(ChatColor.RED+"You don't have permission to execute this command (spawn.other)");
        }
    }
    List<String> BLANK = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender.hasPermission("spawn.other")&&args.length==1)
            return null;
        else
            return BLANK;
    }
}
