package us.williamwofford.huntersmark.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.williamwofford.huntersmark.HuntersMark;

public class CommandTarget implements CommandExecutor {

    @Override
    public boolean onCommand( CommandSender commandSender, Command command, String s, String[] strings ) {
        if ( commandSender instanceof Player ) {
            final Player hunter = (Player) commandSender;

            if ( HuntersMark.hunterManager.isPlayerHunter( hunter ) ) {
                final Player target = Bukkit.getServer().getPlayer( strings[0] );

                if ( target != null ) {
                    HuntersMark.hunterManager.setHunterTarget( hunter, target );

                    return true;
                }
            }
        }
        return false;
    }
}
