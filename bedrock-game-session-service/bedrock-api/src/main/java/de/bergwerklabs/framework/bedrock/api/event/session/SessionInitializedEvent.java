package de.bergwerklabs.framework.bedrock.api.event.session;

import de.bergwerklabs.framework.bedrock.api.session.GameSession;

/**
 * Created by Yannic Rieger on 11.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class SessionInitializedEvent extends AbstractSessionEvent {

    public SessionInitializedEvent(GameSession session) {
        super(session);
    }
}
