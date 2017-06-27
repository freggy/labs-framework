package de.bergwerklabs.framework.commons.spigot.shop;

import java.util.HashMap;

/**
 * Created by Yannic Rieger on 01.05.2017.
 * <p> Manages every created NPCShop </p>
 * @author Yannic Rieger
 */
public class NPCShopManager {

    // TODO: make map immutable.

    /**
     * Gets all created shops
     */
    public static HashMap<String, NPCShop> getShops() { return shops; }

    private static HashMap<String, NPCShop> shops = new HashMap<>();
}
