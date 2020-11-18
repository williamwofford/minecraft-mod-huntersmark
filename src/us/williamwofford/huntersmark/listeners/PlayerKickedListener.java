package us.williamwofford.huntersmark.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import java.util.UUID;

public class PlayerKickedListener implements Listener {

    @EventHandler
    public void onPlayerKicked( PlayerKickEvent e ) {
        final Player player = e.getPlayer();
        final UUID uuid = player.getUniqueId();

        // Update the last known location in given dimension.
    }
}
