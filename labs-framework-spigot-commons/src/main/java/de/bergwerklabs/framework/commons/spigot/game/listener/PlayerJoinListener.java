package de.bergwerklabs.framework.commons.spigot.game.listener;

import de.bergwerklabs.framework.commons.spigot.game.LabsPlayer;
import de.bergwerklabs.framework.commons.spigot.game.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Yannic Rieger on 01.08.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class PlayerJoinListener<T extends LabsPlayer> extends LabsListener<T> {

    /**
     * @param playerManager
     */
    public PlayerJoinListener(PlayerManager<T> playerManager) {
        super(playerManager);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        // TODO: load statistics.
    }
}
