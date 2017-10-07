package de.bergwerklabs.framework.commons.spigot.entity;

import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.entity.npc.Npc;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Yannic Rieger on 22.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class EntityManager implements Listener {

    /**
     *
     */
    public static Map<Integer, Entity> getEntities() { return entities; }

    private static Map<Integer, Entity> entities = new ConcurrentHashMap<>();

    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerRespawn(PlayerRespawnEvent e) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(SpigotCommons.getInstance(), () -> {
            entities.values().forEach(npc -> npc.handleRespawn(e.getPlayer()));
        }, 10);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(SpigotCommons.getInstance(), () -> {
            entities.values().stream().filter(Entity::isVisible).forEach(npc -> npc.handleJoin(e.getPlayer()));
        }, 10);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerMove(PlayerMoveEvent e) {
        entities.values().stream().filter(Entity::isVisible).forEach(npc -> npc.handleMove(e.getPlayer(), e.getTo(), e.getFrom()));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerTeleport(PlayerTeleportEvent e) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(SpigotCommons.getInstance(), () -> {
            entities.values().stream().filter(Entity::isVisible).forEach(npc -> npc.handleTeleport(e));
        }, 10);
    }


}
