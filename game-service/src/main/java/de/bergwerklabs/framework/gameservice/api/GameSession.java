package de.bergwerklabs.framework.gameservice.api;

/**
 * Created by Yannic Rieger on 11.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public interface GameSession {

    LabsGame getGame();

    String getId();
}
