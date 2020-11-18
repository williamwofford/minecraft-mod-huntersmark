package us.williamwofford.huntersmark;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.plugin.java.JavaPlugin;
import us.williamwofford.huntersmark.commands.CommandHunter;
import us.williamwofford.huntersmark.commands.CommandHunterList;
import us.williamwofford.huntersmark.commands.CommandTrack;
import us.williamwofford.huntersmark.item.ItemBuilder;
import us.williamwofford.huntersmark.listeners.CompassListener;
import us.williamwofford.huntersmark.listeners.DimensionListener;
import us.williamwofford.huntersmark.listeners.PlayerKickedListener;

import java.util.Arrays;

public class HuntersMark extends JavaPlugin {

    public static HunterManager hunterManager = new HunterManager();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents( new PlayerKickedListener(), this );
        getServer().getPluginManager().registerEvents( new DimensionListener(), this );
        getServer().getPluginManager().registerEvents( new CompassListener(), this );

        this.getCommand( "listhunters" ).setExecutor( new CommandHunterList() );
        this.getCommand( "hunter" ).setExecutor( new CommandHunter() );
        this.getCommand( "track" ).setExecutor( new CommandTrack() );
    }

    @Override
    public void onDisable() {

    }

    public static final ItemStack ITEM_HUNTER_COMPASS = new ItemBuilder( Material.COMPASS )
        .setName( ChatColor.DARK_RED + "Hunter's Compass" )
        .toItemStack();
}
