package de.bergwerklabs.framework.commons.spigot.inventorymenu.event;

import de.bergwerklabs.framework.commons.spigot.inventorymenu.InventoryItem;
import java.util.Set;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by Yannic Rieger on 03.05.2017.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public class InventoryItemClickEvent {

  private Set<String> parameter;
  private InventoryItem item;
  private InventoryClickEvent event;

  /**
   * @param parameter
   * @param item
   * @param event
   */
  public InventoryItemClickEvent(
      Set<String> parameter, InventoryItem item, InventoryClickEvent event) {
    this.parameter = parameter;
    this.item = item;
    this.event = event;
  }

  /** */
  public Set<String> getParameter() {
    return parameter;
  }

  /** */
  public InventoryItem getItem() {
    return item;
  }

  /** */
  public InventoryClickEvent getEvent() {
    return event;
  }
}
