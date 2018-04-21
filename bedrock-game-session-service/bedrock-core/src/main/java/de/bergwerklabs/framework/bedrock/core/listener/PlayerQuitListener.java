package de.bergwerklabs.framework.bedrock.core.listener;

import de.bergwerklabs.atlantis.client.bukkit.GamestateManager;
import de.bergwerklabs.atlantis.columbia.packages.gameserver.spigot.gamestate.Gamestate;
import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.api.PlayerdataDao;
import de.bergwerklabs.framework.bedrock.core.config.SessionServiceConfig;
import java.util.UUID;
import org.bukkit.Bukkit;
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
   * @param dao playerdata DAO
   * @param config config of the current session.
   */
  public PlayerQuitListener(
      PlayerRegistry playerRegistry, PlayerdataDao dao, SessionServiceConfig config) {
    super(playerRegistry, dao, config);
  }

  @EventHandler(priority = EventPriority.MONITOR)
  public void onPlayerQuit(PlayerQuitEvent event) {

    if (Bukkit.getOnlinePlayers().size() == 1
        && GamestateManager.getCurrentState() == Gamestate.RUNNING) {
      Bukkit.getServer().spigot().restart(); // TODO: change to shutdown
      return;
    }

    final Player player = event.getPlayer();
    final UUID uuid = player.getUniqueId();
    final LabsPlayer labsPlayer = this.playerRegistry.getPlayer(uuid);

    if (labsPlayer == null) return;

    if (labsPlayer.isSpectator()) {
      this.playerRegistry.unregisterSpectator(labsPlayer);
    } else this.playerRegistry.unregisterPlayer(labsPlayer);

    // TODO: save stats
    this.dao.remove(player.getUniqueId());
  }
}
