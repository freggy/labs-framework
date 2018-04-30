package de.bergwerklabs.framework.bedrock.core.listener;

import de.bergwerklabs.framework.bedrock.api.event.game.GameStartEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * Created by Yannic Rieger on 15.04.2018.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public class GameStartListener implements Listener {

  @EventHandler(priority = EventPriority.LOWEST)
  private void onGameStart(GameStartEvent event) {
    //GamestateManager.setGamestate(Gamestate.RUNNING);
  }
}
