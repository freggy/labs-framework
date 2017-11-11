package de.bergwerklabs.framework.bedrock.api.event.lobby;

import de.bergwerklabs.framework.bedrock.api.GameSession;
import de.bergwerklabs.framework.bedrock.api.lobby.AbstractLobby;

/**
 * Created by Yannic Rieger on 21.09.2017.
 * <p>
 * Event that gets fired when the waiting phase of an {@link AbstractLobby} ends.
 *
 * @author Yannic Rieger
 */
public class LobbyWaitingPhaseStopEvent extends AbstractLobbyEvent {

    /**
     * @param session {@link GameSession} where the event gets fired.
     */
    public LobbyWaitingPhaseStopEvent(GameSession session) {
        super(session);
    }
}
