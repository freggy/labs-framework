package de.bergwerklabs.framework.bedrock.api.event.session;

import de.bergwerklabs.framework.bedrock.api.session.GameSession;

/**
 * Created by Yannic Rieger on 11.11.2017.
 * <p>
 * Event gets called when the preparation is done by a session.
 *
 * @author Yannic Rieger
 */
public class SessionDonePreparationEvent extends AbstractSessionEvent {

    /**
     * @param session {@link GameSession} associated with this event.
     */
    public SessionDonePreparationEvent(GameSession session) {
        super(session);
    }
}
