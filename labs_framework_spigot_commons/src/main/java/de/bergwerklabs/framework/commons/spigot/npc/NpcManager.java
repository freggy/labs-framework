package de.bergwerklabs.framework.commons.spigot.npc;

import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.HashMap;

/**
 * Created by Yannic Rieger on 14.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class NpcManager implements Listener {

    /**
     *
     */
    public static HashMap<Integer, GlobalNpc> getGlobalNpcs() { return globalNpcs; }

    private static HashMap<Integer, GlobalNpc> globalNpcs = new HashMap<>();

    @EventHandler
    private void onPlayerRespawn(PlayerRespawnEvent e) {
        Bukkit.getScheduler().runTaskLater(SpigotCommons.getInstance(), () -> {
            getGlobalNpcs().values().forEach(npc -> npc.spawnSingle(e.getPlayer()));
        }, 5);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskLater(SpigotCommons.getInstance(), () -> {
            globalNpcs.values().forEach(npc -> npc.spawnSingle(e.getPlayer()));
        }, 5);
    }
}
