package de.bergwerklabs.framework.bedrock.api.session;

import de.bergwerklabs.framework.bedrock.api.LabsGame;
import de.bergwerklabs.framework.bedrock.api.PlayerdataDao;
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
     * Gets the Dao for Playerdata.
     */
    PlayerdataDao getPlayerdataDao();

    /**
     * Gets the game associated with this session.
     */
    LabsGame getGame();

    /**
     * Gets the sessions id.
     */
    String getId();

    /**
     * Sets the {@link PlayerdataDao} for this session.
     */
    void setPlayerdataDao(PlayerdataDao dao);


    /**
     * Gets called when the session is initialized. In this method every critical work should be done.
     */
    void prepare();
}
