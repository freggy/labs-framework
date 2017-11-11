package de.bergwerklabs.framework.bedrock.service.listener;

import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.service.config.GameServiceConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Yannic Rieger on 01.08.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class PlayerQuitListener<T extends LabsPlayer> extends LabsListener<T> {

    /**
     * @param playerRegistry
     */
    public PlayerQuitListener(PlayerRegistry<T> playerRegistry, GameServiceConfig config) {
        super(playerRegistry, config);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        // TODO: load statistics.
    }
}
