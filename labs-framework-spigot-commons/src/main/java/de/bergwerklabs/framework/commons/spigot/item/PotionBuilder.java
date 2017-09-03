package de.bergwerklabs.framework.commons.spigot.item;

import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

/**
 * Created by Yannic Rieger on 30.04.2017.
 * <p> Builder class for creating Potions </p>
 * @author Yannic Rieger
 */
public class PotionBuilder {

    private Potion potion;

    /**
     * @param type Type of the potion.
     */
    public PotionBuilder(PotionType type) {
        this.potion = new Potion(type);
    }

    /**
     * Sets whether or not the potion has a extended duration.
     * @param extend Value indicating whether or not the potion has a extended duration.
     * @return another PotionBuilder
     */
    public PotionBuilder setExtend(boolean extend) {
        if (!potion.getType().isInstant())
            this.potion.setHasExtendedDuration(extend);
        return this;
    }

    /**
     * Sets the level of the potion.
     * @param lvl Level the potion should have.
     * @return PotionBuilder
     */
    public PotionBuilder setLvl(int lvl) {
        this.potion.setLevel(lvl);
        return this;
    }

    /**
     * Sets whether or not the potion can be thrown.
     * @param splash Value indicating whether or not the potion can be thrown.
     * @return PotionBuilder
     */
    public PotionBuilder setSplash(boolean splash) {
        this.potion.setSplash(splash);
        return this;
    }

    /**
     * Creates the potion that has been built.
     * @return Potion object.
     */
    public Potion create() {
        return this.potion;
    }
}
