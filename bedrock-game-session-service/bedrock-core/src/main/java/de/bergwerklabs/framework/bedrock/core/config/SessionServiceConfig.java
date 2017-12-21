package de.bergwerklabs.framework.bedrock.core.config;

import de.bergwerklabs.atlantis.api.logging.AtlantisLogger;
import org.bukkit.Location;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 * Stores the configuration for the {@link de.bergwerklabs.framework.bedrock.core.BedrockSessionService}
 *
 * @author Yannic Rieger
 */
public class SessionServiceConfig {

    private AtlantisLogger logger = AtlantisLogger.getLogger(getClass());

    /**
     * For further documentation head to the corresponding Confluence page.
     *
     * @param options         contains all the general game options.
     * @param gameSettings    contains all game settings.
     * @param rankingSettings contains all ranking settings.
     * @param lobbySettings   contains all lobby settings
     */
    SessionServiceConfig(Map<String, Boolean> options,
                         Map<String, String> gameSettings,
                         Map<String, Object> rankingSettings,
                         Map<String, Integer> lobbySettings,
                         Map<String, List<String>> configuredStatistics) {
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

        this.configuredStatistics = configuredStatistics;

        this.logger.info("============= [Bedrock Session Config] =============");
        options.forEach((key, value) -> this.logger.info(key + ": " + value));
        gameSettings.forEach((key, value) -> this.logger.info(key + ": " + value));
        lobbySettings.forEach((key, value) -> this.logger.info(key + ": " + value));
        rankingSettings.forEach((key, value) -> this.logger.info(key + ": " + value));
        this.logger.info("====================================================");
    }

    private boolean useAutoRespawn, spectateOnDeath, incrementDeathsOnDeath, incrementGamesPlayedOnGameStart, loadStatisticsOnJoin, spectatorsEnabled;
    private Set<Location> topThreeLocation;
    private Location playerStatsLocation;
    private int maxPlayers, minPlayers, waitingDuration;
    private String gameDataCompund, playerFactoryClass, lobbyClass;
    private Map<String, List<String>> configuredStatistics;

    /**
     * Gets a value indicating whether or not auto respawn is enabled.
     */
    public boolean useAutoRespawn() {
        return useAutoRespawn;
    }

    /**
     * Gets a value indicating whether or not players will be spectators on death.
     */
    public boolean spectateOnDeath() {
        return spectateOnDeath;
    }

    /**
     * Gets a value indicating whether or not the deaths statistic will be incremented on player death.
     */
    public boolean incrementStatisticOnDeath() {
        return incrementDeathsOnDeath;
    }

    /**
     * Gets a value indicating whether or not the games played statistic will be incremented on game start.
     */
    public boolean incrementGamesPlayedOnGameStart() {
        return incrementGamesPlayedOnGameStart;
    }

    /**
     * Gets whether or not the statistics will be loaded on join.
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
     * Gets the {@link Location}s of the top three players for this game mode.
     */
    public Set<Location> getTopThreeLocation() {
        return topThreeLocation;
    }

    /**
     * Gets the {@link Location}s of players own NPC.
     */
    public Location getPlayerStatsLocation() {
        return playerStatsLocation;
    }

    /**
     * Gets the data compound describing the statistics for this game. e.g {@code stats.flash}
     */
    public String getGameDataCompund() {
        return gameDataCompund;
    }

    /**
     * Gets the FQCN of {@link de.bergwerklabs.framework.bedrock.api.PlayerFactory} for this session.
     */
    public String getPlayerFactoryClass() {
        return playerFactoryClass;
    }

    /**
     * Gets the FQCN of {@link de.bergwerklabs.framework.bedrock.api.PlayerFactory} for this session.
     */
    public String getLobbyClass() {
        return lobbyClass;
    }

    /**
     * Gets the duration the players have to wait until the game starts.
     */
    public int getWaitingDuration() {
        return waitingDuration;
    }

    /**
     * Gets the minimum amount of players.
     */
    public int getMinPlayers() {
        return minPlayers;
    }

    /**
     * Gets the maximum amount of players.
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }
}
