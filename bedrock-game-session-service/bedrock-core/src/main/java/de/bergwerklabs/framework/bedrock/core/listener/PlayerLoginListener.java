package de.bergwerklabs.framework.bedrock.core.listener;

import de.bergwerklabs.atlantis.client.bukkit.GamestateManager;
import de.bergwerklabs.atlantis.columbia.packages.gameserver.spigot.gamestate.Gamestate;
import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.api.PlayerdataDao;
import de.bergwerklabs.framework.bedrock.core.BedrockSessionService;
import de.bergwerklabs.framework.bedrock.core.config.SessionServiceConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Created by Yannic Rieger on 15.04.2018.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public class PlayerLoginListener extends LabsListener {

  /**
   * @param playerRegistry registry where all players ares registered.
   * @param dao
   * @param config config of the current session.
   */
  public PlayerLoginListener(
      PlayerRegistry<? extends LabsPlayer> playerRegistry,
      PlayerdataDao dao,
      SessionServiceConfig config) {
    super(playerRegistry, dao, config);
  }

  @EventHandler(priority = EventPriority.LOWEST)
  private void onPlayerLogin(PlayerLoginEvent event) {

    if (!BedrockSessionService.getInstance().hasFinishedPreparing()) {
      event.disallow(
          PlayerLoginEvent.Result.KICK_OTHER,
          "§6>> §eBedrock Session Service §6❘ §bGame session has not been initialized yet.");
    } else if (GamestateManager.getCurrentState() == Gamestate.WAITING
        && this.config.getMaxPlayers() == Bukkit.getOnlinePlayers().size()) {
      event.disallow(
          PlayerLoginEvent.Result.KICK_OTHER,
          "§6>> §eBedrock Session Service §6❘ §cDas Spiel ist " + "voll");
    }
  }
}
