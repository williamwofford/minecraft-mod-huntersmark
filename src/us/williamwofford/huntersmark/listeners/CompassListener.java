package us.williamwofford.huntersmark.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import us.williamwofford.huntersmark.HuntersMark;

public class CompassListener implements Listener {

    @EventHandler
    public void onInteract( PlayerInteractEvent e ) {
        final Player player = e.getPlayer();
        final Action action = e.getAction();

        if ( HuntersMark.hunterManager.isPlayerHunter( player ) ) {

            if ( action.equals( Action.RIGHT_CLICK_BLOCK ) || action.equals( Action.RIGHT_CLICK_AIR ) ) {

                if ( HuntersMark.ITEM_HUNTER_COMPASS.isSimilar( player.getInventory().getItemInMainHand() ) ) {
                    HuntersMark.hunterManager.refreshHunterCompass( player );
                }
            }
        }
    }
}
