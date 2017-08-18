package de.bergwerklabs.framework.commons.spigot.item;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;
import de.bergwerklabs.framework.commons.spigot.item.enchantment.EnchantmentWrapper;
import de.bergwerklabs.framework.commons.spigot.json.JsonUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * Created by Yannic Rieger on 13.04.2017.
 * <p>
 * Class for creating ItemStacks
 * @author Yannic Rieger
 */
public class ItemStackUtil {

    /**
     * Sets the display name of an ItemStack.
     *
     * @param item ItemStack where the display name will be set
     * @param name Name to be set.
     */
    public static void setName(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }

    /**
     * Sets the lore of the given item.
     *
     * @param item ItemStack where the lore will be set.
     * @param lore Lore to set.
     */
    public static void setLore(ItemStack item, String... lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
    }

    /**
     * Sets the lore of the given item.
     *
     * @param item ItemStack where the lore will be set.
     * @param lore Lore to set.
     */
    public static void setLore(ItemStack item, List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
    }


    /**
     * Adds {@link ItemFlag}s to the {@link ItemStack}.
     *
     * @param item {@link ItemStack} where to set the flags.
     * @param flags {@link ItemFlag}s to add.
     */
    public static void addItemFlags(ItemStack item, ItemFlag... flags) {
        item.getItemMeta().addItemFlags(flags);
    }

    /**
     * Sets the skull owner on the given item.
     * @param owner
     * @param item
     */
    public static void setSkullOwner(String owner, ItemStack item) {
        SkullMeta skullMeta = (SkullMeta)item.getItemMeta();
        skullMeta.setOwner(owner);
        item.setItemMeta(skullMeta);
    }

    /**
     * Applies a custom texture to the skull.
     *
     * @param url  Representing the url where the texture is stored.
     */
    public static void applyCustomHeadTexture(String url, ItemStack item) {

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        PropertyMap propertyMap = profile.getProperties();

        if (propertyMap == null)
            throw new IllegalStateException("Profile doesn't contain a property map");

        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        propertyMap.put("textures", new Property("textures", new String(encodedData)));

        ItemMeta headMeta = item.getItemMeta();
        Class<?> headMetaClass = headMeta.getClass();

        try {
            LabsReflection.getField(headMetaClass, "profile").set(headMeta, profile);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        item.setItemMeta(headMeta);
    }

    /**
     * Checks whether or not the given item is a player head or skull.
     * @param item Item to check
     * @return Value indicating whether or not the given item is a skull
     */
    public static boolean isHead(ItemStack item) {
        return item.getItemMeta() instanceof SkullMeta;
    }

    /**
     * Creates an ItemStack out of an JsonObject.
     * @param json JsonObject representing an InventoryItem.
     * @return ItemStack that has been created from JSON.
     */
    public static ItemStack createItemStackFromJson(JsonObject json) {
        ItemStackBuilder builder = new ItemStackBuilder();

        if (json.has("material"))
            builder.setType(Material.valueOf(json.get("material").getAsString()));

        if (json.has("name"))
            builder.setName(json.get("name").getAsString());

        if (json.has("amount"))
            builder.setAmount(json.get("amount").getAsInt());

        if (json.has("damage"))
            builder.setDamage(json.get("damage").getAsShort());

        if (json.has("lore"))
            builder.setLore(JsonUtil.jsonArrayToStringList(json.get("lore").getAsJsonArray()));

        if (json.has("data"))
            builder.setData(json.get("data").getAsByte());

        if (json.has("enchantments")) {
            EnchantmentWrapper.enchantmentsFromJson(json.get("enchantments").getAsJsonArray()).forEach(enchantment -> {
                builder.addEnchantment(enchantment.getEnchantment(), enchantment.getLevel());
            });
        }

        return builder.create();
    }

    // put the below stuff in a PotionWrapper class?

    /**
     * Creates a potion ItemStack from JSON.
     * @param json JsonObject representing the potion.
     * @return Potion as ItemStack created from JSON.
     */
    public static ItemStack potionAsItemStackFromJson(JsonObject json) {
        ItemStack item = potionFromJson(json).toItemStack(1);
        ItemStack itemStackForMeta = createItemStackFromJson(json);

        if (itemStackForMeta.hasItemMeta())
            item.setItemMeta(itemStackForMeta.getItemMeta());
        return item;
    }

    /**
     * Creates a potion from json.
     * @param json JsonObject representing the potion.
     * @return Potion object created from JSON.
     */
    public static Potion potionFromJson(JsonObject json) {
        PotionBuilder builder = new PotionBuilder(PotionType.valueOf(json.get("type").getAsString()));

        if (json.has("extended-duration"))
            builder.setExtend(json.get("extended-duration").getAsBoolean());

        if (json.has("splash"))
            builder.setSplash(json.get("splash").getAsBoolean());

        if (json.has("level"))
            builder.setLvl(json.get("level").getAsInt());

        return builder.create();
    }

    /**
     * Checks whether two ItemStacks are equal.
     *
     * @param a first ItemStack.
     * @param b second ItemStack.
     * @return whether the two ItemStacks are equal.
     */
    public static boolean equals(ItemStack a, ItemStack b) {
        return a.getType() == b.getType()
                && a.getData().equals(b.getData())
                && a.getItemMeta().getDisplayName().equals(b.getItemMeta().getDisplayName());
    }
}