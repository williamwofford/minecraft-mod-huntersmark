package us.williamwofford.huntersmark;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;

public class HunterManager {
    private final Map< UUID, UUID > playerRelations = new HashMap<>();
    private final Map< UUID, LocationMemory > playerLocations = new HashMap<>();

    public Map< UUID, UUID > getPlayerRelations() {
        return playerRelations;
    }

    public Map< UUID, LocationMemory > getPlayerLocations() {
        return playerLocations;
    }

    public Set< Player > getOnlineHunters() {
        final Set< Player > onlinePlayers = new HashSet<>();
        for ( Player player : Bukkit.getServer().getOnlinePlayers() ) {
            if ( playerRelations.containsKey( player.getUniqueId() ) )
                onlinePlayers.add( player );
        }

        return onlinePlayers;
    }

    public boolean isPlayerHunter( Player player ) {
        return playerRelations.containsKey( player.getUniqueId() );
    }

    public Location getCompassPointLocation( Player hunter, Player target ) throws Exception {
        return target.getLocation();
    }

    public void addHunter( Player player ) {
        if ( !isPlayerHunter( player ) ) {
            playerRelations.put( player.getUniqueId(), null );
            Bukkit.broadcastMessage( player.getDisplayName() + " is now a Hunter" );
        }
    }

    public void removeHunter( Player hunter ) {
        if ( isPlayerHunter( hunter ) ) {
            playerRelations.remove( hunter.getUniqueId() );
            Bukkit.broadcastMessage( hunter.getDisplayName() + " is no longer a Hunter" );
        }
    }

    public void setHunterTarget( Player hunter, Player target ) {
        playerRelations.put( hunter.getUniqueId(), target.getUniqueId() );
        Bukkit.broadcastMessage( hunter.getDisplayName() + " is now targeting " + target.getDisplayName() );

        refreshHunterCompass( hunter );
    }

    public void clearHunterTarget( Player hunter ) {
        playerRelations.put( hunter.getUniqueId(), null );
    }

    public void refreshHunterCompass( Player hunter ) {

    }

    public void setPlayerDimension( Player player, World.Environment dimension, Location location ) {

    }
}
