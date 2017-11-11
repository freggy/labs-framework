package de.bergwerklabs.framework.gameservice;

import de.bergwerklabs.framework.gameservice.api.LabsGame;
import de.bergwerklabs.framework.gameservice.api.LabsPlayer;

import java.util.HashMap;
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
     * Gets all {@link T} that play the {@link LabsGame}.
     */
    public HashMap<UUID, T> getPlayers() { return players; }

    /**
     * Gets all the Spectators in a {@link LabsGame}.
     */
    public HashMap<UUID, T> getSpectators() { return spectators; }

    private HashMap<UUID, T> players    = new HashMap<>();
    private HashMap<UUID, T> spectators = new HashMap<>();
}
