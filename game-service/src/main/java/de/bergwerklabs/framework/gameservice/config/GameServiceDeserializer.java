package de.bergwerklabs.framework.gameservice.config;

import com.google.gson.*;
import de.bergwerklabs.framework.gameservice.api.LabsGame;
import de.bergwerklabs.framework.gameservice.lobby.AbstractLobby;

import java.lang.reflect.Type;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class GameServiceDeserializer implements JsonDeserializer<GameServiceConfig> {

    @Override
    public GameServiceConfig deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

        try {
            JsonObject obj = json.getAsJsonObject();
            boolean loadStatsOnJoin       = obj.get("load-stats-on-join").getAsBoolean();
            boolean useAutoRespawn        = obj.get("use-auto-respawn").getAsBoolean();
            boolean spectateOnDeath       = obj.get("spectate-on-death").getAsBoolean();
            boolean incDeathStatOnDeath   = obj.get("increment-deaths-statistic-on-death").getAsBoolean();
            boolean incGamesPlayedOnDeath = obj.get("increment-games-played-on-death").getAsBoolean();
            boolean spectatorsEnabled     = obj.get("spectators-enabled").getAsBoolean();

            Class<AbstractLobby> lobbyClass;
            Class<LabsGame> gameClass = (Class<LabsGame>) Class.forName(obj.get("game-impl-class").getAsString());

            if (obj.has("lobby-impl-class")) {
                lobbyClass = (Class<AbstractLobby>) Class.forName(obj.get("lobby-impl-class").getAsString());
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }
}
