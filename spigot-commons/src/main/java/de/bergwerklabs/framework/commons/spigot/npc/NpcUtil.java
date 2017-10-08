package de.bergwerklabs.framework.commons.spigot.npc;

import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.spigot.item.ItemStackUtil;
import de.bergwerklabs.framework.commons.spigot.location.LocationUtil;
import org.bukkit.Location;

/**
 * Created by Yannic Rieger on 01.05.2017.
 * <p> Util class providing useful methods for working with NPCs </p>
 * @author Yannic Rieger
 */
public class NpcUtil {

//    // Maybe use version mechanism here as well?
//
//    /**
//     * Creates an NPC from JSON.
//     * @param json JsonObject representing the npc.
//     * @return NPC frm JSON.
//     */
//    public static NPC getNpcFromJson(JsonObject json) {
//
//        String tile = "", subtitle = "";
//        boolean displayTitle = false, displaySubtitle = false;
//        NpcBuilder builder = null;
//
//        if (json.has("title")) {
//            tile = json.get("title").getAsString();
//            displayTitle = true;
//        }
//
//        if (json.has("subtitle")) {
//            displaySubtitle = true;
//            subtitle = json.get("subtitle").getAsString();
//        }
//
//        if (json.has("display-title"))
//            displayTitle = json.get("display-title").getAsBoolean();
//
//        if (json.has("display-subtitle"))
//            displaySubtitle = json.get("display-subtitle").getAsBoolean();
//
//        Location location = LocationUtil.locationFromJson(json.get("location").getAsJsonObject());
//
//        try {
//            builder = new NpcBuilder(tile, subtitle, displayTitle, displaySubtitle, location);
//
//            if (json.has("armor"))
//                builder.setArmor(ItemStackUtil.createItemStackFromJson(json.get("armor").getAsJsonObject()));
//
//            if (json.has("skin"))
//                builder.setSkin(new PlayerSkin(json.get("skin").getAsString(), "")); // TODO: change ""
//
//            if (json.has("leggings"))
//                builder.setLeggins(ItemStackUtil.createItemStackFromJson(json.get("leggings").getAsJsonObject()));
//
//            if (json.has("helmet"))
//                builder.setHelmet(ItemStackUtil.createItemStackFromJson(json.get("helmet").getAsJsonObject()));
//
//            if (json.has("boots"))
//                builder.setShoes(ItemStackUtil.createItemStackFromJson(json.get("helmet").getAsJsonObject()));
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return builder.create();
//    }
}
