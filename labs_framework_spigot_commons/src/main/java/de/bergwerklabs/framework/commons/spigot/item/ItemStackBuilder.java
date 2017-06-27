package de.bergwerklabs.framework.commons.spigot.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by Yannic Rieger on 26.04.2017.
 * <p> Builder class for creating an ItemStack. </p>
 * @author Yannic Rieger
 */
public class ItemStackBuilder {

    private ItemStack item;

    public ItemStackBuilder() {
        this.item = new ItemStack(Material.WOOL); // Random default value
    }

    /**
     * @param material Material that the item should consist of.
     */
    public ItemStackBuilder(Material material) {
        item = new ItemStack(material);
    }

    /**
     * Sets the damage of an item.
     * @param damage Damage of the item.
     * @return another ItemStackBuilder
     */
    public ItemStackBuilder setDamage(short damage) {
        item.setDurability(damage);
        return this;
    }

    /**
     * Sets the name of an item.
     * @param name Name of the item.
     * @return another ItemStackBuilder
     */
    public ItemStackBuilder setName(String name) {
        ItemStackUtil.setName(this.item, name);
        return this;
    }

    /**
     * Sets the type of the ItemStack
     * @param type Material to set
     * @return another ItemStackBuilder
     */
    public ItemStackBuilder setType(Material type) {
        this.item.setType(type);
        return this;
    }

    /**
     * Sets the amount.
     * @param amount Amount of ItemStack.
     * @return another ItemStackBuilder
     */
    public ItemStackBuilder setAmount(int amount) {
        if (amount > 64) throw new IllegalArgumentException("Amount cannot be greater than 64.");
        this.item.setAmount(amount);
        return this;
    }

    /**
     * Sets additional data.
     * @param data Data to set.
     * @return another ItemStackBuilder
     */
    public ItemStackBuilder setData(byte data) {
        ItemStack itemStack = new ItemStack(this.item.getType(), this.item.getAmount(), data);
        itemStack.setItemMeta(this.item.getItemMeta());
        this.item = itemStack;
        return this;
    }

    /**
     * Adds enchantments to the ItemStack
     * @return another ItemStackBuilder
     */
    public ItemStackBuilder addEnchantment(Enchantment enchantment, Integer level) {
        this.item.getItemMeta().addEnchant(enchantment, level, true);
        return this;
    }

    /**
     * Sets the lore of the ItemStack.
     * @param lore Lore of the item.
     * @return another ItemStackBuilder
     */
    public ItemStackBuilder setLore(List<String> lore) {
        ItemStackUtil.setLore(this.item, lore);
        return this;
    }

    /**
     * Creates the final ItemStack.
     * @return ItemStack that has been built.
     */
    public ItemStack create() {
        return this.item.clone();
    }
}
