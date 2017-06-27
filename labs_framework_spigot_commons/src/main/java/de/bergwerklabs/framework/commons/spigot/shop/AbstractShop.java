package de.bergwerklabs.framework.commons.spigot.shop;

import de.bergwerklabs.framework.commons.spigot.inventorymenu.InventoryMenu;
import de.bergwerklabs.framework.commons.spigot.json.version.Versionable;
import de.bergwerklabs.framework.commons.spigot.general.Identifiable;

/**
 * Created by Yannic Rieger on 28.04.2017.
 * <p> Base class for shops </p>
 * @author Yannic Rieger
 */
abstract class AbstractShop implements Versionable, Identifiable {

    /**
     * InventoryMenu that gets displayed when interacting with a shop.
     */
    public InventoryMenu getInventoryMenu() {
        return inventoryMenu;
    }

    /**
     * Gets the version of the shop deserializer/serializer
     */
    public String getVersion() { return this.version; }

    /**
     * Gets the id of the shop
     */
    public String getId() { return this.id; }

    protected InventoryMenu inventoryMenu;
    protected String version, id;

    /**
     * @param inventoryMenu InventoryMenu that gets displayed when interacting with a shop.
     * @param version version of the shop.
     * @param id Id of the shop.
     */
    AbstractShop(InventoryMenu inventoryMenu, String version, String id) {
        this.inventoryMenu = inventoryMenu;
        this.version       = version;
        this.id            = id;
    }
}
