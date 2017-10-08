package de.bergwerklabs.framework.gameservice.config;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class GameServiceConfig {

    private boolean useAutoRespawn, spectateOnDeath, incrementDeathsOnDeath, incrementGamesPlayedOnDeath, loadStatisticsOnJoin, spectatorsEnabled;

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
}
