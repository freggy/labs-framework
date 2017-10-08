package de.bergwerklabs.framework.commons.spigot.shop;

import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.InventoryMenu;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Yannic Rieger on 28.04.2017.
 * <p> Represents a npc shop </p>
 * @author Yannic Rieger
 */
public class NPCShop extends AbstractShop implements Listener {

//    /**
//     * Gets the NPC representing the shop.
//     */
//    public NPC getNPC() { return npc; }
//
//    private NPC npc;
//
//    /**
//     * @param inventoryMenu InventoryMenu that gets displayed when interacting with a shop.
//     * @param npc NPC which represents the shop physically
//     * @param version Shop version.
//     */
//    public NPCShop(InventoryMenu inventoryMenu, String version, String id, NPC npc)  {
//        super(inventoryMenu, version, id);
//        this.inventoryMenu = inventoryMenu;
//        this.npc           = npc;
//        Bukkit.getServer().getPluginManager().registerEvents(this, SpigotCommons.getInstance());
//    }
//
//    /**
//     * Spawns shop.
//     */
//    public void spawnShop() {
//        try {
//            this.npc.spawn();
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    @EventHandler
//    private void onShopInteract(NPCInteractEvent e) {
//        if (e.getInteracted().getLocation().equals(this.npc.getLocation()) && e.getAction().equalsIgnoreCase("INTERACT_AT")) {
//            e.getPlayer().openInventory(this.getInventoryMenu().getInventory());
//        }
//    }
}
