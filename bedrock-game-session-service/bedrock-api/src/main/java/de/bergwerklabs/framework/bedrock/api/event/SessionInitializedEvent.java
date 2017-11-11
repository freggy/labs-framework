package de.bergwerklabs.framework.bedrock.api.event;

import de.bergwerklabs.framework.bedrock.api.GameSession;
import de.bergwerklabs.framework.commons.spigot.general.LabsEvent;

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
