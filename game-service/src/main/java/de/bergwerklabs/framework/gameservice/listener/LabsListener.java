package de.bergwerklabs.framework.gameservice.listener;

import de.bergwerklabs.framework.gameservice.api.LabsPlayer;
import de.bergwerklabs.framework.gameservice.PlayerRegistry;
import de.bergwerklabs.framework.gameservice.config.GameServiceConfig;
import org.bukkit.event.Listener;

/**
 * Created by Yannic Rieger on 07.07.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class LabsListener<T extends LabsPlayer> implements Listener {

    protected PlayerRegistry<T> playerRegistry;
    protected GameServiceConfig config;

    /**
     * @param playerRegistry
     */
    public LabsListener(PlayerRegistry<T> playerRegistry, GameServiceConfig config) {
        this.playerRegistry = playerRegistry;
        this.config = config;

    }
}
