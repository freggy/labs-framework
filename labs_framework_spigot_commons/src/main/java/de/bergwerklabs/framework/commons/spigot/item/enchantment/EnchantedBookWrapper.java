package de.bergwerklabs.framework.commons.spigot.item.enchantment;

import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.spigot.item.ItemStackUtil;
import de.bergwerklabs.framework.commons.spigot.json.JsonUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by Yannic Rieger on 02.05.2017.
 * <p>  </p>
 * @author Yannic Rieger
 */
public class EnchantedBookWrapper {

    /**
     *
     * @return
     */
    public ItemStack getBook() {
        return book;
    }

    private ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);

    public EnchantedBookWrapper() {}

    /**
     *
     * @param enchantments
     */
    public EnchantedBookWrapper(List<EnchantmentWrapper> enchantments) {
        EnchantedBookBuilder builder = new EnchantedBookBuilder();
        enchantments.forEach(enchantment -> builder.addEnchant(enchantment.getEnchantment(), enchantment.getLevel(), enchantment.isIgnoreRestriction()));
        this.book = builder.create().getBook();
    }


    /**
     *
     * @param json
     * @return
     */
    public static EnchantedBookWrapper fromJson(JsonObject json) {
        EnchantedBookBuilder builder = new EnchantedBookBuilder();

        JsonUtil.jsonArrayToJsonObjectList(json.get("enchantments").getAsJsonArray()).forEach(jsonEnchantment -> {
            EnchantmentWrapper wrapper = EnchantmentWrapper.fromJson(jsonEnchantment);
            builder.addEnchant(wrapper.getEnchantment(), wrapper.getLevel(), wrapper.isIgnoreRestriction());
        });

        ItemMeta meta = ItemStackUtil.createItemStackFromJson(json).getItemMeta();
        EnchantedBookWrapper book = builder.create();

        EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta)book.getBook().getItemMeta();
        bookMeta.setDisplayName(meta.getDisplayName());
        bookMeta.setLore(meta.getLore());
        book.getBook().setItemMeta(bookMeta);

        return book;
    }
}
