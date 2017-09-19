package de.bergwerklabs.framework.gameservice.listener;

import de.bergwerklabs.framework.gameservice.LabsPlayer;
import de.bergwerklabs.framework.gameservice.PlayerManager;
import de.bergwerklabs.framework.gameservice.config.GameServiceConfig;
import org.bukkit.event.Listener;

/**
 * Created by Yannic Rieger on 07.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class LabsListener<T extends LabsPlayer> implements Listener {

    protected PlayerManager<T> playerManager;
    protected GameServiceConfig config;

    /**
     * @param playerManager
     */
    public LabsListener(PlayerManager<T> playerManager, GameServiceConfig config) {
        this.playerManager = playerManager;
        this.config = config;

    }
}
