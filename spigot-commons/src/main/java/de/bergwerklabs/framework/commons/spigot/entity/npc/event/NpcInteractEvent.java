package de.bergwerklabs.framework.commons.spigot.entity.npc.event;

import de.bergwerklabs.framework.commons.spigot.general.LabsEvent;
import de.bergwerklabs.framework.commons.spigot.entity.npc.Npc;
import org.bukkit.entity.Player;

/**
 * Created by Yannic Rieger on 21.07.2017.
 * <p> Event that gets called when a {@link Player} interacts with a {@link Npc}.
 *
 * @author Yannic Rieger
 */
public class NpcInteractEvent extends LabsEvent {

    /**
     * Gets the {@link Npc} that the player interacted with.
     */
    public Npc getNpc() {
        return npc;
    }

    /**
     * Gets the {@link Player} that interacted with a {@link Npc}.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the {@link Action}
     */
    public Action getAction() {
        return action;
    }

    private Npc npc;
    private Player player;
    private Action action;

    /**
     * @param npc Gets the {@link Npc} that the player interacted with.
     * @param player Gets the {@link Player} that interacted with a {@link Npc}.
     * @param action Gets the {@link Action}
     */
    public NpcInteractEvent(Npc npc, Player player, Action action) {
        this.npc = npc;
        this.player = player;
        this.action = action;
    }
}
