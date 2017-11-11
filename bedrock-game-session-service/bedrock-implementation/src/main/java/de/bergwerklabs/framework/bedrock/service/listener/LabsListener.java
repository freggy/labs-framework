package de.bergwerklabs.framework.bedrock.service.listener;

import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.service.config.GameServiceConfig;
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
