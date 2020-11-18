package us.williamwofford.huntersmark.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.williamwofford.huntersmark.HuntersMark;

public class CommandHunter implements CommandExecutor {

    @Override
    public boolean onCommand( CommandSender commandSender, Command command, String s, String[] strings ) {
        if ( commandSender instanceof Player ) {
            final Player player = (Player) commandSender;
            final boolean playerIsHunter = HuntersMark.hunterManager.isPlayerHunter( player );

            if ( playerIsHunter )
                HuntersMark.hunterManager.removeHunter( player );
            else
                HuntersMark.hunterManager.addHunter( player );

            return true;
        }
        return false;
    }
}
