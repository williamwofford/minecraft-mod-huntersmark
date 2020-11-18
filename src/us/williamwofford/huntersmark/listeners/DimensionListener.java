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
        final Location fromLocation = e.getFrom();

        HuntersMark.hunterManager.setPlayerDimension( player, fromDimension, fromLocation );
    }
}
