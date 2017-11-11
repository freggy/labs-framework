package de.bergwerklabs.framework.gameservice.api.event.lobby;

import de.bergwerklabs.framework.gameservice.lobby.AbstractLobby;

/**
 * Created by Yannic Rieger on 21.09.2017.
 * <p>
 * Event that gets fired when the waiting phase of an {@link AbstractLobby} ends.
 *
 * @author Yannic Rieger
 */
public class LobbyWaitingPhaseStopEvent extends AbstractLobbyEvent {

    /**
     * @param lobby {@link AbstractLobby} where the event gets fired.
     */
    public LobbyWaitingPhaseStopEvent(AbstractLobby lobby) {
        super(lobby);
    }
}
