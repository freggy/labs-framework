package de.bergwerklabs.framework.commons.spigot.inventorymenu.event;

import de.bergwerklabs.framework.commons.spigot.inventorymenu.InventoryItem;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Set;

/**
 * Created by Yannic Rieger on 03.05.2017.
 * <p>  </p>
 * @author Yannic Rieger
 */
public class InventoryItemClickEvent {

    /**
     *
     */
    public Set<String> getParameter() {
        return parameter;
    }

    /**
     *
     */
    public InventoryItem getItem() {
        return item;
    }

    /**
     *
     */
    public InventoryClickEvent getEvent() {
        return event;
    }

    private Set<String> parameter;
    private InventoryItem item;
    private InventoryClickEvent event;

    /**
     * @param parameter
     * @param item
     * @param event
     */
    public InventoryItemClickEvent(Set<String> parameter, InventoryItem item, InventoryClickEvent event) {
        this.parameter = parameter;
        this.item = item;
        this.event = event;
    }
}
