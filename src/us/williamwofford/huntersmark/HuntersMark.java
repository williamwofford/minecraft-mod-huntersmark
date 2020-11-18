package us.williamwofford.huntersmark;

import org.bukkit.plugin.java.JavaPlugin;
import us.williamwofford.huntersmark.commands.CommandHunter;
import us.williamwofford.huntersmark.commands.CommandHunterList;
import us.williamwofford.huntersmark.commands.CommandTarget;
import us.williamwofford.huntersmark.listeners.CompassListener;
import us.williamwofford.huntersmark.listeners.DimensionListener;
import us.williamwofford.huntersmark.listeners.PlayerJoinedListener;

public class HuntersMark extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents( new PlayerJoinedListener(), this );
        getServer().getPluginManager().registerEvents( new DimensionListener(), this );
        getServer().getPluginManager().registerEvents( new CompassListener(), this );

        this.getCommand( "listhunters" ).setExecutor( new CommandHunterList() );
        this.getCommand( "hunter" ).setExecutor( new CommandHunter() );
        this.getCommand( "target" ).setExecutor( new CommandTarget() );
    }

    @Override
    public void onDisable() {

    }
}
