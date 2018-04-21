package de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements;

import de.bergwerklabs.framework.commons.spigot.general.update.Updatable;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.InventoryItem;
import java.util.ArrayList;
import org.bukkit.inventory.Inventory;

/**
 * Created by Yannic Rieger on 12.04.2017.
 *
 * <p>Base class for every InventoryMenuElement
 *
 * @author Yannic Rieger
 */
public abstract class InventoryMenuElement implements Updatable {

  protected ArrayList<InventoryItem> items = new ArrayList<>();

  /** Gets a list containing all child items. */
  public ArrayList<InventoryItem> getItems() {
    return this.items;
  }

  /** Places the items in the inventory. */
  public abstract void render(Inventory inventory);
}
