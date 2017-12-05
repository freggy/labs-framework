package de.bergwerklabs.framework.commons.spigot.math;

import de.bergwerklabs.framework.commons.math.vector.Vector3D;
import de.bergwerklabs.framework.commons.math.vector.Vector3F;
import org.bukkit.Location;
import org.bukkit.World;

public class VectorUtil {

    public static Vector3D createVectorFromLocation(Location location) {
        return new Vector3D(location.getX(), location.getY(), location.getZ());
    }

    public static Vector3F createFloatVectorFromLocation(Location location) {
        return createVectorFromLocation(location).toFloatVector();
    }

    public static Location createLocationFromVector(World world, Vector3D vector) {
        return new Location(world, vector.getX(), vector.getY(), vector.getZ());
    }

    public static Location createLocationFromVector(World world, Vector3F vector) {
        return new Location(world, vector.getX(), vector.getY(), vector.getZ());
    }

    public static Location createLocationFromVector(World world, Vector3D vector, float yaw, float pitch) {
        return new Location(world, vector.getX(), vector.getY(), vector.getZ(), yaw, pitch);
    }

    public static Location createLocationFromVector(World world, Vector3F vector, float yaw, float pitch) {
        return new Location(world, vector.getX(), vector.getY(), vector.getZ(), yaw, pitch);
    }
}
