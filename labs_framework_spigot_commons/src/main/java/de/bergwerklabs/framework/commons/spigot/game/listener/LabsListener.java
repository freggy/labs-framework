package de.bergwerklabs.framework.commons.spigot.game.listener;

import de.bergwerklabs.framework.commons.spigot.game.LabsPlayer;
import de.bergwerklabs.framework.commons.spigot.game.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Yannic Rieger on 07.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class LabsListener<T extends LabsPlayer> implements Listener {

    protected T player;
    private PlayerManager<T> playerManager;

    /**
     * @param playerManager
     */
    public LabsListener(PlayerManager<T> playerManager) {
        this.playerManager = playerManager;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    protected void onPlayerLogin(PlayerLoginEvent e) {
        this.player = this.playerManager.getPlayers().get(e.getPlayer().getUniqueId());
        // TODO: handle stats
    }

    @EventHandler(priority = EventPriority.MONITOR)
    protected void onPlayerDisconnect(PlayerQuitEvent e) {
        // TODO: handle stats
    }
}
