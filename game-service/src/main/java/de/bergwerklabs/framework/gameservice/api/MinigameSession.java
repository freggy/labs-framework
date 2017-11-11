package de.bergwerklabs.framework.gameservice.api;

import de.bergwerklabs.framework.gameservice.api.event.SessionInitializedEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Yannic Rieger on 11.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public abstract class MinigameSession extends JavaPlugin implements GameSession {

    @Override
    public String getId() {
        return GameId.create();
    }

    public MinigameSession() {
        Bukkit.getServer().getPluginManager().callEvent(new SessionInitializedEvent(this));
    }
}
