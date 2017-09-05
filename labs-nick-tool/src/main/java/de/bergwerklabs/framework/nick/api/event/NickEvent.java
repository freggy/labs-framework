package de.bergwerklabs.framework.nick.api.event;

import de.bergwerklabs.framework.commons.spigot.general.LabsEvent;
import de.bergwerklabs.framework.nick.api.NickInfo;
import org.bukkit.entity.Player;

/**
 * Created by Yannic Rieger on 03.09.2017.
 * <p>
 * Event that gets fired when a player either nicks himself or removes the nick.
 *
 * @author Yannic Rieger
 */
public class NickEvent extends LabsEvent {

    /**
     * Gets the information about the nicked player.
     */
    public NickInfo getInfo() {
        return info;
    }

    /**
     * Gets the {@link Player} object that fired the {@link NickEvent}.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the {@link NickAction} associated with this event.
     */
    public NickAction getAction() {
        return action;
    }

    private NickInfo info;
    private Player player;
    private NickAction action;

    /**
     * @param player The {@link Player} object that fired the {@link NickEvent}.
     * @param info   The information about the nicked player.
     * @param action The {@link NickAction} associated with this event.
     */
    public NickEvent(Player player, NickInfo info, NickAction action) {
        this.player = player;
        this.info   = info;
        this.action =  action;
    }
}
