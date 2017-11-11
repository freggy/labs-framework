package de.bergwerklabs.framework.gameservice.lobby;

import de.bergwerklabs.framework.gameservice.api.LabsGame;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 * Simple lobby implementation for mini games.
 *
 * @author Yannic Rieger
 */
public class SimpleLobby extends AbstractLobby {

    public SimpleLobby(int waitingDuration, LabsGame game) {
        super(waitingDuration, game);
    }

    @Override
    public void startGame() {
        this.game.start();
    }
}
