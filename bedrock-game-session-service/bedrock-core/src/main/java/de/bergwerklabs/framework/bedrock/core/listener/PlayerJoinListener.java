package de.bergwerklabs.framework.bedrock.core.listener;

import de.bergwerklabs.atlantis.client.base.playerdata.PlayerdataSet;
import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.api.PlayerdataDao;
import de.bergwerklabs.framework.bedrock.core.BedrockSessionService;
import de.bergwerklabs.framework.bedrock.core.config.SessionServiceConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Yannic Rieger on 01.08.2017.
 *
 * @author Yannic Rieger
 */
public class PlayerJoinListener extends LabsListener {

    /**
     * @param playerRegistry registry where all players ares registered.
     * @param dao            playerdata DAO
     * @param config         config of the current session.
     */
    public PlayerJoinListener(PlayerRegistry playerRegistry, PlayerdataDao dao, SessionServiceConfig config) {
        super(playerRegistry, dao, config);
    }
    // TODO: use NABS abstraction

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        LabsPlayer player = playerRegistry.registerPlayer(BedrockSessionService.getInstance().getPlayerFactory().createPlayer(event.getPlayer()));
        if (this.config.loadStatisticsOnJoin()) {
            Bukkit.getScheduler().runTaskAsynchronously(BedrockSessionService.getInstance(), () -> {

                BedrockSessionService.getInstance().getRanking().displayRankingToPlayer(player);
            });
        }
    }
}
