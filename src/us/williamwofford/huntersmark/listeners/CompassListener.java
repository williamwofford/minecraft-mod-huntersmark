package us.williamwofford.huntersmark.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import us.williamwofford.huntersmark.HuntersMark;

public class CompassListener implements Listener {

    @EventHandler
    public void onInteract( PlayerInteractEvent e ) {
        Player player = e.getPlayer();
        Action action = e.getAction();

        if ( HuntersMark.hunterManager.isPlayerHunter( player ) ) {

            if ( action.equals( Action.RIGHT_CLICK_BLOCK ) || action.equals( Action.RIGHT_CLICK_AIR ) ) {

                if ( player.getInventory().getItemInMainHand().getType() == Material.COMPASS ) {
                    HuntersMark.hunterManager.refreshHunterCompass( player );
                }
            }
        }
    }
}
