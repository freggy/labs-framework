package de.bergwerklabs.framework.bedrock.service.config;

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


    SessionServiceConfig(Map<String, Boolean> options, Map<String, String> gameSettings, Map<String, Object> rankingSettings) {
        this.useAutoRespawn                  = options.get("use-auto-respawn");
        this.spectateOnDeath                 = options.get("spectate-on-death");
        this.incrementDeathsOnDeath          = options.get("increment-deaths-on-death");
        this.incrementGamesPlayedOnGameStart = options.get("increment-games-played-on-game-start");
        this.loadStatisticsOnJoin            = options.get("load-statistics-on-join");
        this.spectatorsEnabled               = options.get("spectators-enabled");

        this.gameDataCompund    = gameSettings.get("game-data-compound");
        this.playerFactoryClass = gameSettings.get("player-factory-class");
        this.lobbyClass         = gameSettings.get("lobby-class");

        this.topThreeLocation    = (Set<Location>) rankingSettings.get("top-locations");
        this.playerStatsLocation = (Location) rankingSettings.get("player-location");
    }


    private boolean useAutoRespawn, spectateOnDeath, incrementDeathsOnDeath, incrementGamesPlayedOnGameStart, loadStatisticsOnJoin, spectatorsEnabled;
    private Set<Location> topThreeLocation;
    private Location playerStatsLocation;
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
}
