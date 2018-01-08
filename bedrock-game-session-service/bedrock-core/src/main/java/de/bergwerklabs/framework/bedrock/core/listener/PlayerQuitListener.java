package de.bergwerklabs.framework.bedrock.core.listener;

import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.api.PlayerdataDao;
import de.bergwerklabs.framework.bedrock.core.config.SessionServiceConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerQuitEvent;

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
        Player player = event.getPlayer();
        // TODO: save stats
        this.dao.remove(player.getUniqueId());
    }
}
