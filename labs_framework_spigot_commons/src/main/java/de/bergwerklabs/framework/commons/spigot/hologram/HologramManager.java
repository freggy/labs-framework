package de.bergwerklabs.framework.commons.spigot.hologram;

import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.hologram.compound.GlobalHologramCompound;
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

    public static HashSet<GlobalHologramCompound> getGlobalHologramCompound() { return globalHologramCompounds; }

    private static HashSet<GlobalHologramCompound> globalHologramCompounds = new HashSet<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskLater(SpigotCommons.getInstance(), () -> globalHologramCompounds.forEach(GlobalHologramCompound::destroy), 5L);
        Bukkit.getScheduler().runTaskLater(SpigotCommons.getInstance(), () -> globalHologramCompounds.forEach(compound -> compound.display(compound.getLocation())), 10L);
    }
}
