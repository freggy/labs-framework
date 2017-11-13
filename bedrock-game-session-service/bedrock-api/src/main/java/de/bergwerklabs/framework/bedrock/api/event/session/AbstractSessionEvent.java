package de.bergwerklabs.framework.bedrock.api.event.session;

import de.bergwerklabs.framework.bedrock.api.GameSession;
import de.bergwerklabs.framework.commons.spigot.general.LabsEvent;

/**
 * Created by Yannic Rieger on 11.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public abstract class AbstractSessionEvent extends LabsEvent {

    public GameSession getSession() {
        return session;
    }

    protected GameSession session;

    public AbstractSessionEvent(GameSession session) {
        this.session = session;
    }
}
