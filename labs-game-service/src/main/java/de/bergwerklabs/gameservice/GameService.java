package de.bergwerklabs.gameservice;

import de.bergwerklabs.gameservice.config.GameServiceConfig;
import de.bergwerklabs.gameservice.statistic.StatisticsContext;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Yannic Rieger on 18.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class GameService extends JavaPlugin {

    public static GameService getInstance() { return instance; }

    public GameServiceConfig getServiceConfig() {
        return config;
    }

    public StatisticsContext getContext() {
        return context;
    }

    public void setContext(StatisticsContext context) {
        this.context = context;
    }

    private static GameService instance;
    private GameServiceConfig config;
    private StatisticsContext context;

    @Override
    public void onEnable() {
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
}
