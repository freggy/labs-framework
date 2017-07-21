package de.bergwerklabs.framework.commons.spigot.npc;

import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.npc.event.Action;
import de.bergwerklabs.framework.commons.spigot.npc.event.NpcInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
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
        Bukkit.getScheduler().runTaskLater(SpigotCommons.getInstance(), () -> {
            npcs.values().forEach(npc -> npc.handleRespawn(e.getPlayer()));
        }, 5);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskLater(SpigotCommons.getInstance(), () -> {
            npcs.values().forEach(npc -> npc.handleJoin(e.getPlayer()));
        }, 5);
    }

    @EventHandler
    private void onPlayerInteractEntityEvent(PlayerInteractEntityEvent e) {
        npcs.values().stream().filter(npc -> npc.getEntityId() == e.getRightClicked().getEntityId())
                  .findFirst()
                  .ifPresent(npc -> manager.callEvent(new NpcInteractEvent(npc, e.getPlayer(), Action.RIGHT_CLICK)));
    }

    @EventHandler
    private void onPlayerDamageEntity(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();

        if (damager instanceof Player) {
            npcs.values().stream().filter(npc -> npc.getEntityId() == e.getEntity().getEntityId())
                .findFirst()
                .ifPresent(npc -> manager.callEvent(new NpcInteractEvent(npc, (Player) damager, Action.HIT)));
        }
    }
}
