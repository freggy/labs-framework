package de.bergwerklabs.framework.bedrock.service.config;

import org.bukkit.Location;

import java.util.Set;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class SessionServiceConfig {

    private boolean useAutoRespawn, spectateOnDeath, incrementDeathsOnDeath, incrementGamesPlayedOnDeath, loadStatisticsOnJoin, spectatorsEnabled;
    private Set<Location> topThreeLocation;
    private Location playerStatsLocation;


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
    public boolean incrementGamesPlayedOnDeath() {
        return incrementGamesPlayedOnDeath;
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

    public Set<Location> getTopThreeLocation() {
        return topThreeLocation;
    }

    public Location getPlayerStatsLocation() {
        return playerStatsLocation;
    }
}
