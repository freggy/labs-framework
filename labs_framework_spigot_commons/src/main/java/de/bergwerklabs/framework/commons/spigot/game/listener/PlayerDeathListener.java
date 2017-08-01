package de.bergwerklabs.framework.commons.spigot.game.listener;

import de.bergwerklabs.framework.commons.spigot.game.LabsPlayer;
import de.bergwerklabs.framework.commons.spigot.game.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Yannic Rieger on 01.08.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class PlayerDeathListener<T extends LabsPlayer> extends LabsListener<T> {

    /**
     * @param playerManager
     */
    public PlayerDeathListener(PlayerManager<T> playerManager) {
        super(playerManager);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        // TODO: set statistics if enabled.
        // TODO: set spectator when enabled.
    }
}
