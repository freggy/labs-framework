package de.bergwerklabs.framework.bedrock.core.listener;

import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.api.PlayerdataDao;
import de.bergwerklabs.framework.bedrock.core.config.SessionServiceConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

/**
 * Created by Yannic Rieger on 01.08.2017.
 *
 * @author Yannic Rieger
 */
public class PlayerQuitListener extends LabsListener {

    /**
     * @param playerRegistry registry where all players ares registered.
     * @param dao            playerdata DAO
     * @param config         config of the current session.
     */
    public PlayerQuitListener(PlayerRegistry playerRegistry, PlayerdataDao dao, SessionServiceConfig config) {
        super(playerRegistry, dao, config);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();
        final LabsPlayer labsPlayer = this.playerRegistry.getPlayer(uuid);

        if (labsPlayer.isSpectator()) {
            this.playerRegistry.unregisterSpectator(labsPlayer);
        }
        else this.playerRegistry.unregisterPlayer(labsPlayer);

        // TODO: save stats
        this.dao.remove(player.getUniqueId());
    }
}
