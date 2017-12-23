package de.bergwerklabs.framework.nabs.bukkit.gamestate;

/**
 * Created by Yannic Rieger on 20.12.2017.
 * <p>
 * Handles changes of game states.
 *
 * @author Yannic Rieger
 */
public interface GamestateManager {

    /**
     * Gets the current {@link Gamestate}.
     */
    Gamestate getCurrentGamestate();

    /**
     * Sets the {@link Gamestate}.
     *
     * @param state current state of the game.
     */
    void setCurrentGamestate(Gamestate state);

}
