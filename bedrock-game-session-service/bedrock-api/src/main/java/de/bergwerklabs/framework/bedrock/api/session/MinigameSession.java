package de.bergwerklabs.framework.bedrock.api.session;

import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.event.session.SessionInitializedEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

/**
 * Created by Yannic Rieger on 11.11.2017.
 * <p>
 * This class has to be extended by a main class for a mini game.
 *
 * @author Yannic Rieger
 */
public abstract class MinigameSession extends JavaPlugin implements GameSession {

    @Override
    public String getId() {
        return GameId.create();
    }

    public static MinigameSession getInstance() {
        return instance;
    }

    protected static MinigameSession instance;

    public <T extends LabsPlayer> T getPlayer(UUID uuid) {
        return (T) this.getGame().getPlayerRegistry().getPlayer(uuid);
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getServer().getPluginManager().callEvent(new SessionInitializedEvent(this));
    }
}
