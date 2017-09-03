package de.bergwerklabs.framework.commons.spigot.general;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Yannic Rieger on 15.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class LabsEvent extends Event {

    /**
     * Gets the list of all handlers.
     */
    public static HandlerList getHandlerList() { return handlerList; }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    protected static HandlerList handlerList = new HandlerList();
}
