package de.bergwerklabs.framework.commons.spigot.npc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
    public static HashMap<Integer, Npc> getNpcs() { return npcs; }

    private static HashMap<Integer, Npc> npcs = new HashMap<>();

    @EventHandler
    private void onPlayerRespawn(PlayerRespawnEvent e) {
        getNpcs().values().forEach(npc -> npc.spawn(e.getPlayer()));
    }
}
