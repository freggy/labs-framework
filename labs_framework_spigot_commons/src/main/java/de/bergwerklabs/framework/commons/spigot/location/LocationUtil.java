package de.bergwerklabs.framework.commons.spigot.location;

import com.google.gson.JsonObject;
import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * Created by Yannic Rieger on 29.04.2017.
 * <p> Util class for Locations </p>
 * @author Yannic Rieger
 */
public class LocationUtil {

    /**
     * Creates a location from JSON.
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
     * @param json JsonObject representing the vector.
     * @return vector created from JSON.
     */
    public static Vector vectorFromJson(JsonObject json) {
        return new Vector(json.get("x").getAsDouble(), json.get("y").getAsDouble(), json.get("z").getAsDouble());
    }
}
