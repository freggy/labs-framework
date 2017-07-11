package de.bergwerklabs.framework.commons.spigot.shop.json.version.V1_0;

import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.spigot.json.version.Deserializer;
import de.bergwerklabs.framework.commons.spigot.shop.NPCShop;
import de.bergwerklabs.framework.commons.spigot.inventorymenu.InventoryMenuManager;
import de.bergwerklabs.framework.commons.spigot.npc.NpcUtil;

/**
 * Created by Yannic Rieger on 29.04.2017.
 * <p> Deserializer for version 1.0 NPC shops </p>
 * @author Yannic Rieger
 */
public class NpcShopDeserializerV1_0 implements Deserializer<NPCShop> {

    @Override
    public NPCShop deserialize(JsonObject json) {
        return new NPCShop(InventoryMenuManager.getInventoryMenus().get(json.get("inventory").getAsString()),
                           json.get("version").getAsString(),
                           json.get("id").getAsString(),
                           NpcUtil.getNpcFromJson(json.get("npc").getAsJsonObject()));
    }
}
