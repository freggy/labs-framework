package de.bergwerklabs.framework.bedrock.api.lobby;

import de.bergwerklabs.framework.bedrock.api.GameSession;
import de.bergwerklabs.framework.bedrock.api.event.lobby.LobbyWaitingPhaseStartEvent;
import org.bukkit.Bukkit;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 * Simple lobby implementation for mini games.
 *
 * @author Yannic Rieger
 */
public class SimpleLobby extends AbstractLobby {

    // TODO: implement start timer

    public SimpleLobby(int waitingDuration, GameSession session) {
        super(waitingDuration, session);
    }

    @Override
    public void startWaitingPhase() {
        Bukkit.getServer().getPluginManager().callEvent(new LobbyWaitingPhaseStartEvent(this.session));
        // TODO: implement start timer
    }
}
