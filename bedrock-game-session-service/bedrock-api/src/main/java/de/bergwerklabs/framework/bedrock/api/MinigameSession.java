package de.bergwerklabs.framework.bedrock.api;

import de.bergwerklabs.framework.bedrock.api.event.SessionInitializedEvent;
import de.bergwerklabs.framework.bedrock.api.lobby.AbstractLobby;
import de.bergwerklabs.framework.bedrock.api.lobby.SimpleLobby;
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

    @Override
    public AbstractLobby getLobby() { return this.lobby; }

    protected SimpleLobby lobby;

    public MinigameSession() {
        this.lobby = new SimpleLobby(20, this);
        Bukkit.getServer().getPluginManager().callEvent(new SessionInitializedEvent(this));
    }
}
