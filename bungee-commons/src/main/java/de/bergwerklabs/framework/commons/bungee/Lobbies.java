package de.bergwerklabs.framework.commons.bungee;

import net.md_5.bungee.api.config.ServerInfo;

/**
 * Created by Yannic Rieger on 10.04.2018.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public class Lobbies {

  public static boolean isSuitableLobby(int rankId, ServerInfo serverInfo) {
    System.out.println(rankId);
    String serverName = serverInfo.getName();
    return serverName.contains("silent") && Players.isVipOrUp(rankId)
        || serverName.contains("lobby_premium")
            && (Players.isPremium(rankId) || Players.isVipOrUp(rankId))
        || serverName.contains("lobby_default")
            && (Players.isPremium(rankId)
                || Players.isVipOrUp(rankId)
                || Players.isDefault(rankId));
  }
}
