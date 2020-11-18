package us.williamwofford.huntersmark;

import org.bukkit.Location;
import org.bukkit.World;

public class LocationMemory {
    private Location overworld;
    private Location nether;
    private Location end;

    public LocationMemory( Location overworld, Location nether, Location end ) {
        this.overworld = overworld;
        this.nether = nether;
        this.end = end;
    }
    public LocationMemory() {
        this( null, null, null );
    }

    public Location getLocation( World.Environment dimension ) {
        switch ( dimension ) {
            default:
                return overworld;
            case NETHER:
                return nether;
            case THE_END:
                return end;
        }
    }

    public void setLocation( World.Environment dimension, Location location ) {
        switch ( dimension ) {
            default:
                overworld = location;
                break;
            case NETHER:
                nether = location;
                break;
            case THE_END:
                end = location;
                break;
        }
    }
}
