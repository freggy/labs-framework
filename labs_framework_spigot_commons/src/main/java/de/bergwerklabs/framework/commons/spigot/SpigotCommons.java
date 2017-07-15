package de.bergwerklabs.framework.commons.spigot;

import de.bergwerklabs.framework.commons.spigot.file.FileUtil;
import de.bergwerklabs.framework.commons.spigot.general.LabsController;
import de.bergwerklabs.framework.commons.spigot.npc.NpcManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Yannic Rieger on 02.05.2017.
 * <p> Main class </p>
 * @author Yannic Rieger
 */
public class SpigotCommons extends JavaPlugin implements Listener, LabsController {

    /**
     * Gets the instance of the Framework Plugin.
     */
    public static SpigotCommons getInstance() { return instance; }

    public final String CONSOLE_PREFIX = "[SpigotCommons] ";

    private static SpigotCommons instance;

    @Override
    public void onEnable() {
        // Just for test purposes
        FileUtil.createFolderIfNotExistent(this.getDataFolder());
        instance = this;

        this.getServer().getPluginManager().registerEvents(new NpcManager(), this);
    }

    @Override
    public void onDisable() {
        this.getServer().getScheduler().cancelTasks(this);
    }
}
