package de.bergwerklabs.framework.bedrock.api.lobby;

import de.bergwerklabs.framework.bedrock.api.session.GameSession;
import de.bergwerklabs.framework.commons.spigot.general.timer.LabsTimer;
import de.bergwerklabs.framework.commons.spigot.title.ActionbarTitle;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
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
    protected int waitingDuration, maxPlayers, minPlayers;

    /**
     * @param waitingDuration
     * @param session
     */
    public AbstractLobby(int waitingDuration, int maxPlayers, int minPlayers, GameSession session) {
        this.session = session;
        this.waitingDuration = waitingDuration;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.timer = new LabsTimer(waitingDuration, timeLeft -> {
            int currentPlayers = Bukkit.getOnlinePlayers().size();
            if (timeLeft >= 6) {
                ActionbarTitle.broadcastTitle("§6» §aSpiel startet in §b" + timeLeft + " §6«");
                Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getEyeLocation(), Sound.NOTE_BASS, 1.0F, 1.0F));
            }
            else {
                ActionbarTitle.broadcastTitle("§6» §eSpieler: §b" + currentPlayers + "/" + this.maxPlayers +" §6| §aMindestens: §b" + this.minPlayers + " §6«");
            }
        });
    }

    public abstract void startWaitingPhase();
}
