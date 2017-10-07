package de.bergwerklabs.framework.commons.spigot.json.version;

import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.spigot.general.LabsController;
import org.bukkit.Bukkit;

import java.util.Arrays;

/**
 * Created by Yannic Rieger on 12.04.2017.
 * <p> Reflection util class. </p>
 * @author Yannic Rieger
 */
public class VersionedJsonReflectionUtil {

    /**
     * Checks if a class implements LabsController.
     *
     * @param clazz Class to check
     * @return value indicating whether or not the class implements LabsController.
     */
    public static boolean implementsInterface(Class clazz) {
        return Arrays.stream(clazz.getInterfaces()).anyMatch(face -> LabsController.class.isAssignableFrom(face));
    }

    /**
     * Gets the correct deserializer according to the version.
     *
     * @param pathToDeserializer Path where the deserializer is located.
     * @param version MinecraftVersion deserializer deserialize.
     * @return the correct deserializer based on the version.
     */
    public static Deserializer getDeserializer(String pathToDeserializer, String version) {
        return (Deserializer)getCoder(pathToDeserializer, version);
    }

    /**
     * Gets the correct serializer according to the version.
     *
     * @param pathToSerializer Path where the serializer is located.
     * @param version MinecraftVersion the serializer can serialize.
     * @return the correct serializer based on the version.
     */
    public static Serializer getSerializer(String pathToSerializer, String version) {
        return (Serializer)getCoder(pathToSerializer, version);
    }

    /**
     * Gets the correct coder according to the version.
     *
     * @param pathToCoder Path where the coder is located.
     * @param version MinecraftVersion the coder can en/decode.
     * @return correct coder based on the version.
     */
    private static Coder getCoder(String pathToCoder, String version) {
        Coder coder;
        try {
            // Get the coder class corresponding to the version.
            coder = Class.forName(pathToCoder.replace("{version}", version)).asSubclass(Deserializer.class).newInstance();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return coder;
    }

    /**
     *
     * @param json
     * @return
     */
    public static LabsController controllerFromJson(JsonObject json) {
        LabsController controller;
        try {
            if (!VersionedJsonReflectionUtil.implementsInterface(Class.forName(json.get("class").getAsString())))
                throw new IllegalArgumentException("Class does not implement LabsController");

            controller = Class.forName(json.get("class").getAsString()).asSubclass(LabsController.class).newInstance();
            return controller;
        }
        catch (IllegalArgumentException ex) {
            Bukkit.getLogger().info(ex.getMessage());
            return null;
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        catch (InstantiationException e) {
            Bukkit.getLogger().info("Controller class cloud not be instantiated.");
            return null;
        }
        catch (ClassNotFoundException e) {
            Bukkit.getLogger().info("Class could not be found.");
            return null;
        }
    }

}

