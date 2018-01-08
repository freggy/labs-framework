package de.bergwerklabs.framework.bedrock.api.session;

import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.PlayerdataDao;
import de.bergwerklabs.framework.bedrock.api.event.session.SessionInitializedEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by Yannic Rieger on 11.11.2017.
 * <p>
 * This class has to be extended by a main class for a mini game.
 *
 * @author Yannic Rieger
 */
public abstract class MinigameSession<T extends LabsPlayer> extends JavaPlugin implements GameSession {

    @Override
    public String getId() {
        return GameId.create();
    }

    @Override
    public PlayerdataDao getPlayerdataDao() {
        return this.dao;
    }

    @Override
    public void setPlayerdataDao(PlayerdataDao dao) {
        this.dao = dao;
    }

    private PlayerdataDao dao;

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().callEvent(new SessionInitializedEvent(this));
    }
}
