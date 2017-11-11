package de.bergwerklabs.framework.bedrock.service;

import de.bergwerklabs.framework.bedrock.api.GameSession;
import de.bergwerklabs.framework.bedrock.api.event.SessionInitializedEvent;
import de.bergwerklabs.framework.bedrock.service.config.GameServiceConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class BedrockSessionService extends JavaPlugin implements Listener {

    /**
     *
     */
    public static BedrockSessionService getInstance() { return instance; }

    /**
     *
     */
    public GameServiceConfig getServiceConfig() {
        return config;
    }

    private static BedrockSessionService instance;
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
        this.session.getLobby().startWaitingPhase();
    }

    @EventHandler
    private void onPlayerJoin(PlayerLoginEvent event) {
        if (session == null) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§6>> §eBedrock Session Service §6| §bGame session has not been initialized yet.");
        }
    }
}
