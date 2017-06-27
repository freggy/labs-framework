package de.bergwerklabs.framework.commons.spigot.item;

import com.google.gson.JsonObject;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Yannic Rieger on 28.04.2017.
 * <p> Useful wrapper class for Skulls. </p>
 * @author Yannic Rieger
 */
public class PlayerHead {

    /**
     * Owner of the Head
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Gets a value indicating whether or not the texture is a custom one.
     */
    public boolean isCustom() {
        return isCustom;
    }

    /**
     * Gets the display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the skull as an ItemStack.
     */
    public ItemStack getItem() { return item.clone(); }

    private String owner, displayName;
    private boolean isCustom = false;
    private ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);

    /**
     * @param owner Owner of the head.
     * @param displayName Name of the head
     * @param isCustom Gets a value indicating whether or not the texture is a custom one.
     */
    public PlayerHead(String owner, String displayName, boolean isCustom) {
        this.owner        = owner;
        this.displayName  = displayName;
        this.isCustom     = isCustom;

        if (displayName != null) ItemStackUtil.setName(this.item, displayName);

        if (isCustom) ItemStackUtil.applyCustomHeadTexture(owner, this.item);
        else ItemStackUtil.setSkullOwner(owner, this.item);
    }

    /**
     * @param owner Owner of the head.
     * @param isCustom Gets a value indicating whether or not the texture is a custom one.
     * @param meta Metadata that the ItemStack should have.
     */
    public PlayerHead(String owner, boolean isCustom, ItemMeta meta) {
        this.owner = owner;
        this.isCustom = isCustom;
        this.item.setItemMeta(meta);

        try {
            if (!isCustom) {
                ItemStackUtil.setSkullOwner(owner, this.item);
            }
            else {
                ItemStackUtil.applyCustomHeadTexture(owner, this.item);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Creates a PlayerHead from JSON.
     * @param json JsonObject representing the PlayerHead.
     * @return PlayerHead created out of JSON.
     */
    public static PlayerHead fromJson(JsonObject json) {
        ItemStack item = ItemStackUtil.createItemStackFromJson(json); // We just need the ItemMeta
        String owner = json.get("owner").getAsString();

        if (json.has("is-custom"))
           return new PlayerHead(owner, json.get("is-custom").getAsBoolean(), item.getItemMeta());
        else
            return new PlayerHead(owner, false, item.getItemMeta());
    }
}
