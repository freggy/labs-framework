package de.bergwerklabs.framework.commons.spigot.npc;

import de.bergwerklabs.util.entity.NPC;
import de.bergwerklabs.util.entity.PlayerSkin;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Yannic Rieger on 28.04.2017.
 * <p> Builder class for creating an NPC. </p>
 * @author Yannic Rieger
 */
public class NPCBuilder {

    private NPC npc;

    /**
     * @param title Title to display
     * @param subtitle Subtitle to display
     * @param displayTitle Value indicating whether or not the title should be displayed.
     * @param displaySubtitle Value indicating whether or not the subtitle should be displayed.
     * @param location location where the NPC spawns.
     * @throws IllegalAccessException
     */
    public NPCBuilder(String title, String subtitle, boolean displayTitle, boolean displaySubtitle, Location location) throws IllegalAccessException {
        this.npc = new NPC(title, subtitle, displayTitle, displaySubtitle, location);
    }

    /**
     * Sets the skin of the NPC
     * @param skin Skin
     * @return another NPCBuilder
     */
    public NPCBuilder setSkin(PlayerSkin skin) {
        this.npc.changeSkin(skin);
        return this;
    }

    /**
     * Sets the armor.
     * @param item ItemStack representing the armor.
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @return another NPCBuilder
     */
    public NPCBuilder setArmor(ItemStack item) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        this.npc.setChestSlot(item);
        return this;
    }

    /**
     * Sets the shoes.
     * @param item ItemStack representing the shoes.
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @return another NPCBuilder
     */
    public NPCBuilder setShoes(ItemStack item) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        this.npc.setShoeSlot(item);
        return this;
    }

    /**
     * Sets the Leggings.
     * @param item ItemStack representing the Leggings.
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @return another NPCBuilder
     */
    public NPCBuilder setLeggins(ItemStack item) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        this.npc.setLegSlot(item);
        return this;
    }

    /**
     * Sets the helmet.
     * @param item ItemStack representing the helmet.
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @return another NPCBuilder
     */
    public NPCBuilder setHelmet(ItemStack item) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        this.npc.setHeadSlot(item);
        return this;
    }

    /**
     * Updates the skin of the NPC.
     * @param value
     * @param signature
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @return another NPCBuilder
     */
    public NPCBuilder updateSkin(String value, String signature) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this.npc.updateSkin(value, signature);
        return this;
    }

    /**
     * Sets sleep state.
     * @param sleepState Value indicating whether or not...
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @return another NPCBuilder
     */
    public NPCBuilder setSleepState(boolean sleepState) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.npc.setSleepState(sleepState);
        return this;
    }

    /**
     * Creates the final NPC.
     * @return Built NPC
     */
    public NPC create() {
        return this.npc;
    }
}
