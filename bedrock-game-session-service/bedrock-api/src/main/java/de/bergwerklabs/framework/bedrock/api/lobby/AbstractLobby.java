package de.bergwerklabs.framework.bedrock.api.lobby;

import de.bergwerklabs.framework.bedrock.api.GameSession;
import de.bergwerklabs.framework.commons.spigot.general.timer.LabsTimer;
import org.bukkit.event.Listener;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public abstract class AbstractLobby implements Listener {

    protected GameSession session;
    protected LabsTimer timer;

    /**
     * @param waitingDuration
     * @param session
     */
    public AbstractLobby(int waitingDuration, GameSession session) {
        this.session = session;
        this.timer = new LabsTimer(waitingDuration, null); // TODO: write start timer logic
    }

    public abstract void startWaitingPhase();
}
