package de.bergwerklabs.framework.bedrock.api.session;

import de.bergwerklabs.framework.bedrock.api.LabsGame;
import de.bergwerklabs.framework.bedrock.api.lobby.AbstractLobby;

/**
 * Created by Yannic Rieger on 11.11.2017.
 * <p>
 * Interface representing a game session.
 *
 * @author Yannic Rieger
 */
public interface GameSession {

    /**
     * Gets the game associated with this session.
     */
    LabsGame getGame();

    /**
     * Gets the sessions id.
     */
    String getId();

    /**
     * Gets called when the session is initialized. In this method every critical work should be done.
     */
    void prepare();
}
