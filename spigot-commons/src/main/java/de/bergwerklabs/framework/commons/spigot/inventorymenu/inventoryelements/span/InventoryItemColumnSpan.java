package de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements.span;

import de.bergwerklabs.framework.commons.spigot.inventorymenu.InventoryItem;
import org.apache.commons.lang.Validate;
import org.bukkit.inventory.Inventory;

/**
 * Created by Yannic Rieger on 12.04.2017.
 *
 * <p>Class which fills an inventory column by column from given start to end slot.
 *
 * <p>For more information see documentation.
 *
 * @author Yannic Rieger
 */
public class InventoryItemColumnSpan extends InventoryItemSpanBase {

  /**
   * @param startSlot Slot where the span starts.
   * @param endSlot Slot where the span ends.
   * @param item InventoryItem that will be placed.
   */
  public InventoryItemColumnSpan(int startSlot, int endSlot, InventoryItem item) {
    super(startSlot, endSlot, item);
  }

  @Override
  public void render(Inventory inventory) {
    Validate.notNull(inventory, "Parameter 'inventory' cannot be null");
    this.placeItem(this.getStartSlot(), inventory);
  }

  /**
   * Places items one per row.
   *
   * @param slot slot to place
   * @param inventory Inventory to place the item in.
   */
  private void placeItem(int slot, Inventory inventory) {
    this.placeItem(slot);
    if (slot == this.getEndSlot()) return;
    this.placeItem(slot + 9, inventory);
  }
}
