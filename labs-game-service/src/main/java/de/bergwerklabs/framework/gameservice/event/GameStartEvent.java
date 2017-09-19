package de.bergwerklabs.framework.gameservice.event;

import de.bergwerklabs.framework.gameservice.LabsGame;
import de.bergwerklabs.framework.gameservice.LabsPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Yannic Rieger on 07.07.2017.
 * <p> Event that will be fired when an instance of {@link LabsGame} invokes {@link LabsGame#start()}.
 *
 * @author Yannic Rieger
 */
public class GameStartEvent<T extends LabsPlayer> extends Event {

    /**
     * Gets the {@link LabsGame<T>} that has been started.
     */
    public LabsGame<T> getGame() { return this.game; }

    /**
     * Gets the list of all handlers.
     */
    public static HandlerList getHandlerList() { return handlers; }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    private static HandlerList handlers = new HandlerList();
    private HandlerList handlerList = new HandlerList();
    private LabsGame<T> game;

    /**
     * @param game Instance of a {@link LabsGame}.
     */
    public GameStartEvent(LabsGame<T> game) {
        this.game = game;
    }
}
