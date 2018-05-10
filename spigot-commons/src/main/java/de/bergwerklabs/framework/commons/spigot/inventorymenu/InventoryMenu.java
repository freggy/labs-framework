package de.bergwerklabs.framework.commons.spigot.inventorymenu;

import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.general.Identifiable;
import de.bergwerklabs.framework.commons.spigot.general.LabsController;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.event.InventoryItemClickEvent;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.event.InventoryMenuPreprocessEvent;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements.InventoryItemRect;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements.span.InventoryItemColumnSpan;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements.span.InventoryItemRowSpan;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements.span.InventoryItemSpanBase;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.method.OnPreprocessMethod;
import de.bergwerklabs.framework.commons.spigot.item.ItemStackUtil;
import de.bergwerklabs.framework.commons.spigot.json.version.Versionable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Yannic Rieger on 12.04.2017.
 *
 * <p>Represents an inventory GUI.
 *
 * @author Yannic Rieger
 */
public class InventoryMenu implements Listener, Versionable, Identifiable {

  private ArrayList<InventoryItem> items;
  private ArrayList<InventoryItemRect> rects;
  private ArrayList<InventoryItemRowSpan> rowSpans;
  private ArrayList<InventoryItemColumnSpan> columnSpans;
  private OnPreprocessMethod onPreprocess;
  private LabsController controller;
  private Inventory inventory;
  private String version, id;
  private ArrayList<InventoryItem> allItems = new ArrayList<>();
  /**
   * @param inventory Minecraft Inventory.
   * @param items Items contained in this inventory.
   * @param version MinecraftVersion of the inventory json file.
   * @param controller Controller which contains the important methods.
   */
  public InventoryMenu(
      Inventory inventory,
      String version,
      String id,
      LabsController controller,
      OnPreprocessMethod onPreprocess,
      ArrayList<InventoryItem> items,
      ArrayList<InventoryItemRect> rects,
      ArrayList<InventoryItemRowSpan> rowSpans,
      ArrayList<InventoryItemColumnSpan> columnSpans) {
    this.controller = controller;
    this.items = items;
    this.version = version;
    this.inventory = inventory;
    this.rects = rects;
    this.rowSpans = rowSpans;
    this.columnSpans = columnSpans;
    this.id = id;
    this.onPreprocess = onPreprocess;

    InventoryMenuManager.menus.put(this.id, this);
    Bukkit.getServer().getPluginManager().registerEvents(this, SpigotCommons.getInstance());

    // Render order has to be respected.
    if (rects != null && !rects.isEmpty()) {
      rects.forEach(rect -> rect.render(this.inventory));
      rects.forEach(rect -> this.allItems.addAll(rect.getItems()));
    }

    if (rowSpans != null && !rowSpans.isEmpty()) {
      rowSpans.forEach(rowSpan -> rowSpan.render(this.inventory));
      columnSpans.forEach(rowSpan -> this.allItems.addAll(rowSpan.getItems()));
    }

    if (columnSpans != null && !columnSpans.isEmpty()) {
      columnSpans.forEach(columnSpan -> columnSpan.render(this.inventory));
      columnSpans.forEach(columnSpan -> this.allItems.addAll(columnSpan.getItems()));
    }

    if (items != null && !items.isEmpty()) {
      this.allItems.addAll(items);
      items.forEach(item -> this.inventory.setItem(item.getSlot(), item.getItemStack()));
    }
  }

  /** Gets the version of the inventory's json file. */
  public String getVersion() {
    return version;
  }

  /** Gets the id of the menu. */
  public String getId() {
    return this.id;
  }

  /** Gets all the items contained in this InventoryMenu. */
  public ArrayList<InventoryItem> getItems() {
    return items;
  }

  /** Gets the controller for this InventoryMenu. */
  public LabsController getController() {
    return controller;
  }

  /** Gets the org.bukkit.inventory.Inventory */
  public Inventory getInventory() {
    return inventory;
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  private void onItemClick(InventoryClickEvent e) {
    if (e.getClickedInventory() == null
        || Arrays.hashCode(e.getClickedInventory().getContents())
            != Arrays.hashCode(this.inventory.getContents())
        || e.getCurrentItem().getType() == Material.AIR) return;

    if (e.getInventory().getTitle().equals(this.getInventory().getTitle())) {
      this.onPreprocess.invoke(
          new InventoryMenuPreprocessEvent(
              new HashSet<>(this.allItems), (Player) e.getWhoClicked()));
      InventoryItem item;
      this.addItemsToList();
      item = this.searchList(this.allItems, e.getCurrentItem());

      if (item != null && item.getOnClick() != null) {
        try {
          item.getOnClick()
              .invoke(new InventoryItemClickEvent(new ArrayList<>(item.getOnClick().getParams()), item, e));
          e.setCancelled(true);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
      e.setCancelled(true);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof InventoryMenu) {
      InventoryMenu other = (InventoryMenu) o;
      return this.allItems.equals(other.allItems)
          && this.id.equals(other.id)
          && this.controller.equals(other.controller)
          && this.rowSpans.equals(other.rowSpans)
          && this.columnSpans.equals(other.columnSpans)
          && this.rects.equals(other.rects);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        this.allItems, this.columnSpans, this.rowSpans, this.controller, this.rects, this.id);
  }

  // TODO: create extension method for itemstack getHashCode when using kotlin.

  /** Adds items to the items list. Only adds the updated ones. */
  private void addItemsToList() {
    // Add every item to list, in order to find the clicked item
    if (!rects.isEmpty())
      rects
          .stream()
          .filter(InventoryItemRect::isUpdated)
          .forEach(rect -> this.allItems.addAll(rect.getItems()));
    if (!rowSpans.isEmpty())
      rowSpans
          .stream()
          .filter(InventoryItemSpanBase::isUpdated)
          .forEach(rownSpan -> this.allItems.addAll(rownSpan.getItems()));
    if (!columnSpans.isEmpty())
      columnSpans
          .stream()
          .filter(InventoryItemSpanBase::isUpdated)
          .forEach(columnSpan -> this.allItems.addAll(columnSpan.getItems()));
    if (!items.isEmpty())
      items
          .stream()
          .filter(InventoryItem::isUpdated)
          .forEach(invItem -> Collections.addAll(items, invItem));
  }

  /**
   * @param list
   * @param itemStack
   * @return
   */
  private InventoryItem searchList(List<InventoryItem> list, ItemStack itemStack) {
    for (InventoryItem item : list) {
      if (this.matches(item, itemStack)) {
        return item;
      }
    }
    return null;
  }

  /**
   * @param invItem
   * @param item
   * @return
   */
  private boolean matches(InventoryItem invItem, ItemStack item) {
    ItemStack invItemSack = invItem.getItemStack();
    if (ItemStackUtil.isHead(invItemSack)) {
      return invItemSack.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName());
    } else return item.isSimilar(invItem.getItemStack());
  }
}
