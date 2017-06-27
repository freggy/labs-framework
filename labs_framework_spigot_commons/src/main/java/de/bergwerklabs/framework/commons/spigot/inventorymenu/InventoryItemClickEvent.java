package de.bergwerklabs.framework.commons.spigot.inventorymenu;

import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

/**
 * Created by Yannic Rieger on 03.05.2017.
 * <p>  </p>
 * @author Yannic Rieger
 */
public class InventoryItemClickEvent {

    /**
     *
     */
    public List<Object> getParameter() {
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

    private List<Object> parameter;
    private InventoryItem item;
    private InventoryClickEvent event;

    /**
     * @param parameter
     * @param item
     * @param event
     */
    public InventoryItemClickEvent(List<Object> parameter, InventoryItem item, InventoryClickEvent event) {
        this.parameter = parameter;
        this.item = item;
        this.event = event;
    }
}
