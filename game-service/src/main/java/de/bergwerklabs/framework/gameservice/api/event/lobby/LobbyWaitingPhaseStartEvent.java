package de.bergwerklabs.framework.gameservice.api.event.lobby;

import de.bergwerklabs.framework.gameservice.lobby.AbstractLobby;

/**
 * Created by Yannic Rieger on 21.09.2017.
 * <p>
 * Event gets fired when the waiting phase of an {@link AbstractLobby} starts.
 *
 * @author Yannic Rieger
 */
public class LobbyWaitingPhaseStartEvent extends AbstractLobbyEvent {

    /**
     * @param lobby {@link AbstractLobby} where the event gets fired.
     */
    public LobbyWaitingPhaseStartEvent(AbstractLobby lobby) {
        super(lobby);
    }
}
