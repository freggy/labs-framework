package de.bergwerklabs.framework.commons.spigot.inventorymenu.version.V1_0;

import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.spigot.general.LabsController;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.method.OnClickMethod;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.method.OnPreprocessMethod;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.method.UpdateMethod;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.InventoryItem;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.InventoryMenu;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements.InventoryItemRect;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements.span.InventoryItemColumnSpan;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.inventoryelements.span.InventoryItemRowSpan;
import de.bergwerklabs.framework.commons.spigot.item.ItemStackUtil;
import de.bergwerklabs.framework.commons.spigot.item.PlayerHead;
import de.bergwerklabs.framework.commons.spigot.item.enchantment.EnchantedBookWrapper;
import de.bergwerklabs.framework.commons.spigot.json.JsonUtil;
import de.bergwerklabs.framework.commons.spigot.json.version.Deserializer;
import de.bergwerklabs.framework.commons.spigot.json.version.VersionedJsonReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by Yannic Rieger on 12.04.2017.
 * <p> Class for deserializing scheme version 1.0 </p>
 * @author Yannic Rieger
 */
public class InventoryMenuDeserializerV1_0 implements Deserializer<InventoryMenu> {

    private LabsController controller = null;
    private OnPreprocessMethod onPreprocessMethod;

    @Override
    public InventoryMenu deserialize(JsonObject json) {
        if (!json.has("class")) throw new IllegalStateException("No controller found.");

        controller = VersionedJsonReflectionUtil.controllerFromJson(json);

        if (json.has("on-preprocess"))
            this.onPreprocessMethod = OnPreprocessMethod.fromJson(json.get("on-preprocess").getAsJsonObject(), controller);

        ArrayList<InventoryItem>           items       = new ArrayList<>();
        ArrayList<InventoryItemRowSpan>    rowSpans    = new ArrayList<>();
        ArrayList<InventoryItemColumnSpan> columnSpans = new ArrayList<>();
        ArrayList<InventoryItemRect>       rects       = new ArrayList<>();

        String title = json.get("title").getAsString();
        int size = json.get("size").getAsInt();

        Inventory inventory = Bukkit.createInventory(null, size, title);

        if (json.has("item-row-spans"))
            json.get("item-row-spans").getAsJsonArray().iterator().forEachRemaining(span -> rowSpans.add(this.createItemRowSpan(span.getAsJsonObject(), controller, inventory)));

        if (json.has("item-column-spans"))
            json.get("item-column-spans").getAsJsonArray().iterator().forEachRemaining(span -> columnSpans.add(this.createItemColumnSpan(span.getAsJsonObject(), controller, inventory)));

        if (json.has("item-rects"))
            json.get("item-rects").getAsJsonArray().iterator().forEachRemaining(rect -> rects.add(this.createItemRect(rect.getAsJsonObject(), controller, inventory)));

        if (json.has("items"))
            JsonUtil.jsonArrayToJsonObjectList(json.get("items").getAsJsonArray()).forEach(item -> items.add(this.createInventoryItemFromJson(item, controller, inventory, false)));

        return new InventoryMenu(inventory, json.get("version").getAsString(), json.get("id").getAsString(), controller, onPreprocessMethod, items, rects, rowSpans, columnSpans);
    }

    /**
     * Creates an InventoryItemRect from JSON.
     * @param json JsonObject representing an InventoryItemRect.
     */
    private InventoryItemRect createItemRect(JsonObject json, LabsController controller, Inventory inventory) {
        InventoryItem borderItem = null, fillItem = null;

        if (json.has("border-item"))
            borderItem = createInventoryItemFromJson(json.get("border-item").getAsJsonObject(), controller, inventory, true);

        if (json.has("fill-item"))
            fillItem = createInventoryItemFromJson(json.get("fill-item").getAsJsonObject(), controller, inventory, true);

        return new InventoryItemRect(json.get("top-left-slot").getAsInt(), json.get("bottom-right-slot").getAsInt(), borderItem, fillItem);
    }

    /**
     * Creates an InventoryItemRowSpan from JSON.
     * @param json JsonObject representing an InventoryItemRowSpan.
     */
    private InventoryItemRowSpan createItemRowSpan(JsonObject json, LabsController controller, Inventory inventory) {
        return new InventoryItemRowSpan(json.get("start-slot").getAsInt(), json.get("end-slot").getAsInt(), this.createInventoryItemFromJson(json, controller, inventory, true));
    }

    /**
     * Creates an InventoryItemColumnSpan from JSON.
     * @param json JsonObject representing an InventoryItemColumnSpan.
     */
    private InventoryItemColumnSpan createItemColumnSpan(JsonObject json, LabsController controller, Inventory inventory) {
        return new InventoryItemColumnSpan(json.get("start-slot").getAsInt(), json.get("end-slot").getAsInt(), this.createInventoryItemFromJson(json, controller, inventory, true));
    }

    /**
     * Creates an InventoryItems form JSON.
     * @param item JsonObject representing the InventoryItem.
     */
    private InventoryItem createInventoryItemFromJson(JsonObject item, LabsController controller, Inventory inventory, boolean isChild) {

        OnClickMethod onClick = null;
        UpdateMethod update = null;
        ItemStack itemStack = null;

        if (item.has("on-click"))
            onClick = OnClickMethod.fromJson(item.get("on-click").getAsJsonObject(), controller);

        if (item.has("update"))
            update = UpdateMethod.fromJson(item.get("update"), controller);

        if (item.has("item"))
            itemStack = ItemStackUtil.createItemStackFromJson(item.get("item").getAsJsonObject());
        else if (item.has("head")) {
            itemStack = PlayerHead.fromJson(item.get("head").getAsJsonObject()).getItem();
        }
        else if (item.has("potion")) {
            itemStack = ItemStackUtil.potionAsItemStackFromJson(item.get("potion").getAsJsonObject());
        }
        else if (item.has("enchanted-book")) {
            itemStack = EnchantedBookWrapper.fromJson(item.get("enchanted-book").getAsJsonObject()).getBook();
        }

        // slot can be optional
        if (item.has("slot")) {
            return new InventoryItem(item.get("slot").getAsInt(), itemStack, onClick, update, inventory, isChild);
        }
        else
            return new InventoryItem(null, itemStack, onClick, update, inventory, isChild);
    }
}

