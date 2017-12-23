package de.bergwerklabs.framework.nabs.bukkit;

import de.bergwerklabs.framework.nabs.bukkit.gamestate.GamestateManager;

/**
 * Created by Yannic Rieger on 21.12.2017.
 * <p>
 * Abstraction of a game server client. This interface provides methods for interacting
 * with the backend system specific to game servers.
 *
 * @author Yannic Rieger
 */
public interface BukkitGameserverClient extends BukkitClient {

    /**
     * Gets the {@link GamestateManager}.
     */
    GamestateManager getGamestateManager();
}
