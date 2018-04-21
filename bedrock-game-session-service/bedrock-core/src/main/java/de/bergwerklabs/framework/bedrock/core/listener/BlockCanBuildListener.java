package de.bergwerklabs.framework.bedrock.core.listener;

import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.api.PlayerdataDao;
import de.bergwerklabs.framework.bedrock.core.config.SessionServiceConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockCanBuildEvent;

/**
 * Created by Yannic Rieger on 15.04.2018.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public class BlockCanBuildListener extends LabsListener {

  /**
   * @param playerRegistry registry where all players ares registered.
   * @param dao
   * @param config config of the current session.
   */
  public BlockCanBuildListener(
      PlayerRegistry<? extends LabsPlayer> playerRegistry,
      PlayerdataDao dao,
      SessionServiceConfig config) {
    super(playerRegistry, dao, config);
  }

  @EventHandler
  private void onBlockCanBuild(BlockCanBuildEvent event) {
    if (this.config.isSpectatorsEnabled()) {
      event.setBuildable(true);
    }
  }
}
