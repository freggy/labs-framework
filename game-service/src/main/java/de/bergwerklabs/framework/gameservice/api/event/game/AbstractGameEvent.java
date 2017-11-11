package de.bergwerklabs.framework.gameservice.api.event.game;

import de.bergwerklabs.framework.commons.spigot.general.LabsEvent;
import de.bergwerklabs.framework.gameservice.api.LabsGame;
import de.bergwerklabs.framework.gameservice.api.LabsPlayer;

/**
 * Created by Yannic Rieger on 21.09.2017.
 * <p>
 * Base class for game events.
 *
 * @author Yannic Rieger
 */
public class AbstractGameEvent<T extends LabsPlayer> extends LabsEvent {

    /**
     * Gets the {@link LabsGame <T>} that has been started.
     */
    public LabsGame<T> getGame() { return this.game; }

    private LabsGame<T> game;

    /**
     * @param game Instance of a {@link LabsGame}.
     */
    AbstractGameEvent(LabsGame<T> game) {
        this.game = game;
    }
}
