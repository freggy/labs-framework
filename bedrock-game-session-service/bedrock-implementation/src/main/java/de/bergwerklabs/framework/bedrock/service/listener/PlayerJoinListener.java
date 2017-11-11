package de.bergwerklabs.framework.bedrock.service.listener;

import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.service.config.GameServiceConfig;
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
     * @param playerRegistry
     */
    public PlayerJoinListener(PlayerRegistry<T> playerRegistry, GameServiceConfig config) {
        super(playerRegistry, config);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (this.config.loadStatisticsOnJoin()) {
            // TODO: load statistics using context
        }
    }
}
