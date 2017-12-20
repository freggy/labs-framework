package de.bergwerklabs.framework.fukkit.gamestate;

/**
 * Created by Yannic Rieger on 20.12.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public interface GamestateManager {

    Gamestate getCurrentGamestate();

    void setCurrentGamestate(Gamestate state);

}
