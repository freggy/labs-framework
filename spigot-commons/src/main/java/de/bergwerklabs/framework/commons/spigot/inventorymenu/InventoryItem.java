package de.bergwerklabs.framework.commons.spigot.inventorymenu;

import de.bergwerklabs.framework.commons.spigot.inventorymenu.method.OnClickMethod;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.method.UpdateMethod;
import de.bergwerklabs.framework.commons.spigot.general.update.Updatable;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

/**
 * Created by Yannic Rieger on 12.04.2017.
 * <p> Class representing an item in a InventoryMenu. </p>
 * @author Yannic Rieger
 * @see <a href=https://hackmd.io/GbAsAYGNVBWBaAHOARgZnqAbIx8CGA7FPAIzCmT6y6wBMAJlkA==> Documentation </a>
 */
public class InventoryItem implements Updatable {

    /**
     * Gets the slot the item is placed in.
     */
    public Integer getSlot() {
        return slot;
    }

    /**
     * Gets the corresponding ItemStack object.
     */
    public ItemStack getItemStack() {
        return itemStack.clone();
    }

    /**
     * Method to invoke when the item is clicked.
     */
    public OnClickMethod getOnClick() {
        return onClick;
    }

    /**
     * Method which Updates the ItemStack
     */
    public UpdateMethod getUpdateMethod() {
        return update;
    }

    /**
     * Gets the inventory associated with this item.
     */
    public Inventory getAssociatedInventory() { return associatedInventory; }

    /**
     * Value indicating whether or not the item is updatable.
     */
    public boolean isUpdatable() {
        return updatable;
    }

    /**
     * Value indicating whether or not the item is a child element.
     */
    public boolean isChild() {
        return isChild;
    }

    /**
     * Returns a value that indicates whether or not the object has been updated.
     */
    public boolean isUpdated() {
        return updated;
    }

    private Integer slot;
    private UpdateMethod<ItemStack> update;
    private OnClickMethod onClick;
    private ItemStack itemStack;
    private Inventory associatedInventory;
    private boolean updatable = false, isChild, updated = false;

    /**
     * @param slot      0 based index where the item will be placed in the inventory.
     * @param itemStack ItemStack representing the item.
     * @param onClick   Method that will be invoked when the item gets clicked on.
     */
    public InventoryItem(Integer slot, ItemStack itemStack, OnClickMethod onClick, UpdateMethod<ItemStack> update, Inventory associatedInventory, boolean isChild) {
        this.slot                = slot;
        this.onClick             = onClick;
        this.update              = update;
        this.associatedInventory = associatedInventory;
        this.isChild             = isChild;

        /*
        if (update != null) {
            try {
                this.itemStack = update.invoke();
                this.updatable = true;

                //if (update.getInterval() != -1 && !this.isChild)
                    //TaskManager.registerNewUpdatable(update.getInterval(), this);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else this.itemStack = itemStack; */
    }

    /**
     * Places the item in the specified slot.
     * @param slot Slot to place the item in.
     */
    public void place(int slot) {
        if (this.slot == null) this.slot = slot;
        this.associatedInventory.setItem(slot, this.itemStack);
    }

    @Override
    public void update() {
        /*
        try {
            this.itemStack = this.update.invoke();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        this.place(this.slot); */
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof InventoryItem) {
            InventoryItem other = (InventoryItem)o;
            return this.isChild == other.isChild && this.associatedInventory.hashCode() == other.associatedInventory.hashCode() &&
                   this.itemStack.equals(other.itemStack);
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.associatedInventory, this.isChild, this.itemStack, this.onClick, this.updatable, this.update);
    }
}
