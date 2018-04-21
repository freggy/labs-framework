package de.bergwerklabs.framework.bedrock.api.session;

import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.PlayerdataDao;
import de.bergwerklabs.framework.bedrock.api.event.session.SessionInitializedEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Yannic Rieger on 11.11.2017.
 *
 * <p>This class has to be extended by a main class for a mini game.
 *
 * @author Yannic Rieger
 */
public abstract class MinigameSession<T extends LabsPlayer> extends JavaPlugin
    implements GameSession {

  private PlayerdataDao dao;

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

  @Override
  public void onEnable() {
    Bukkit.getServer().getPluginManager().callEvent(new SessionInitializedEvent(this));
  }
}
