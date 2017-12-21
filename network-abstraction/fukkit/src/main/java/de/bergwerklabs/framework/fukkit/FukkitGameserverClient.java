package de.bergwerklabs.framework.fukkit;

import de.bergwerklabs.framework.fukkit.gamestate.GamestateManager;

/**
 * Created by Yannic Rieger on 21.12.2017.
 * <p>
 * Abstraction of a game server client. This interface provides methods for interacting
 * with the backend system specific to game servers.
 *
 * @author Yannic Rieger
 */
public interface FukkitGameserverClient extends FukkitClient {

    /**
     * Gets the {@link GamestateManager}.
     */
    GamestateManager getGamestateManager();
}
