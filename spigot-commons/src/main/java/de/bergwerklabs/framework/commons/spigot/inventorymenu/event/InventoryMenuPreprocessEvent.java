package de.bergwerklabs.framework.commons.spigot.inventorymenu.event;

import de.bergwerklabs.framework.commons.spigot.inventorymenu.InventoryItem;
import java.util.Set;
import org.bukkit.entity.Player;

/**
 * Created by Yannic Rieger on 08.01.2018.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public class InventoryMenuPreprocessEvent {

  private Set<InventoryItem> inventoryItems;
  private Player player;

  public InventoryMenuPreprocessEvent(Set<InventoryItem> items, Player player) {
    this.inventoryItems = items;
    this.player = player;
  }

  public Set<InventoryItem> getInventoryItems() {
    return inventoryItems;
  }

  public Player getPlayer() {
    return player;
  }
}
