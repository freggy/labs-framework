package de.bergwerklabs.framework.bedrock.service;

import de.bergwerklabs.atlantis.client.base.util.AtlantisPackageService;
import de.bergwerklabs.atlantis.client.bukkit.GamestateManager;
import de.bergwerklabs.atlantis.columbia.packages.gameserver.spigot.gamestate.Gamestate;
import de.bergwerklabs.framework.bedrock.api.GameSession;
import de.bergwerklabs.framework.bedrock.api.event.session.SessionDonePreparationEvent;
import de.bergwerklabs.framework.bedrock.api.event.session.SessionInitializedEvent;
import de.bergwerklabs.framework.bedrock.service.config.SessionServiceConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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
    public SessionServiceConfig getServiceConfig() {
        return this.config;
    }

    public Ranking getRanking() {
        return this.ranking;
    }

    public AtlantisPackageService getPacketService() {
        return service;
    }

    private static BedrockSessionService instance;
    private SessionServiceConfig config;
    private GameSession session;
    private Ranking ranking;
    private AtlantisPackageService service = new AtlantisPackageService();

    private boolean finishedPreparing = false;

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
        GamestateManager.setGamestate(Gamestate.PREPARING);
        this.session.prepare();
        // TODO: load ranking
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerJoin(PlayerLoginEvent event) {
        if (!finishedPreparing) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§6>> §eBedrock Session Service §6| §bGame session has not been initialized yet.");
        }
    }

    @EventHandler
    private void onPreparationDone(SessionDonePreparationEvent event) {
        this.finishedPreparing = true;
        GamestateManager.setGamestate(Gamestate.WAITING);
        event.getSession().getLobby().startWaitingPhase();
    }
}
