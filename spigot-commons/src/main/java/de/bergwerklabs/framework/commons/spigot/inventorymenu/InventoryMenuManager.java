package de.bergwerklabs.framework.commons.spigot.inventorymenu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yannic Rieger on 02.05.2017.
 *
 * <p>Class manages InventoryMenus.
 *
 * @author Yannic Rieger
 */
public class InventoryMenuManager {

  static HashMap<String, InventoryMenu> menus = new HashMap<>();

  /** Contains every created InventoryMenu. This map is unmodifiable. */
  public static Map<String, InventoryMenu> getInventoryMenus() {
    return Collections.unmodifiableMap(menus);
  }
}
