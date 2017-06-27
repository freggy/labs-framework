package de.bergwerklabs.framework.commons.spigot.item.enchantment;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.spigot.json.JsonUtil;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Yannic Rieger on 02.05.2017.
 * <p>  </p>
 * @author Yannic Rieger
 */
public class EnchantmentWrapper {

    /**
     *
     * @return
     */
    public Enchantment getEnchantment() {
        return enchantment;
    }

    /**
     *
     * @return
     */
    public boolean isIgnoreRestriction() {
        return ignoreRestriction;
    }

    /**
     *
     * @return
     */
    public int getLevel() {
        return level;
    }

    private int level = 1;
    private Enchantment enchantment;

    private boolean ignoreRestriction = false;

    /**
     *
     * @param enchantment
     * @param level
     * @param ignoreRestriction
     */
    public EnchantmentWrapper(Enchantment enchantment, int level, boolean ignoreRestriction) {
        this.enchantment = enchantment;
        this.level = level;
        this.ignoreRestriction = ignoreRestriction;
    }


    /**
     *
     * @param json
     * @return
     */
    public static EnchantmentWrapper fromJson(JsonObject json) {
        int level = 1;
        boolean ignore = false;

        if (json.has("level"))
            level = json.get("level").getAsInt();

        if (json.has("ignore-level-restriction"))
            ignore = json.get("ignore-level-restriction").getAsBoolean();

        Enchantment enchantment = Enchantment.getByName(json.get("type").getAsString());

        if (enchantment == null) {
            Bukkit.getLogger().info("[LABS_UTIL] EnchantmentWrapper " + json.get("type").getAsString() + "could not be found");
            return null;
        }

        else return new EnchantmentWrapper(enchantment, level, ignore);
    }

    /**
     *
     * @param jsonElements
     * @return
     */
    public static List<EnchantmentWrapper> enchantmentsFromJson(JsonArray jsonElements) {
        return JsonUtil.jsonArrayToJsonObjectList(jsonElements).stream().map(jsonEnchantment -> fromJson(jsonEnchantment)).collect(Collectors.toList());
    }
}
