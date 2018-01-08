package de.bergwerklabs.framework.commons.spigot.inventorymenu.event;

import de.bergwerklabs.framework.commons.spigot.inventorymenu.InventoryItem;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * Created by Yannic Rieger on 08.01.2018.
 * <p>
 *
 * @author Yannic Rieger
 */
public class InventoryMenuPreprocessEvent {

    public Set<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }

    public Player getPlayer() {
        return player;
    }

    private Set<InventoryItem> inventoryItems;
    private Player player;

    public InventoryMenuPreprocessEvent(Set<InventoryItem> items, Player player) {
        this.inventoryItems = items;
        this.player = player;
    }
}
