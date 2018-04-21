package de.bergwerklabs.framework.commons.spigot.location;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import de.bergwerklabs.framework.commons.math.SQRT;
import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * Created by Yannic Rieger on 29.04.2017.
 *
 * <p>Util class for Locations
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
    if (!json.has("x")) throw new IllegalStateException("Parameter x is not present");
    if (!json.has("y")) throw new IllegalStateException("Parameter y is not present");
    if (!json.has("z")) throw new IllegalStateException("Parameter z is not present");
    if (!json.has("world")) throw new IllegalStateException("Parameter world is not present");

    Double x = json.get("x").getAsDouble();
    Double y = json.get("y").getAsDouble();
    Double z = json.get("z").getAsDouble();
    String world = json.get("world").getAsString();

    LocationBuilder builder = new LocationBuilder(world, x, y, z);

    if (json.has("direction"))
      builder.setDirection(vectorFromJson(json.get("direction").getAsJsonObject()));

    if (json.has("yaw")) builder.setYaw(json.get("yaw").getAsFloat());

    if (json.has("pitch")) builder.setPitch(json.get("pitch").getAsFloat());

    return builder.create();
  }

  /**
   * @param location
   * @return
   */
  public static JsonObject locationToJson(Location location) {
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("x", new JsonPrimitive(location.getX()));
    jsonObject.add("y", new JsonPrimitive(location.getY()));
    jsonObject.add("z", new JsonPrimitive(location.getZ()));
    jsonObject.add("world", new JsonPrimitive(location.getWorld().getName()));
    jsonObject.add("pitch", new JsonPrimitive(location.getPitch()));
    jsonObject.add("yaw", new JsonPrimitive(location.getYaw()));
    jsonObject.add("direction", vectorToJson(location.getDirection()));
    return jsonObject;
  }

  /**
   * @param vector
   * @return
   */
  public static JsonObject vectorToJson(Vector vector) {
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("x", new JsonPrimitive(vector.getX()));
    jsonObject.add("y", new JsonPrimitive(vector.getY()));
    jsonObject.add("z", new JsonPrimitive(vector.getZ()));
    return jsonObject;
  }

  /**
   * Creates a vector from JSON
   *
   * @param json JsonObject representing the vector.
   * @return vector created from JSON.
   */
  public static Vector vectorFromJson(JsonObject json) {
    if (!json.has("x")) throw new IllegalStateException("Parameter x is not present");
    if (!json.has("y")) throw new IllegalStateException("Parameter y is not present");
    if (!json.has("z")) throw new IllegalStateException("Parameter z is not present");
    return new Vector(
        json.get("x").getAsDouble(), json.get("y").getAsDouble(), json.get("z").getAsDouble());
  }

  /**
   * Returns the euclidean distance using {@link SQRT#fast(double)}.
   *
   * @param a a {@link Location}
   * @param b another {@link Location}
   * @return the euclidean distance between the two given locations.
   */
  public static double calculateDistanceFast(Location a, Location b) {
    return SQRT.fast(
        Math.pow(a.getX() - b.getX(), 2)
            + Math.pow(a.getY() - b.getY(), 2)
            + Math.pow(a.getZ() - b.getZ(), 2));
  }

  /**
   * Returns the euclidean distance using {@link SQRT#fastest(double)}.
   *
   * @param a a {@link Location}
   * @param b another {@link Location}
   * @return the euclidean distance between the two given locations.
   */
  public static double calculateDistanceFastes(Location a, Location b) {
    return SQRT.fastest(
        Math.pow(a.getX() - b.getX(), 2)
            + Math.pow(a.getY() - b.getY(), 2)
            + Math.pow(a.getZ() - b.getZ(), 2));
  }
}
