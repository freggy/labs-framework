package de.bergwerklabs.framework.bedrock.api.lobby;

import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.api.event.game.GameStartEvent;
import de.bergwerklabs.framework.bedrock.api.event.lobby.LobbyWaitingPhaseStopEvent;
import de.bergwerklabs.framework.bedrock.api.session.GameSession;
import de.bergwerklabs.framework.commons.spigot.general.timer.LabsTimer;
import de.bergwerklabs.framework.commons.spigot.general.timer.LabsTimerStopCause;
import de.bergwerklabs.framework.commons.spigot.general.update.TaskManager;
import de.bergwerklabs.framework.commons.spigot.title.ActionbarTitle;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.event.Listener;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 * Abstract lobby class. If custom lobby behavior is wanted, you have to extend this class.
 *
 * @author Yannic Rieger
 */
public abstract class AbstractLobby implements Listener {

    protected GameSession session;
    protected LabsTimer timer;
    protected int waitingDuration, maxPlayers, minPlayers;
    protected PlayerRegistry registry;

    /**
     * @param waitingDuration duration the players have to wait.
     * @param maxPlayers      maximum amount of player that can play the game.
     * @param minPlayers      minimum amount of players needed to start the game.
     * @param session         {@link GameSession} associated with this lobby.
     */
    public AbstractLobby(int waitingDuration, int maxPlayers, int minPlayers, GameSession session, PlayerRegistry registry) {
        this.session = session;
        this.waitingDuration = waitingDuration;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.registry = registry;
        this.timer = new LabsTimer(waitingDuration, timeLeft -> {
            int currentPlayers = Bukkit.getOnlinePlayers().size();
            if (timeLeft <= 5) {
                ActionbarTitle.broadcastTitle("§6» §aSpiel startet in §b" + timeLeft + " §6«");
                Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getEyeLocation(), Sound.NOTE_BASS, 1.0F, 1.0F));
            }
            else {
                ActionbarTitle.broadcastTitle("§6» §eSpieler: §b" + currentPlayers + "/" + this.maxPlayers +" §6❘ §aMindestens: §b" + this.minPlayers + " §6«");
            }
            Bukkit.getOnlinePlayers().forEach(player -> player.setLevel(timeLeft));
        });
    }

    public abstract void init();


    /**
     * Starts the waiting phase.
     */
    public abstract void startWaitingPhase();
}
