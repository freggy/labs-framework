package de.bergwerklabs.framework.commons.spigot.hologram;

import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashSet;

/**
 * Created by Yannic Rieger on 20.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class HologramManager implements Listener {

    public static HashSet<Hologram> getHolograms() { return holograms; }

    private static HashSet<Hologram> holograms = new HashSet<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskLater(SpigotCommons.getInstance(),
                                           () -> holograms.forEach(hologram -> hologram.handleJoin(e.getPlayer())), 10L);
    }
}
