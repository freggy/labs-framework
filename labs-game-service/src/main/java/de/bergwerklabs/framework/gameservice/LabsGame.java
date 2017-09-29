package de.bergwerklabs.framework.gameservice;

import de.bergwerklabs.framework.gameservice.listener.LabsListener;
import org.bukkit.Bukkit;

/**
 * Created by Yannic Rieger on 07.07.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public abstract class LabsGame<T extends LabsPlayer> {

    /**
     * Gets the {@link PlayerRegistry} for this game.
     */
    public PlayerRegistry<T> getPlayerRegistry() { return this.playerRegistry; }

    protected PlayerRegistry<T> playerRegistry = new PlayerRegistry<>();
    protected PluginMessenger messenger;

    public LabsGame(String name) {
        Bukkit.getPluginManager().registerEvents(new LabsListener<>(playerRegistry, GameService.getInstance().getServiceConfig()), GameService.getInstance());
        this.messenger = new PluginMessenger(name);
    }

    /**
     * Starts the game.
     */
    public abstract void start();

    /**
     * Stops the game.
     */
    public abstract void stop();
}
