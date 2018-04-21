package de.bergwerklabs.framework.commons.spigot.item.enchantment;

import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.spigot.item.ItemStackUtil;
import de.bergwerklabs.framework.commons.spigot.json.JsonUtil;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Yannic Rieger on 02.05.2017.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public class EnchantedBookWrapper {

  private ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);

  public EnchantedBookWrapper() {}

  /** @param enchantments */
  public EnchantedBookWrapper(List<EnchantmentWrapper> enchantments) {
    EnchantedBookBuilder builder = new EnchantedBookBuilder();
    enchantments.forEach(
        enchantment ->
            builder.addEnchant(
                enchantment.getEnchantment(),
                enchantment.getLevel(),
                enchantment.isIgnoreRestriction()));
    this.book = builder.create().getBook();
  }

  /**
   * @param json
   * @return
   */
  public static EnchantedBookWrapper fromJson(JsonObject json) {
    EnchantedBookBuilder builder = new EnchantedBookBuilder();

    JsonUtil.jsonArrayToJsonObjectList(json.get("enchantments").getAsJsonArray())
        .forEach(
            jsonEnchantment -> {
              EnchantmentWrapper wrapper = EnchantmentWrapper.fromJson(jsonEnchantment);
              builder.addEnchant(
                  wrapper.getEnchantment(), wrapper.getLevel(), wrapper.isIgnoreRestriction());
            });

    ItemMeta meta = ItemStackUtil.createItemStackFromJson(json).getItemMeta();
    EnchantedBookWrapper book = builder.create();

    EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) book.getBook().getItemMeta();
    bookMeta.setDisplayName(meta.getDisplayName());
    bookMeta.setLore(meta.getLore());
    book.getBook().setItemMeta(bookMeta);

    return book;
  }

  /** */
  public ItemStack getBook() {
    return book;
  }

  public Map<Enchantment, Integer> getEnchantments() {
    return this.book.getEnchantments();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof EnchantedBookWrapper) {
      EnchantedBookWrapper other = (EnchantedBookWrapper) o;
      return this.book.equals(other.book)
          && this.book.getEnchantments().hashCode() == other.book.getEnchantments().hashCode();
    } else return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.book);
  }
}
