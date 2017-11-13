package de.bergwerklabs.framework.bedrock.service.listener;

import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.service.config.SessionServiceConfig;
import org.bukkit.event.Listener;

/**
 * Created by Yannic Rieger on 07.07.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class LabsListener implements Listener {

    protected PlayerRegistry playerRegistry;
    protected SessionServiceConfig config;

    /**
     * @param playerRegistry
     */
    public LabsListener(PlayerRegistry playerRegistry, SessionServiceConfig config) {
        this.playerRegistry = playerRegistry;
        this.config = config;

    }
}
