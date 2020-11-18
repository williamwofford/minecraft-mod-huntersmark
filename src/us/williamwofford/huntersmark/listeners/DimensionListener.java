package us.williamwofford.huntersmark.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import us.williamwofford.huntersmark.HuntersMark;

public class DimensionListener implements Listener {

    @EventHandler
    public void onTeleported( PlayerPortalEvent e ) {
        final Player player = e.getPlayer();
        final PlayerTeleportEvent.TeleportCause cause = e.getCause();

        final World.Environment fromDimension = player.getWorld().getEnvironment();
        final World.Environment toDimension;

        final Location fromLocation = e.getFrom();
        final Location toLocation = e.getTo();

        switch ( cause ) {
            default:
                toDimension = player.getWorld().getEnvironment();
                break;
            case NETHER_PORTAL:
                switch ( fromDimension ) {
                    case NORMAL:
                        toDimension = World.Environment.NETHER;
                        break;
                    case NETHER:
                        toDimension = World.Environment.NORMAL;
                        break;
                    default:
                        toDimension = null;
                }
                break;
            case END_PORTAL:
                toDimension = World.Environment.THE_END;
                break;
        }

        HuntersMark.hunterManager.setPlayerDimension( player, fromDimension, fromLocation );
//        HuntersMark.hunterManager.setPlayerDimension( player, toDimension, toLocation );

        Bukkit.broadcastMessage( player.getDisplayName() + " has portaled from " + fromDimension.toString() + " to " + toDimension.toString() );
    }

}
