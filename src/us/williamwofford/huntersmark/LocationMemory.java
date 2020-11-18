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

    public Location getLocation( World.Environment dimension ) throws Exception {
        switch ( dimension ) {
            default:
                if ( overworld == null )
                    throw new Exception();
                return overworld;
            case NETHER:
                if ( nether == null )
                    throw new Exception();
                return nether;
            case THE_END:
                if ( end == null )
                    throw new Exception();
                return end;
        }
    }

    public void setLocation( World.Environment dimension, Location location ) {

    }
}
