package de.bergwerklabs.framework.nick.api.event;

import com.sun.jna.platform.win32.NTSecApi;
import de.bergwerklabs.framework.commons.spigot.general.LabsEvent;
import de.bergwerklabs.framework.nick.api.NickApi;
import de.bergwerklabs.framework.nick.api.NickInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Created by Yannic Rieger on 03.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class NickEvent extends LabsEvent {

    /**
     *
     */
    public NickInfo getInfo() {
        return info;
    }

    /**
     *
     */
    public Player getPlayer() {
        return player;
    }

    /**
     *
     */
    public NickAction getAction() {
        return action;
    }

    private NickInfo info;
    private Player player;
    private NickAction action;

    public NickEvent(Player player, NickInfo info, NickAction action) {
        this.player = player;
        this.info   = info;
        this.action =  action;
    }
}
