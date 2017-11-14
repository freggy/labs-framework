package de.bergwerklabs.framework.bedrock.api;

import de.bergwerklabs.framework.bedrock.api.lobby.AbstractLobby;

/**
 * Created by Yannic Rieger on 11.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public interface GameSession {

    LabsGame getGame();

    AbstractLobby getLobby();

    String getId();

    void prepare();
}
