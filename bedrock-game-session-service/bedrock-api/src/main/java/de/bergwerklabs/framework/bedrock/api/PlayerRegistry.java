package de.bergwerklabs.framework.bedrock.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Yannic Rieger on 01.05.2017.
 * <p>
 * Class which keeps track of every player that will play the game.
 * The {@code PlayerRegistry} is part of every {@link LabsGame}.
 *
 * @author Yannic Rieger
 */
public class PlayerRegistry<T extends LabsPlayer> {

    /**
     * Gets all {@link LabsPlayer} that play the {@link LabsGame}.
     */
    public Map<UUID, T> getPlayers() { return Collections.unmodifiableMap(players); }

    /**
     * Gets all the Spectators in a {@link LabsGame}.
     */
    public Map<UUID, T> getSpectators() { return Collections.unmodifiableMap(spectators); }

    private Map<UUID, T> players    = new HashMap<>();
    private Map<UUID, T> spectators = new HashMap<>();


    public T registerSpectator(T spectator) {
        return this.spectators.putIfAbsent(spectator.getPlayer().getUniqueId(), spectator);
    }

    public T registerPlayer(T player) {
        return this.players.putIfAbsent(player.getPlayer().getUniqueId(), player);
    }

    public T unregisterPlayer(LabsPlayer player) {
        return this.players.remove(player);
    }

    public T unregisterSpectator(LabsPlayer spectator) {
        return this.spectators.remove(spectator);
    }

}
