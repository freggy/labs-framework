package de.bergwerklabs.framework.bedrock.service.listener;

import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.service.config.SessionServiceConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Yannic Rieger on 01.08.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class PlayerQuitListener extends LabsListener {

    /**
     * @param playerRegistry
     */
    public PlayerQuitListener(PlayerRegistry playerRegistry, SessionServiceConfig config) {
        super(playerRegistry, config);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event) {
        // TODO: save stats.
    }
}
