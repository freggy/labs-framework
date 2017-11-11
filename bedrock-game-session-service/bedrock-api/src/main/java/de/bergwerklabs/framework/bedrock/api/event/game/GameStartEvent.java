package de.bergwerklabs.framework.bedrock.api.event.game;

import de.bergwerklabs.framework.bedrock.api.LabsGame;
import de.bergwerklabs.framework.bedrock.api.LabsPlayer;

/**
 * Created by Yannic Rieger on 07.07.2017.
 * <p>
 * Event that will be fired when an instance of {@link LabsGame} invokes {@link LabsGame#start()}.
 *
 * @author Yannic Rieger
 */
public class GameStartEvent<T extends LabsPlayer> extends AbstractGameEvent<T> {

    public GameStartEvent(LabsGame<T> game) {
        super(game);
    }
}
