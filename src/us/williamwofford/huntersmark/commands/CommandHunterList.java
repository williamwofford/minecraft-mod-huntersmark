package us.williamwofford.huntersmark.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.williamwofford.huntersmark.HuntersMark;

public class CommandHunterList implements CommandExecutor {

    @Override
    public boolean onCommand( CommandSender commandSender, Command command, String s, String[] strings ) {
        String result;

        if ( HuntersMark.hunterManager.getOnlineHunters().size() > 0) {
            result = "Current Hunters online: ";
            for ( Player player : HuntersMark.hunterManager.getOnlineHunters() ) {
                final ChatColor onlineStatusColor = player.isOnline() ? ChatColor.RESET : ChatColor.DARK_GRAY;
                result += onlineStatusColor + player.getDisplayName() + " ";
            }
        } else {
            result = "There are currently no Hunters online.";
        }

        commandSender.sendMessage( result );

        return true;
    }
}
