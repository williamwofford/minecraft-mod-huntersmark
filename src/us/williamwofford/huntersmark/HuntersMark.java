package us.williamwofford.huntersmark;

import org.bukkit.plugin.java.JavaPlugin;
import us.williamwofford.huntersmark.listeners.CompassListener;
import us.williamwofford.huntersmark.listeners.DimensionListener;
import us.williamwofford.huntersmark.listeners.PlayerJoinedListener;

public class HuntersMark extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents( new PlayerJoinedListener(), this );
        getServer().getPluginManager().registerEvents( new DimensionListener(), this );
        getServer().getPluginManager().registerEvents( new CompassListener(), this );

        
    }

    @Override
    public void onDisable() {

    }
}
