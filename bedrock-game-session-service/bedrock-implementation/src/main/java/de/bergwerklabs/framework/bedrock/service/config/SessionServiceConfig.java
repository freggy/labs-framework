package de.bergwerklabs.framework.bedrock.service.config;

import de.bergwerklabs.atlantis.api.logging.AtlantisLogger;
import org.bukkit.Location;

import java.util.Map;
import java.util.Set;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class SessionServiceConfig {

    private AtlantisLogger logger = AtlantisLogger.getLogger(getClass());

    SessionServiceConfig(Map<String, Boolean> options, Map<String, String> gameSettings, Map<String, Object> rankingSettings, Map<String, Integer> lobbySettings) {
        this.useAutoRespawn                  = options.get("use-auto-respawn");
        this.spectateOnDeath                 = options.get("spectate-on-death");
        this.incrementDeathsOnDeath          = options.get("increment-deaths-on-death");
        this.incrementGamesPlayedOnGameStart = options.get("increment-games-played-on-game-start");
        this.loadStatisticsOnJoin            = options.get("load-stats-on-join");
        this.spectatorsEnabled               = options.get("spectators-enabled");

        this.gameDataCompund    = gameSettings.get("game-data-compound");
        this.playerFactoryClass = gameSettings.get("player-factory-class");
        this.lobbyClass         = gameSettings.get("lobby-class");

        this.topThreeLocation    = (Set<Location>) rankingSettings.get("top-locations");
        this.playerStatsLocation = (Location) rankingSettings.get("player-location");

        this.maxPlayers      = lobbySettings.get("max-players");
        this.minPlayers      = lobbySettings.get("min-players");
        this.waitingDuration = lobbySettings.get("waiting-duration");

        this.logger.info("============= [Bedrock Session Config] =============");
        options.forEach((key, value) -> this.logger.info(key +  ": " + value));
        gameSettings.forEach((key, value) -> this.logger.info(key +  ": " + value));
        lobbySettings.forEach((key, value) -> this.logger.info(key +  ": " + value));
        rankingSettings.forEach((key, value) -> this.logger.info(key +  ": " + value));
        this.logger.info("====================================================");
    }


    private boolean useAutoRespawn, spectateOnDeath, incrementDeathsOnDeath, incrementGamesPlayedOnGameStart, loadStatisticsOnJoin, spectatorsEnabled;
    private Set<Location> topThreeLocation;
    private Location playerStatsLocation;
    private int maxPlayers, minPlayers, waitingDuration;
    private String gameDataCompund, playerFactoryClass, lobbyClass;

    /**
     *
     */
    public boolean useAutoRespawn() {
        return useAutoRespawn;
    }

    /**
     *
     */
    public boolean spectateOnDeath() {
        return spectateOnDeath;
    }

    /**
     *
     */
    public boolean incrementStatisticOnDeath() {
        return incrementDeathsOnDeath;
    }

    /**
     *
     */
    public boolean incrementGamesPlayedOnGameStart() {
        return incrementGamesPlayedOnGameStart;
    }

    /**
     *
     */
    public boolean loadStatisticsOnJoin() {
        return loadStatisticsOnJoin;
    }

    /**
     *
     */
    public boolean spectatorsEnabled() {
        return spectatorsEnabled;
    }

    /**
     *
     */
    public Set<Location> getTopThreeLocation() {
        return topThreeLocation;
    }

    /**
     *
     */
    public Location getPlayerStatsLocation() {
        return playerStatsLocation;
    }

    /**
     *
     */
    public String getGameDataCompund() {
        return gameDataCompund;
    }

    /**
     *
     */
    public String getPlayerFactoryClass() {
        return playerFactoryClass;
    }

    /**
     *
     */
    public String getLobbyClass() {
        return lobbyClass;
    }

    public int getWaitingDuration() {
        return waitingDuration;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
}
