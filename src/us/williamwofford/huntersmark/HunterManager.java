package us.williamwofford.huntersmark;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

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

    public Player getHunterTarget( Player hunter ) {
        return Bukkit.getServer().getPlayer( playerRelations.get( hunter.getUniqueId() ) );
    }

    public boolean isPlayerHunter( Player player ) {
        return playerRelations.containsKey( player.getUniqueId() );
    }

    public Location getCompassPointLocation( Player hunter, Player target ) throws TrackingFailureException {

        if ( !target.isOnline() )
            throw new TrackingFailureException( TrackingFailureCause.PLAYER_OFFLINE );

        final World.Environment hunterDimension = hunter.getWorld().getEnvironment();
        final World.Environment targetDimension = target.getWorld().getEnvironment();

        if ( hunterDimension == targetDimension )
            return target.getLocation();
        else {
            if ( !playerLocations.containsKey( target.getUniqueId() ) )
                throw new TrackingFailureException( TrackingFailureCause.NO_DIMENSION_MEMORY );

            final LocationMemory memory = playerLocations.get( target.getUniqueId() );
            final Location location = memory.getLocation( hunterDimension );

            if ( location != null )
                return location;
            else
                throw new TrackingFailureException( TrackingFailureCause.NO_DIMENSION_MEMORY );
        }
    }

    public void addHunter( Player player ) {
        if ( !isPlayerHunter( player ) ) {
            playerRelations.put( player.getUniqueId(), null );

            ItemStack compass = HuntersMark.ITEM_HUNTER_COMPASS;

            player.getInventory().addItem( compass );

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
        if ( !hunter.equals( target ) ) {
            playerRelations.put( hunter.getUniqueId(), target.getUniqueId() );
            Bukkit.broadcastMessage( hunter.getDisplayName() + " is now tracking " + target.getDisplayName() );

            refreshHunterCompass( hunter );
        }
        else {
            hunter.sendMessage( "Cannot track yourself" );
        }
    }

    public void clearHunterTarget( Player hunter ) {
        playerRelations.put( hunter.getUniqueId(), null );
    }

    public void refreshHunterCompass( Player hunter ) {
        final Player target = getHunterTarget( hunter );

        if ( target != null ) {
            try {
                setCompassTarget( hunter, target );
            } catch ( TrackingFailureException e ) {
                String msg = target.getDisplayName() + ChatColor.DARK_RED + " cannot be tracked ";

                switch ( e.cause ) {
                    case PLAYER_OFFLINE:
                        msg += "because they are offline";
                        break;
                    case NO_DIMENSION_MEMORY:
                        msg += "because they have never entered this dimension";
                        break;
                }

                hunter.sendMessage( msg );
            }
        }
        else {
            hunter.sendMessage( "Use \"/track <player>\" to track another player" );
        }
    }

    public void setCompassTarget( Player hunter, Player target ) throws TrackingFailureException {
        final Location trackLocation = getCompassPointLocation( hunter, target );
        hunter.setCompassTarget( trackLocation );

//        for ( ItemStack item : hunter.getInventory().getContents() ) {
//            if ( HuntersMark.ITEM_HUNTER_COMPASS.isSimilar( item ) ) {
//                CompassMeta compassMeta = (CompassMeta) item.getItemMeta();
//
//                compassMeta.setLodestoneTracked( false );
//                compassMeta.setLodestone( trackLocation );
//
//                item.setItemMeta( compassMeta );
//            }
//        }
    }

    public void setPlayerDimension( Player player, World.Environment dimension, Location location ) {
        final UUID uuid = player.getUniqueId();
        final LocationMemory memory;

        if ( playerLocations.get( uuid ) != null )
            memory = playerLocations.get( uuid );
        else
            memory = new LocationMemory();

        memory.setLocation( dimension, location );
        playerLocations.put( uuid, memory );
    }

    private static class TrackingFailureException extends Exception {
        private TrackingFailureCause cause;

        public TrackingFailureException( TrackingFailureCause cause ) {
            super();
            this.cause = cause;
        }
    }

    private enum TrackingFailureCause {
        PLAYER_OFFLINE,
        NO_DIMENSION_MEMORY
    }
}
