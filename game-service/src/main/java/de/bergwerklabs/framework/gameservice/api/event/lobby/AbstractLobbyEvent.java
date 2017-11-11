package de.bergwerklabs.framework.gameservice.api.event.lobby;

import de.bergwerklabs.framework.commons.spigot.general.LabsEvent;
import de.bergwerklabs.framework.gameservice.lobby.AbstractLobby;

/**
 * Created by Yannic Rieger on 21.09.2017.
 * <p>
 * Base class for lobby events.
 *
 * @author Yannic Rieger
 */
public class AbstractLobbyEvent extends LabsEvent {

    /**
     * Gets the {@link AbstractLobby} associated with this event.
     */
    public AbstractLobby getLobby() {
        return lobby;
    }

    private AbstractLobby lobby;

    /**
     * @param lobby {@link AbstractLobby} where the event gets fired.
     */
    AbstractLobbyEvent(AbstractLobby lobby) {
        this.lobby = lobby;
    }
}
