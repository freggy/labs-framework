package de.bergwerklabs.framework.commons.spigot.npc.event;

import de.bergwerklabs.framework.commons.spigot.npc.Npc;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Created by Yannic Rieger on 22.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class NpcInteractAtEvent extends NpcInteractEvent {

    /**
     * Gets a {@link Vector} indicating where the player interacted with the {@link Npc}.
     */
    private Vector getVector() { return this.vector; }

    private Vector vector;

    /**
     * @param npc Gets the {@link Npc} that the player interacted with.
     * @param player Gets the {@link Player} that interacted with a {@link Npc}.
     * @param action Gets the {@link Action}
     */
    public NpcInteractAtEvent(Npc npc, Player player, Action action, Vector vector) {
        super(npc, player, action);
    }
}
