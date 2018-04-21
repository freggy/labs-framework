package de.bergwerklabs.framework.bedrock.gameserver.listener;

import java.util.Optional;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Created by Yannic Rieger on 23.11.2017.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public class FullJoinListener implements Listener {

  @EventHandler
  private void onPlayerLogin(PlayerLoginEvent event) {
    Optional<? extends Player> playerOptional =
        Bukkit.getOnlinePlayers()
            .stream()
            .filter(player -> player.hasPermission("bergwerklabs.fulljoin"))
            .findFirst();
    if (playerOptional.isPresent()) {
      // TODO: move to random lobby
      playerOptional
          .get()
          .kickPlayer(
              ">> Bedrock Session Service | Du wurdest gekickt, weil ein Premium oder Teammitglied dem Server beigetreten ist.");
    } else
      event.disallow(
          PlayerLoginEvent.Result.KICK_OTHER,
          ">> Bedrock Session Service | Der Server ist leider voll :(");
  }
}
