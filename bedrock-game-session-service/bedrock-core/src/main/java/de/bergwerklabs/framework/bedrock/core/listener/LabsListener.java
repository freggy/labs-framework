package de.bergwerklabs.framework.bedrock.core.listener;

import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.api.PlayerdataDao;
import de.bergwerklabs.framework.bedrock.core.config.SessionServiceConfig;
import org.bukkit.event.Listener;

/**
 * Created by Yannic Rieger on 07.07.2017.
 * <p>
 * Base class for listeners in the Bedrock Session Service plugin.
 * This class provides some extras like the {@link PlayerRegistry} and the {@link SessionServiceConfig} of the session.
 *
 * @author Yannic Rieger
 */
class LabsListener implements Listener {

    protected PlayerRegistry playerRegistry;
    protected SessionServiceConfig config;
    protected PlayerdataDao dao;

    /**
     * @param playerRegistry registry where all players ares registered.
     * @param config         config of the current session.
     */
    LabsListener(PlayerRegistry playerRegistry, PlayerdataDao dao, SessionServiceConfig config) {
        this.playerRegistry = playerRegistry;
        this.dao = dao;
        this.config = config;
    }
}
