package de.bergwerklabs.framework.bedrock.api.event.lobby;

import de.bergwerklabs.framework.bedrock.api.session.GameSession;
import de.bergwerklabs.framework.bedrock.api.lobby.AbstractLobby;

/**
 * Created by Yannic Rieger on 21.09.2017.
 * <p>
 * Event gets fired when the waiting phase of an {@link AbstractLobby} starts.
 *
 * @author Yannic Rieger
 */
public class LobbyWaitingPhaseStartEvent extends AbstractLobbyEvent {

    /**
     * @param session {@link GameSession} where the event gets fired.
     */
    public LobbyWaitingPhaseStartEvent(GameSession session) {
        super(session);
    }
}
