package de.bergwerklabs.framework.gameservice.lobby;

import de.bergwerklabs.framework.commons.spigot.general.timer.LabsTimer;
import de.bergwerklabs.framework.gameservice.LabsGame;
import org.bukkit.event.Listener;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public abstract class AbstractLobby implements Listener {

    protected LabsGame game;
    protected LabsTimer timer;

    public AbstractLobby(int waitingDuration, LabsGame game) {
        this.game = game;
        this.timer = new LabsTimer(waitingDuration, null); // TODO: write start timer logic
    }

    /**
     *
     */
    public abstract void startGame();
}
