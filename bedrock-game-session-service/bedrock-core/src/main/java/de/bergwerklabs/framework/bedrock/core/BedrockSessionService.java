package de.bergwerklabs.framework.bedrock.core;

import com.google.gson.GsonBuilder;
import de.bergwerklabs.atlantis.api.logging.AtlantisLogger;
import de.bergwerklabs.atlantis.client.base.util.AtlantisPackageService;
import de.bergwerklabs.atlantis.client.bukkit.GamestateManager;
import de.bergwerklabs.atlantis.columbia.packages.gameserver.spigot.gamestate.Gamestate;
import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.bedrock.api.PlayerdataDao;
import de.bergwerklabs.framework.bedrock.api.lobby.AbstractLobby;
import de.bergwerklabs.framework.bedrock.api.session.GameSession;
import de.bergwerklabs.framework.bedrock.api.PlayerFactory;
import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.api.event.session.SessionDonePreparationEvent;
import de.bergwerklabs.framework.bedrock.api.event.session.SessionInitializedEvent;
import de.bergwerklabs.framework.bedrock.core.config.SessionServiceConfig;
import de.bergwerklabs.framework.bedrock.core.config.SessionServiceDeserializer;
import de.bergwerklabs.framework.bedrock.core.listener.PlayerDeathListener;
import de.bergwerklabs.framework.bedrock.core.listener.PlayerJoinListener;
import de.bergwerklabs.framework.bedrock.core.listener.PlayerQuitListener;
import de.bergwerklabs.framework.commons.spigot.general.update.TaskManager;
import de.bergwerklabs.framework.nabs.standalone.PlayerdataFactory;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class BedrockSessionService extends JavaPlugin implements Listener {

    /**
     * Gets an instance of the service.
     */
    public static BedrockSessionService getInstance() { return instance; }

    /**
     * Gets the {@link SessionServiceConfig}.
     */
    public SessionServiceConfig getServiceConfig() {
        return this.config;
    }

    /**
     * Gets the {@link Ranking} for this session.
     */
    public Ranking getRanking() {
        return this.ranking;
    }

    /**
     * Gets the {@link AtlantisPackageService} for this plugin.
     */
    public AtlantisPackageService getPacketService() {
        return service;
    }

    /**
     * Gets the {@link PlayerFactory} of this session.
     */
    public PlayerFactory<? extends LabsPlayer> getPlayerFactory() {
        return factory;
    }

    private Logger logger = Bukkit.getLogger();
    private static BedrockSessionService instance;
    private SessionServiceConfig config;
    private AbstractLobby lobby;
    private PlayerFactory factory;
    private PlayerdataFactory playerdataFactory;
    private Ranking ranking;
    private AtlantisPackageService service = new AtlantisPackageService();
    private boolean finishedPreparing = false;
    private final PlayerRegistry<? extends LabsPlayer> REGISTRY = new PlayerRegistry<>();

    @Override
    public void onEnable() {
        GamestateManager.setGamestate(Gamestate.PREPARING);
        TaskManager.startTimers(this);
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        instance = this;

        try {
            this.logger.info("Reading config...");
            this.getDataFolder().mkdirs();
            this.config = new GsonBuilder().registerTypeAdapter(SessionServiceConfig.class, new SessionServiceDeserializer())
                                           .create().fromJson(new FileReader(this.getDataFolder() + "/config.json"), SessionServiceConfig.class);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            this.logger.warning("No config file found. Stopping the server...");
            GamestateManager.setGamestate(Gamestate.FAILED);
            this.getServer().shutdown();
        }

        this.ranking = new Ranking(this.config.getTopThreeLocation(),
                                   this.config.getPlayerStatsLocation(),
                                   this.config.getGameDataCompund());

        Optional<PlayerFactory> factoryOptional         = ReflectionUtil.getFactoryClassInstance(this.config.getPlayerFactoryClass());
        Optional<PlayerdataFactory> dataFactoryOptional = ReflectionUtil.getFactoryClassInstance(this.config.getDataFactoryClass());
        this.factory           = this.checkOptional(factoryOptional);
        this.playerdataFactory = this.checkOptional(dataFactoryOptional);
    }

    @EventHandler
    private void onSessionInitialized(SessionInitializedEvent event) {
        GameSession session = event.getSession();
        this.logger.info("Session has been initialized.");
        this.logger.info("Game is " + session.getGame().getName());
        this.logger.info("Session ID is " + session.getId());
        session.setPlayerdataDao(new PlayerdataDao(this.playerdataFactory));

        Bukkit.getServer().getServicesManager().register(GameSession.class, session, this, ServicePriority.Normal);

        Optional<AbstractLobby> lobbyOptional = ReflectionUtil.getLobbyInstance(
                this.config.getLobbyClass(),
                this.config.getWaitingDuration(),
                this.config.getMaxPlayers(),
                this.config.getMinPlayers(),
                session,
                this.REGISTRY
        );

        this.lobby = this.checkOptional(lobbyOptional);
        Bukkit.getServer().getPluginManager().registerEvents(this.lobby, this);
        this.lobby.init();
        this.registerEvents(session.getPlayerdataDao());
        session.prepare();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerJoin(PlayerLoginEvent event) {
        if (!finishedPreparing) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§6>> §eBedrock Session Service §6| §bGame session has not been initialized yet.");
        }
        else if (Bukkit.getOnlinePlayers().size() == this.config.getMaxPlayers()) {
            // TODO: put spectator
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§6>> §eBedrock Session Service §6| §bGame is in " +
                    "progress");
        }
    }

    @EventHandler
    private void onPreparationDone(SessionDonePreparationEvent event) {
        this.logger.info("Preparation done.");
        this.logger.info("Starting lobby waiting phase...");
        System.out.println(event.getSession().getGame().getName());
        this.finishedPreparing = true;
        GamestateManager.setGamestate(Gamestate.WAITING);
        this.lobby.startWaitingPhase();
    }

    private <T> T checkOptional(Optional<T> optional) {
        if (!optional.isPresent()) {
            this.getLogger().warning("No valid Class could be found. Shutting down server...");
            GamestateManager.setGamestate(Gamestate.FAILED);
            this.getServer().shutdown();
            return null;
        }
        else return optional.get();
    }

    /**
     * Registers all events.
     *
     * @param dao
     */
    private void registerEvents(PlayerdataDao dao) {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new PlayerJoinListener(this.REGISTRY, dao, this.config), this);
        manager.registerEvents(new PlayerQuitListener(this.REGISTRY, dao, this.config), this);
        manager.registerEvents(new PlayerDeathListener(this.REGISTRY, dao, this.config), this);
    }
}
