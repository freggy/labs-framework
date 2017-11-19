package de.bergwerklabs.framework.bedrock.core.listener;

import de.bergwerklabs.atlantis.client.base.playerdata.PlayerdataSet;
import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.core.BedrockSessionService;
import de.bergwerklabs.framework.bedrock.core.config.SessionServiceConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Yannic Rieger on 01.08.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class PlayerJoinListener extends LabsListener {

    /**
     * @param playerRegistry
     */
    public PlayerJoinListener(PlayerRegistry playerRegistry, SessionServiceConfig config) {
        super(playerRegistry, config);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        LabsPlayer player = playerRegistry.registerPlayer(BedrockSessionService.getInstance().getPlayerFactory().createPlayer(event.getPlayer()));
        if (this.config.loadStatisticsOnJoin()) {
            Bukkit.getScheduler().runTaskAsynchronously(BedrockSessionService.getInstance(), () -> {
                PlayerdataSet set = new PlayerdataSet(event.getPlayer().getUniqueId());
                set.loadAndWait();
                player.setDataSet(set);
                BedrockSessionService.getInstance().getRanking().displayRankingToPlayer(player);
            });
        }
    }
}
