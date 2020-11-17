package us.williamwofford.huntersmark;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class HunterManager {
    private final Map< Player, Player > playerRelations = new HashMap<>();
    private final Map< Player, LocationMemory > playerLocations = new HashMap<>();

    public boolean isPlayerHunter( Player player ) {
        return playerRelations.containsKey( player );
    }

    public Location getCompassPointLocation( Player hunter, Player target ) throws UnnavigableError {
        return target.getLocation();
    }

    public void addHunter( Player player ) {

    }

    public void removeHunter( Player player ) {

    }

    public void setHunterTarget( Player hunter, Player target ) {

    }

    public void clearHunterTarget( Player hunter ) {

    }

    public void refreshHunterCompass( Player hunter ) {

    }

    public void setPlayerDimension( Player player, World.Environment dimension, Location location ) {

    }

    private class UnnavigableError extends Exception {
        public UnnavigableError() {
            super();
        }

        public UnnavigableError( String message ) {
            super( message );
        }

        public UnnavigableError( String message, Throwable cause ) {
            super( message, cause );
        }

        public UnnavigableError( Throwable cause ) {
            super( cause );
        }
    }
}
