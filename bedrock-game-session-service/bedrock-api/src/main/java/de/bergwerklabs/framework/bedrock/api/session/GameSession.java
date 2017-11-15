package de.bergwerklabs.framework.bedrock.api.session;

import de.bergwerklabs.framework.bedrock.api.LabsGame;
import de.bergwerklabs.framework.bedrock.api.lobby.AbstractLobby;

/**
 * Created by Yannic Rieger on 11.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public interface GameSession {

    LabsGame getGame();

    String getId();

    void prepare();
}
