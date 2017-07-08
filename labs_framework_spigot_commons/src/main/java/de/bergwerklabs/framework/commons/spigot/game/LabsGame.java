package de.bergwerklabs.framework.commons.spigot.game;

import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.game.listener.LabsListener;
import org.bukkit.Bukkit;

/**
 * Created by Yannic Rieger on 07.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public abstract class LabsGame<T extends LabsPlayer> {

    /**
     * Gets the {@link PlayerManager} for this game.
     */
    public PlayerManager<T> getPlayerManager() { return this.playerManager; }

    protected PlayerManager<T> playerManager = new PlayerManager<>();

    public LabsGame() {
        Bukkit.getPluginManager().registerEvents(new LabsListener<>(playerManager), SpigotCommons.getInstance());
    }

    /**
     * Starts the game.
     */
    public abstract void start();
}
