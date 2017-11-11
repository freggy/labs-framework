package de.bergwerklabs.framework.gameservice;

import de.bergwerklabs.framework.gameservice.api.GameSession;
import de.bergwerklabs.framework.gameservice.api.event.SessionInitializedEvent;
import de.bergwerklabs.framework.gameservice.config.GameServiceConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class GameService extends JavaPlugin implements Listener {

    public static GameService getInstance() { return instance; }

    public GameServiceConfig getServiceConfig() {
        return config;
    }

    private static GameService instance;
    private GameServiceConfig config;
    private GameSession session;

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        instance = this;
        // TODO: load config
        if (this.config.spectateOnDeath()) {
            Bukkit.getPluginManager().registerEvents(new Listener() {
                @EventHandler
                private void onCanBlockBuild(BlockCanBuildEvent event) {
                    event.setBuildable(true);
                }
            }, this);
        }
    }

    @EventHandler
    private void onSessionInitialized(SessionInitializedEvent event) {
        this.session = event.getSession();
        Bukkit.getServer().getServicesManager().register(GameSession.class, this.session, this, ServicePriority.Normal);
    }

}
