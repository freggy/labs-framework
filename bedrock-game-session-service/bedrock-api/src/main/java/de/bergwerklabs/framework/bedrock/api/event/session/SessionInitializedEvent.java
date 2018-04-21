package de.bergwerklabs.framework.bedrock.api.event.session;

import de.bergwerklabs.framework.bedrock.api.session.GameSession;

/**
 * Created by Yannic Rieger on 11.11.2017.
 *
 * <p>Gets called when a session has finished initialization.
 *
 * @author Yannic Rieger
 */
public class SessionInitializedEvent extends AbstractSessionEvent {

  /** @param session {@link GameSession} associated with this event. */
  public SessionInitializedEvent(GameSession session) {
    super(session);
  }
}
