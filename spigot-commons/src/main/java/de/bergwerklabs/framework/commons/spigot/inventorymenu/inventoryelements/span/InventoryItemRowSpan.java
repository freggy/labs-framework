package de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements.span;

import de.bergwerklabs.framework.commons.spigot.inventorymenu.InventoryItem;
import org.apache.commons.lang.Validate;
import org.bukkit.inventory.Inventory;

/**
 * Created by Yannic Rieger on 12.04.2017.
 * <p> Class which fills an inventory row by row from specified start to end slot. </p>
 * <p> Form more information see documentation. </p>
 * @author Yannic Rieger
 */
public class InventoryItemRowSpan extends InventoryItemSpanBase {

    /**
     * @param startSlot Slot where the span starts.
     * @param endSlot   Slot where the span ends.
     * @param item      InventoryItem that will be placed.
     */
    public InventoryItemRowSpan(int startSlot, int endSlot, InventoryItem item) {
        super(startSlot, endSlot, item);
    }

    @Override
    public void render(Inventory inventory) {
        Validate.notNull(inventory, "Parameter 'inventory' cannot be null");
        for (int slot = this.getStartSlot(); slot < this.getEndSlot() + 1; slot++) {
            this.placeItem(slot);
        }
    }
}

