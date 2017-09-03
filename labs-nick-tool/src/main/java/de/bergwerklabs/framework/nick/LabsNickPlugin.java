package de.bergwerklabs.framework.nick;

import de.bergwerklabs.framework.nick.api.NickApi;
import de.bergwerklabs.framework.nick.command.NickCommand;
import de.bergwerklabs.framework.nick.command.NickListCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Yannic Rieger on 03.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class LabsNickPlugin extends JavaPlugin implements Listener {

    /**
     *
     */
    public static LabsNickPlugin getInstance() { return instance; }

    /**
     *
     */
    public NickApi getNickApi() { return this.manager; }

    private static LabsNickPlugin instance;
    private NickManager manager;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("nick").setExecutor(new NickCommand());
        this.getCommand("nicklist").setExecutor(new NickListCommand());
        this.manager = new NickManager(NickUtil.retrieveNickNames(), NickUtil.retrieveSkins());
    }

    @EventHandler
    private void onPlayerLoginEvent(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        // TODO: check if player has aut nick on
        this.manager.nickPlayer(player);
    }
}
