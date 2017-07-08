package de.bergwerklabs.framework.commons.spigot.game.event;

import de.bergwerklabs.framework.commons.spigot.game.LabsGame;
import de.bergwerklabs.framework.commons.spigot.game.LabsPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Yannic Rieger on 07.07.2017.
 * <p> Event that will be fired when an instance of {@link LabsGame} invokes {@link LabsGame#start()}.
 *
 * @author Yannic Rieger
 */
public class GameStartEvent extends Event {

    /**
     * Gets the {@link LabsGame} that has been started.
     */
    public <T extends LabsPlayer> LabsGame<T> getGame() { return this.game; }

    @Override
    public HandlerList getHandlers() { return handlerList; }

    private HandlerList handlerList = new HandlerList();
    private LabsGame game;

    /**
     * @param game Instance of a {@link LabsGame}.
     */
    public <T extends LabsPlayer> GameStartEvent(LabsGame<T> game) {
        this.game = game;
    }
}
