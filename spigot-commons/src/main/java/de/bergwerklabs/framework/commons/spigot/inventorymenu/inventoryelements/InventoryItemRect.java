package de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements;

import de.bergwerklabs.framework.commons.spigot.inventorymenu.InventoryItem;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements.span.InventoryItemColumnSpan;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements.span.InventoryItemRowSpan;
import org.apache.commons.lang.Validate;
import org.bukkit.inventory.Inventory;

/**
 * Created by Yannic Rieger on 12.04.2017.
 * <p> Rectangle class for InventoryMenus </p>
 * @author Yannic Rieger
 * @see <a href=https://hackmd.io/GbAsAYGNVBWBaAHOARgZnqAbIx8CGA7FPAIzCmT6y6wBMAJlkA==> Documentation </a>
 */
public class InventoryItemRect extends InventoryMenuElement {

    /**
     * Gets the top left slot of the rectangle.
     */
    public Integer getTopLeftSlot() {
        return topLeftSlot;
    }

    /**
     * Gets the bottom right slot of the rectangle.
     */
    public Integer getBottomRightSlot() {
        return bottomRightSlot;
    }

    /**
     * Item the border consists of.
     */
    public InventoryItem getBorderItem() {
        return borderItem;
    }

    /**
     * Gets the item the inner space of the rectangle contains.
     */
    public InventoryItem getFillItem() {
        return fillItem;
    }

    private int topLeftSlot, bottomRightSlot;
    private InventoryItem borderItem, fillItem;
    private boolean borderOnly = false, full = false, updated = false;
    private final int ROW_LENGTH = 9;
    private InventoryItemColumnSpan left, right;
    private InventoryItemRowSpan top, bottom;

    /**
     * @param topLeftSlot     Top left slot of the rectangle.
     * @param bottomRightSlot Bottom right slot of the rectangle.
     */
    public InventoryItemRect(Integer topLeftSlot, Integer bottomRightSlot, InventoryItem borderItem, InventoryItem fillItem) {
        this.topLeftSlot     = topLeftSlot;
        this.bottomRightSlot = bottomRightSlot;
        this.borderItem      = borderItem;
        this.fillItem        = fillItem;
        this.borderItem      = borderItem;
        this.borderOnly      = this.fillItem == null;
        this.full            = this.fillItem != null && this.borderItem == null;
    }

    @Override
    public void render(Inventory inventory) {
        Validate.notNull(inventory, "Parameter 'inventory' cannot be null");
        if (this.full)
            this.renderFull(inventory);
        else if (this.borderOnly)
            this.renderBorder(inventory);
        else
            this.renderOuterAndInner(inventory);
    }

    @Override
    public void update() {
        this.left.update();
        this.right.update();
        this.top.update();
        this.bottom.update();
        this.updated = true;
    }

    public boolean isUpdated() {
        return updated;
    }

    /**
     * Renders the inner and the outer item.
     * @param inventory org.bukkit.inventory.Inventory
     */
    private void renderOuterAndInner(Inventory inventory) {
        this.renderBorder(inventory);
        this.renderInnerSpace(((this.topLeftSlot / ROW_LENGTH) * ROW_LENGTH) + ROW_LENGTH + 1, inventory);
    }

    /**
     * Renders border.
     * @param inventory org.bukkit.inventory.Inventory
     */
    private void renderBorder(Inventory inventory) {
        this.placeOuterItems(inventory);
    }

    /**
     * Renders a rectangle filled with one item.
     * @param inventory org.bukkit.inventory.Inventory
     */
    private void renderFull(Inventory inventory) {
        int rows = this.bottomRightSlot / ROW_LENGTH + 1;
        for (int row = 0; row < rows; row++) {
            int currentRow = ROW_LENGTH * row;
            InventoryItemRowSpan span = new InventoryItemRowSpan(currentRow, currentRow + 8, this.fillItem);
            items.addAll(span.items);
            span.render(inventory);
        }
    }

    /**
     * Creates a border.
     * @param inventory Inventory the border will be drawn in.
     */
    private void placeOuterItems(Inventory inventory) {
        // The row where the slot is located can be calculated row = slot / 9
        int bottomRow = this.bottomRightSlot / ROW_LENGTH;

        // One interesting thing is that when you divide a slot by 9
        // e.g slot / 9 = row.slot => 19 / 9 = 2.1. So slot 19 is the second slot of row 2.
        // NOTE: the numbers are zero based.
        // The number before the decimal point represents the slot, the number after the row specific slot.
        // In this statement we get the current slot.
        int bottomSlot = new Integer(String.valueOf(String.valueOf(this.topLeftSlot / 9F).charAt(2)));

        // The row of the final slot is calculated by row * ROW_LENGTH.
        // To calculate the row specific slot add the slot.
        int finalBottomSlot = (bottomRow * ROW_LENGTH) + bottomSlot;

        // Same as above for other slot.
        int topRow       = this.topLeftSlot / ROW_LENGTH;
        int topSlot      = new Integer(String.valueOf(String.valueOf(this.bottomRightSlot / 9F).charAt(2)));
        int finalTopSlot = (topRow * ROW_LENGTH) + topSlot;

        // Places items from the top left slot to the row where the bottom right slot is located.
        // This renders the left side of the rectangle.
        this.left = new InventoryItemColumnSpan(this.topLeftSlot, finalBottomSlot, this.borderItem);

        // Places items from the bottom right slot to the row where the top left slot is located.
        // This renders the right side of the rectangle.
        this.right = new InventoryItemColumnSpan(finalTopSlot, this.bottomRightSlot, this.borderItem);

        // Renders the bottom side of the rectangle.
        this.bottom = new InventoryItemRowSpan(finalBottomSlot, this.bottomRightSlot, this.borderItem);

        // Renders the top side of the rectangle.
        this.top = new InventoryItemRowSpan(this.topLeftSlot, finalTopSlot, this.borderItem);

        items.addAll(left.items);
        items.addAll(right.items);
        items.addAll(top.items);
        items.addAll(bottom.items);

        left.render(inventory);
        right.render(inventory);
        top.render(inventory);
        bottom.render(inventory);
    }

    /**
     * Fills the inner space of an ItemRect.
     * @param slot Slot where to begin.
     * @param inventory Inventory to place the items in.
     */
    private void renderInnerSpace(int slot, Inventory inventory) {
        // Calculates the diagonal slot with length 1
        int startSlot = ((slot / ROW_LENGTH) * ROW_LENGTH) + ROW_LENGTH + 1;

        // Bottom is reached
        if (inventory.getItem(startSlot).getType() == borderItem.getItemStack().getType()) return;

        InventoryItemRowSpan span = new InventoryItemRowSpan(startSlot, startSlot + 7, this.fillItem);
        items.addAll(span.items);
        span.render(inventory);
        renderInnerSpace(startSlot, inventory);
    }
}