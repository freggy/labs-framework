package de.bergwerklabs.framework.commons.spigot.location;

import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.math.SQRT;
import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * Created by Yannic Rieger on 29.04.2017.
 * <p> Util class for Locations
 *
 * @author Yannic Rieger
 */
public class LocationUtil {

    /**
     * Creates a location from JSON.
     *
     * @param json JsonObject representing the Location
     * @return Location created from JSON.
     */
    public static Location locationFromJson(JsonObject json) {
        Double x = json.get("x").getAsDouble();
        Double y = json.get("y").getAsDouble();
        Double z = json.get("z").getAsDouble();
        String world = json.get("world").getAsString();

        LocationBuilder builder = new LocationBuilder(world, x, y, z);

        if (json.has("direction"))
           builder.setDirection(vectorFromJson(json.get("direction").getAsJsonObject()));

        if (json.has("yaw"))
            builder.setYaw(json.get("yaw").getAsFloat());

        if (json.has("pitch"))
            builder.setYaw(json.get("pitch").getAsFloat());

        return builder.create();
    }

    /**
     * Creates a vector from JSON
     *
     * @param json JsonObject representing the vector.
     * @return vector created from JSON.
     */
    public static Vector vectorFromJson(JsonObject json) {
        return new Vector(json.get("x").getAsDouble(), json.get("y").getAsDouble(), json.get("z").getAsDouble());
    }

    /**
     * Returns the euclidean distance using {@link SQRT#fast(double)}.
     *
     * @param a a {@link Location}
     * @param b another {@link Location}
     * @return the euclidean distance between the two given locations.
     */
    public static double calculateDistanceFast(Location a, Location b) {
        return SQRT.fast(Math.pow(a.getX() - b.getX(), 2) +
                            Math.pow(a.getY() - b.getY(), 2) +
                            Math.pow(a.getZ() - b.getZ(), 2));

    }

    /**
     * Returns the euclidean distance using {@link SQRT#fastest(double)}.
     *
     * @param a a {@link Location}
     * @param b another {@link Location}
     * @return the euclidean distance between the two given locations.
     */
    public static double calculateDistanceFastes(Location a, Location b) {
        return SQRT.fastest(Math.pow(a.getX() - b.getX(), 2) +
                               Math.pow(a.getY() - b.getY(), 2) +
                               Math.pow(a.getZ() - b.getZ(), 2));
    }

}
