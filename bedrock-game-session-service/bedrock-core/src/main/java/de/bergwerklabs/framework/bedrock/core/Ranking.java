package de.bergwerklabs.framework.bedrock.core;

import de.bergwerklabs.api.cache.pojo.PlayerdataEntry;
import de.bergwerklabs.api.cache.pojo.RankingCacheEntry;
import de.bergwerklabs.atlantis.api.corepackages.AtlantisCache;
import de.bergwerklabs.atlantis.api.corepackages.cache.CacheLoadAndGetPacket;
import de.bergwerklabs.atlantis.api.corepackages.cache.CachePacket;
import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.bukkit.Location;

/**
 * Created by Yannic Rieger on 13.11.2017.
 *
 * <p>Class responsible for loading the ranking and displaying it to other players.
 *
 * @author Yannic Rieger
 */
public class Ranking {

  private Set<Location> topThree;
  private Location playerStats;
  private String game;
  private PlayerdataEntry[] topPlayers;

  /**
   * @param topThree {@link Set} containing the {@link Location} of the top three players.
   *     <b>NOTE:</b> The order of the places will be determined by the order the {@link Location}s
   *     contained in the set.
   * @param playerStats this is the place where the players own NPC will be spawned.
   * @param game name of the mini game that will be played.
   */
  Ranking(Set<Location> topThree, Location playerStats, String game) {
    this.topThree = topThree;
    this.playerStats = playerStats;
    this.game = game;
  }

  /**
   * Displays the NPCs to the player.
   *
   * @param player {@link LabsPlayer} to display the NPCs to.
   */
  public void displayRankingToPlayer(LabsPlayer player) {
    retrieveTopPlayers();
    // TODO: create and spawn NPCs at given location
  }

  /** Retrieves the top players if they have not been retrieved already. */
  private void retrieveTopPlayers() {
    if (this.topPlayers == null) {
      try {
        RankingCacheEntry entry =
            (RankingCacheEntry)
                BedrockSessionService.getInstance()
                    .getPacketService()
                    .sendRequestWithFuture(
                        new CacheLoadAndGetPacket<>(this.game, AtlantisCache.RANKING_CACHE),
                        CachePacket.class)
                    .get(4, TimeUnit.SECONDS)
                    .getCacheObject();
        this.topPlayers = entry.getTopPlayers();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
