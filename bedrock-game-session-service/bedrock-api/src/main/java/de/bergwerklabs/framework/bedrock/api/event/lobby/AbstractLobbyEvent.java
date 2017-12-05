package de.bergwerklabs.framework.bedrock.api.event.lobby;

import de.bergwerklabs.framework.bedrock.api.session.GameSession;
import de.bergwerklabs.framework.bedrock.api.lobby.AbstractLobby;
import de.bergwerklabs.framework.commons.spigot.general.LabsEvent;

/**
 * Created by Yannic Rieger on 21.09.2017.
 * <p>
 * Base class for lobby events.
 *
 * @author Yannic Rieger
 */
public class AbstractLobbyEvent extends LabsEvent {

    /**
     * Gets the {@link GameSession} associated with this event.
     */
    public GameSession getSession() {
        return session;
    }

    private GameSession session;

    /**
     * @param session {@link AbstractLobby} where the event gets fired.
     */
    AbstractLobbyEvent(GameSession session) {
        this.session = session;
    }
}
