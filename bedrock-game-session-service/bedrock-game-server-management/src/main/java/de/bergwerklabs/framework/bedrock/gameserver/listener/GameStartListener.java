package de.bergwerklabs.framework.bedrock.gameserver.listener;

import de.bergwerklabs.framework.bedrock.api.event.game.GameStartEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Yannic Rieger on 08.02.2018.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public class GameStartListener implements Listener {

  @EventHandler
  private void onGameStart(GameStartEvent event) {}
}
