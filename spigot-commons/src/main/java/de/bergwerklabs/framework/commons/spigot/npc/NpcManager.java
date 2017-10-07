package de.bergwerklabs.framework.commons.spigot.npc;

import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.entity.npc.GlobalNpc;
import de.bergwerklabs.framework.commons.spigot.entity.npc.Npc;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.PluginManager;

import java.util.HashMap;

/**
 * Created by Yannic Rieger on 14.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class NpcManager implements Listener {

    /**
     * Contains all {@link GlobalNpc}s created.
     */
    public static HashMap<Integer, Npc> geNpcs() { return npcs; }

    private static HashMap<Integer, Npc> npcs = new HashMap<>();

    private PluginManager manager = Bukkit.getPluginManager();

    @EventHandler
    private void onPlayerRespawn(PlayerRespawnEvent e) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(SpigotCommons.getInstance(), () -> {
            npcs.values().forEach(npc -> npc.handleRespawn(e.getPlayer()));
        }, 10);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(SpigotCommons.getInstance(), () -> {
            npcs.values().stream().filter(Npc::isVisible).forEach(npc -> npc.handleJoin(e.getPlayer()));
        }, 10);
    }

    @EventHandler
    private void onPlayerMove(PlayerMoveEvent e) {
        npcs.values().stream().filter(Npc::isVisible).forEach(npc -> npc.handleMove(e.getPlayer(), e.getTo(), e.getFrom()));
    }

    @EventHandler
    private void onPlayerTeleport(PlayerTeleportEvent e) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(SpigotCommons.getInstance(), () -> {
            npcs.values().stream().filter(Npc::isVisible).forEach(npc -> npc.handleTeleport(e));
        }, 10);
    }
}
