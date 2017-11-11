package de.bergwerklabs.framework.gameservice.api.event;

import de.bergwerklabs.framework.commons.spigot.general.LabsEvent;
import de.bergwerklabs.framework.gameservice.api.GameSession;

/**
 * Created by Yannic Rieger on 11.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class SessionInitializedEvent extends LabsEvent {

    public GameSession getSession() {
        return session;
    }

    private GameSession session;

    public SessionInitializedEvent(GameSession session) {
        this.session = session;
    }
}
