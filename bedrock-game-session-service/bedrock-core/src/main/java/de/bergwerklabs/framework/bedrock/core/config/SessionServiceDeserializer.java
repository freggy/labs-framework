package de.bergwerklabs.framework.bedrock.core.config;

import com.google.gson.*;
import de.bergwerklabs.framework.commons.spigot.json.JsonUtil;
import de.bergwerklabs.framework.commons.spigot.location.LocationUtil;
import org.bukkit.Location;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 * Deserializer for the {@link SessionServiceConfig}.
 *
 * @author Yannic Rieger
 */
public class SessionServiceDeserializer implements JsonDeserializer<SessionServiceConfig> {

    @Override
    public SessionServiceConfig deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject obj = json.getAsJsonObject();
        boolean loadStatsOnJoin       = obj.get("load-stats-on-join").getAsBoolean();
        boolean useAutoRespawn        = obj.get("use-auto-respawn").getAsBoolean();
        boolean spectateOnDeath       = obj.get("spectate-on-death").getAsBoolean();
        boolean incDeathStatOnDeath   = obj.get("increment-games-played-on-game-start").getAsBoolean();
        boolean incGamesPlayedOnStart = obj.get("increment-deaths-on-death").getAsBoolean();
        boolean spectatorsEnabled     = obj.get("spectators-enabled").getAsBoolean();

        String lobbyClass   = obj.get("lobby-class").getAsString();
        String factoryClass = obj.get("player-factory-class").getAsString();
        String dataCompount = obj.get("game-data-compound").getAsString();

        JsonObject ranking = obj.getAsJsonObject("ranking");
        Set<Location> topLocations = JsonUtil.jsonArrayToJsonObjectList(ranking.getAsJsonArray("top-locations"))
                                             .stream().map(LocationUtil::locationFromJson).collect(Collectors.toSet());

        Location playerStatsLocation = LocationUtil.locationFromJson(ranking.getAsJsonObject("player-location"));

        Map<String, Boolean> options = new HashMap<>();
        options.put("load-stats-on-join", loadStatsOnJoin);
        options.put("use-auto-respawn", useAutoRespawn);
        options.put("spectate-on-death", spectateOnDeath);
        options.put("increment-deaths-on-death", incDeathStatOnDeath);
        options.put("increment-games-played-on-game-start", incGamesPlayedOnStart);
        options.put("spectators-enabled", spectatorsEnabled);

        Map<String, String> gameSettings = new HashMap<>();
        gameSettings.put("lobby-class", lobbyClass);
        gameSettings.put("player-factory-class", factoryClass);
        gameSettings.put("game-data-compound", dataCompount);

        Map<String, Object> rankingSettings = new HashMap<>();
        rankingSettings.put("top-locations", topLocations);
        rankingSettings.put("player-location", playerStatsLocation);

        Map<String, Integer> lobbySettings = new HashMap<>();
        lobbySettings.put("max-players", obj.get("max-players").getAsInt());
        lobbySettings.put("min-players", obj.get("min-players").getAsInt());
        lobbySettings.put("waiting-duration",obj.get("waiting-duration").getAsInt());

        Map<String, List<String>> configuredStats = this.retrieveConfiguredStats(obj.get("configured-statistics").getAsJsonArray());

        return new SessionServiceConfig(options, gameSettings, rankingSettings, lobbySettings, configuredStats);
    }

    /**
     *
     * @param array
     * @return
     */
    private Map<String, List<String>> retrieveConfiguredStats(JsonArray array) {
        Map<String, List<String>> map = new HashMap<>();
        JsonUtil.jsonArrayToJsonObjectList(array).stream().forEach(configured -> {
            List<String> keys = new ArrayList<>();
            configured.get("data-keys").getAsJsonArray().forEach(key -> keys.add(key.getAsString()));
            map.put(configured.get("data-group").getAsString(), keys);
        });
        return map;
    }
}
