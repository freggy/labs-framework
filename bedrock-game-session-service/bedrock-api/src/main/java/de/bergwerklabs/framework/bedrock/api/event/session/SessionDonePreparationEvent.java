package de.bergwerklabs.framework.bedrock.api.event.session;

import de.bergwerklabs.framework.bedrock.api.GameSession;

/**
 * Created by Yannic Rieger on 11.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class SessionDonePreparationEvent extends AbstractSessionEvent {

    public SessionDonePreparationEvent(GameSession session) {
        super(session);
    }
}
