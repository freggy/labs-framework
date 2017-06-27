package de.bergwerklabs.framework.commons.spigot;

import de.bergwerklabs.framework.commons.spigot.file.FileUtil;
import de.bergwerklabs.framework.commons.spigot.general.LabsController;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Yannic Rieger on 02.05.2017.
 * <p> Main class </p>
 * @author Yannic Rieger
 */
public class LabsFrameworkSpigotCommons extends JavaPlugin implements Listener, LabsController {

    /**
     * Gets the instance of the Framework Plugin.
     */
    public static LabsFrameworkSpigotCommons getInstance() { return instance; }

    public final String CONSOLE_PREFIX = "[LabsFrameworkSpigotCommons] ";

    private static LabsFrameworkSpigotCommons instance;

    @Override
    public void onEnable() {
        // Just for test purposes
        FileUtil.createFolderIfNotExistent(this.getDataFolder());
        this.getServer().getPluginManager().registerEvents(this, this);
        instance = this;
    }

    @Override
    public void onDisable() {
        this.getServer().getScheduler().cancelTasks(this);
    }
}
