package de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements.span;

import de.bergwerklabs.framework.commons.spigot.inventorymenu.InventoryItem;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements.InventoryMenuElement;
import org.apache.commons.lang.Validate;

/**
 * Created by Yannic Rieger on 12.04.2017.
 *
 * <p>Base class for the item span gui elements.
 *
 * @author Yannic Rieger
 */
public abstract class InventoryItemSpanBase extends InventoryMenuElement {

  private int startSlot, endSlot;
  private InventoryItem item;
  private boolean updated = false;

  /**
   * @param startSlot Slot where the span starts.
   * @param endSlot Slot where the span ends.
   * @param item InventoryItem that will be placed.
   */
  public InventoryItemSpanBase(int startSlot, int endSlot, InventoryItem item) {
    Validate.notNull(item, "Parameter 'item' cannot be null");
    this.startSlot = startSlot;
    this.endSlot = endSlot;
    this.item = item;

    // if (item.isUpdatable() && item.getUpdateMethod().getInterval() != -1)
    // TaskManager.registerNewUpdatable(this.item.getUpdateMethod().getInterval(), this);
  }

  /** Gets the corresponding InventoryMenuItem. */
  public InventoryItem getItem() {
    return item;
  }

  /** Gets the start slot. */
  public int getStartSlot() {
    return startSlot;
  }

  /** Gets the end slot. */
  public int getEndSlot() {
    return endSlot;
  }

  @Override
  public void update() {
    this.items.forEach(InventoryItem::update);
    this.updated = true;
  }

  public boolean isUpdated() {
    return updated;
  }

  protected void placeItem(int slot) {
    InventoryItem template = this.getItem();

    // The template slot is null
    if (template.getSlot() == null) {
      template.place(slot);
      this.items.add(template);
    } else {
      // Create a new Item to register it
      InventoryItem copy =
          new InventoryItem(
              slot,
              template.getItemStack(),
              template.getOnClick(),
              template.getUpdateMethod(),
              template.getAssociatedInventory(),
              template.isChild());
      this.items.add(copy);
      copy.place(slot);
    }
  }
}
