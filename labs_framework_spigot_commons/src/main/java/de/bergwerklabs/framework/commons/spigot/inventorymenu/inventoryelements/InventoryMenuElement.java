package de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements;

import de.bergwerklabs.framework.commons.spigot.inventorymenu.InventoryItem;
import de.bergwerklabs.framework.commons.spigot.general.update.Updatable;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

/**
 * Created by Yannic Rieger on 12.04.2017.
 * <p> Base class for every InventoryMenuElement </p>
 * @author Yannic Rieger
 * @see <a href=https://hackmd.io/GbAsAYGNVBWBaAHOARgZnqAbIx8CGA7FPAIzCmT6y6wBMAJlkA==> Documentation </a>
 */
public abstract class InventoryMenuElement implements Updatable {

    /**
     * Gets a list containing all child items.
     */
    public ArrayList<InventoryItem> getItems() { return this.items; }

    protected ArrayList<InventoryItem> items = new ArrayList<>();

    /**
     * Places the items in the inventory.
     */
    public abstract void render(Inventory inventory);
}

