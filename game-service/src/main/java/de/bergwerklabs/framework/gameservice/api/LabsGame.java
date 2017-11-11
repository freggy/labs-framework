package de.bergwerklabs.framework.gameservice.api;

import de.bergwerklabs.commons.spigot.chat.messenger.PluginMessenger;
import de.bergwerklabs.framework.gameservice.GameService;
import de.bergwerklabs.framework.gameservice.PlayerRegistry;
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
}
