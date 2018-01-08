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


    /**
     * Retrieves a player from the map. Entry can be null if they left the game or is spectating.
     *
     * @param uuid {@link UUID} of the player.
     */
    public T getPlayer(UUID uuid) {
        return this.players.get(uuid);
    }

    /**
     * Retrieves a spectator from the map. Entry can be null if they left the game or is playing.
     *
     * @param uuid {@link UUID} of the player.
     */
    public T getSpectator(UUID uuid) {
        return this.spectators.get(uuid);
    }


    /**
     * Registers a spectator.
     *
     * @param spectator {@link LabsPlayer} to be registered as an spectator.
     * @return          instance of {@link LabsPlayer}
     */
    public T registerSpectator(T spectator) {
        return this.spectators.putIfAbsent(spectator.getPlayer().getUniqueId(), spectator);
    }

    /**
     * Registers a player.
     *
     * @param player {@link LabsPlayer} to be registered.
     * @return       instance of {@link LabsPlayer}.
     */
    public T registerPlayer(T player) {
        return this.players.putIfAbsent(player.getPlayer().getUniqueId(), player);
    }

    /**
     * Unregisters a spectator
     *
     * @param spectator {@link LabsPlayer} to be unregistered as an spectator.
     * @return          instance of {@link LabsPlayer}
     */
    public T unregisterSpectator(T spectator) {
        return this.spectators.remove(spectator);
    }

    /**
     * Unregisters a player.
     *
     * @param player {@link LabsPlayer} to be unregistered.
     * @return       instance of {@link LabsPlayer}
     */
    public T unregisterPlayer(T player) {
        return this.players.remove(player);
    }

}
