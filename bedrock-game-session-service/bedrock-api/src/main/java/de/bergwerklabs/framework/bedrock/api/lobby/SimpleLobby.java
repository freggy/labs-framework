package de.bergwerklabs.framework.bedrock.api.lobby;

import de.bergwerklabs.framework.bedrock.api.session.GameSession;
import de.bergwerklabs.framework.bedrock.api.event.lobby.LobbyWaitingPhaseStartEvent;
import de.bergwerklabs.framework.commons.spigot.general.timer.LabsTimerStopCause;
import de.bergwerklabs.framework.commons.spigot.general.timer.event.LabsTimerStopEvent;
import de.bergwerklabs.framework.commons.spigot.general.update.TaskManager;
import de.bergwerklabs.framework.commons.spigot.title.ActionbarTitle;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 * Simple lobby implementation for mini games.
 *
 * @author Yannic Rieger
 */
public class SimpleLobby extends AbstractLobby {

    private boolean timerShortened = false;

    /**
     * @param waitingDuration
     * @param maxPlayers
     * @param minPlayers
     * @param session
     */
    public SimpleLobby(int waitingDuration, int maxPlayers, int minPlayers, GameSession session) {
        super(waitingDuration, maxPlayers, minPlayers, session);
    }

    @Override
    public void startWaitingPhase() {
        Bukkit.getServer().getPluginManager().callEvent(new LobbyWaitingPhaseStartEvent(this.session));

        TaskManager.registerAsyncRepeatingTask(() -> {
            int currentPlayers = Bukkit.getOnlinePlayers().size();
            if (currentPlayers < this.minPlayers) {
                this.timer.stop();
                ActionbarTitle.broadcastTitle("§6» §cWarte auf §4" + (this.minPlayers - currentPlayers) + " andere Spieler... §6«");
            }
            else if (currentPlayers >= minPlayers) {
                if (!this.timer.isRunning()) {
                    this.timer.start();
                }
            }
            else if (currentPlayers >= maxPlayers) {
                if (!timerShortened) {
                    this.timer.setTimeLeft(15);
                    timerShortened = true;
                    this.session.getGame().getMessenger().messageAll("§bMaximal Anzahl erreicht. Timer wird auf 15 Sekunden verkürzt.");
                }
            }
        }, 20, 10);
    }

    @EventHandler
    private void onTimerStopped(LabsTimerStopEvent event) {
        if (event.getCause() == LabsTimerStopCause.TIMES_UP) {
            // clear chat.
            for (int i = 0; i < 30; i++) {
                this.session.getGame().getMessenger().messageAll(" ");
            }
            this.session.getGame().start();
        }
    }
}
