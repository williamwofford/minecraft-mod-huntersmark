package us.williamwofford.huntersmark;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HunterManager {
    private final Map< Player, Player > playerRelations = new HashMap<>();
    private final Map< Player, LocationMemory > playerLocations = new HashMap<>();

    public Map< Player, Player > getPlayerRelations() {
        return playerRelations;
    }

    public Map< Player, LocationMemory > getPlayerLocations() {
        return playerLocations;
    }

    public Set< Player > getAllHunters() {
        return playerRelations.keySet();
    }

    public boolean isPlayerHunter( Player player ) {
        return playerRelations.containsKey( player );
    }

    public Location getCompassPointLocation( Player hunter, Player target ) throws Exception {
        return target.getLocation();
    }

    public void addHunter( Player player ) {
        if ( !isPlayerHunter( player ) ) {
            playerRelations.put( player, null );
            Bukkit.broadcastMessage( player.getDisplayName() + " is now a Hunter" );
        }
    }

    public void removeHunter( Player hunter ) {
        if ( isPlayerHunter( hunter ) ) {
            playerRelations.remove( hunter );
            Bukkit.broadcastMessage( hunter.getDisplayName() + " is no longer a Hunter" );
        }
    }

    public void setHunterTarget( Player hunter, Player target ) {
        playerRelations.put( hunter, target );
        Bukkit.broadcastMessage( hunter.getDisplayName() + " is now targeting " + target.getDisplayName() );

        refreshHunterCompass( hunter );
    }

    public void clearHunterTarget( Player hunter ) {
        playerRelations.put( hunter, null );
    }

    public void refreshHunterCompass( Player hunter ) {

    }

    public void setPlayerDimension( Player player, World.Environment dimension, Location location ) {

    }
}
