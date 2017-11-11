package de.bergwerklabs.framework.gameservice.listener;

import de.bergwerklabs.framework.gameservice.api.LabsPlayer;
import de.bergwerklabs.framework.gameservice.PlayerRegistry;
import de.bergwerklabs.framework.gameservice.config.GameServiceConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Yannic Rieger on 01.08.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class PlayerQuitListener<T extends LabsPlayer> extends LabsListener<T> {

    /**
     * @param playerRegistry
     */
    public PlayerQuitListener(PlayerRegistry<T> playerRegistry, GameServiceConfig config) {
        super(playerRegistry, config);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        // TODO: load statistics.
    }
}
