package de.bergwerklabs.framework.commons.spigot.game.listener;

import de.bergwerklabs.framework.commons.spigot.game.LabsPlayer;
import de.bergwerklabs.framework.commons.spigot.game.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Yannic Rieger on 01.08.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class PlayerQuitListener<T extends LabsPlayer> extends LabsListener<T> {

    /**
     * @param playerManager
     */
    public PlayerQuitListener(PlayerManager<T> playerManager) {
        super(playerManager);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        // TODO: load statistics.
    }
}
