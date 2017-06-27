package de.bergwerklabs.framework.commons.spigot.item.enchantment;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

/**
 * Created by Yannic Rieger on 01.05.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class EnchantedBookBuilder {

    private EnchantedBookWrapper book = new EnchantedBookWrapper();

    /**
     *
     * @param type
     * @param level
     * @param ignoreLevelRestriction
     * @return
     */
    public EnchantedBookBuilder addEnchant(Enchantment type, int level, boolean ignoreLevelRestriction) {
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta)this.book.getBook().getItemMeta();
        meta.addEnchant(type, level, ignoreLevelRestriction);
        this.book.getBook().setItemMeta(meta);
        return this;
    }

    /**
     *
     * @return
     */
    public EnchantedBookWrapper create() {
        return this.book;
    }


}
