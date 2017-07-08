package de.bergwerklabs.framework.commons.spigot.game;

import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Yannic Rieger on 01.05.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class PlayerManager<T extends LabsPlayer> implements Listener {

    /**
     *
     */
    public HashMap<UUID, T> getPlayers() { return players; }

    private HashMap<UUID, T> players = new HashMap<>();
}
