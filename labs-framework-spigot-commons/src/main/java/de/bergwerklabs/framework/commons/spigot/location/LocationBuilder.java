package de.bergwerklabs.framework.commons.spigot.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * Created by Yannic Rieger on 29.04.2017.
 * <p> Builder class for creating Locations more easily </p>
 * @author Yannic Rieger
 */
public class LocationBuilder {

    private Location location;

    /**
     * @param world World where the location exists.
     * @param x x co-ordinate
     * @param y y co-ordinate
     * @param z z co-ordinate
     */
    public LocationBuilder(String world, double x, double y, double z) {
        this.location = new Location(Bukkit.getWorld(world), x, y, z);
    }

    /**
     * Sets the direction.
     *
     * @param direction A unit-vector pointing in the direction that this Location is facing.
     * @return another LocationBuilder
     */
    public LocationBuilder setDirection(Vector direction) {
        this.location.setDirection(direction);
        return this;
    }

    /**
     * Sets yaw
     *
     * @param yaw
     * @return another LocationBuilder
     */
    public LocationBuilder setYaw(float yaw) {
        this.location.setYaw(yaw);
        return this;
    }

    /**
     * Sets pitch
     *
     * @param pitch Pitch of this location, measured in degrees
     * @return another LocationBuilder
     */
    public LocationBuilder setPitch(float pitch) {
        this.location.setPitch(pitch);
        return this;
    }

    /**
     * Creates the final location
     * @return Location that has been built.
     */
    public Location create() {
        return this.location.clone();
    }
}
