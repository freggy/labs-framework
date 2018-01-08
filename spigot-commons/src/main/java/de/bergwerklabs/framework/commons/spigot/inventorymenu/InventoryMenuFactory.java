package de.bergwerklabs.framework.commons.spigot.inventorymenu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.spigot.json.version.VersionedJsonDeserializer;
import de.bergwerklabs.framework.commons.spigot.json.version.VersionedJsonSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Yannic Rieger on 12.04.2017.
 * <p> Factory used for creating InventoryMenu instances. </p>
 * @author Yannic Rieger
 * @see <a href=https://hackmd.io/GbAsAYGNVBWBaAHOARgZnqAbIx8CGA7FPAIzCmT6y6wBMAJlkA==> Documentation </a>
 */
public class InventoryMenuFactory {

    private final static String DESERIALIZER_PATH = "de.bergwerklabs.framework.commons.spigot.inventorymenu.version.{version}.InventoryMenuDeserializer{version}";
    private final static String SERIALIZER_PATH   = "de.bergwerklabs.framework.commons.spigot.inventorymenu.version.{version}.InventoryMenuSerializer{version}";

    private static VersionedJsonSerializer<InventoryMenu> serializer     = new VersionedJsonSerializer<>(SERIALIZER_PATH);
    private static VersionedJsonDeserializer<InventoryMenu> deserializer = new VersionedJsonDeserializer<>(DESERIALIZER_PATH);


    private static Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(InventoryMenu.class, deserializer)
                                                                    .registerTypeAdapter(InventoryMenu.class, serializer)
                                                                    .create();

    /**
     * Creates an instance of an InventoryMenu.
     * @param json JsonObject representing the InventoryMenu.
     * @return InventoryMenu
     */
    public static InventoryMenu createInstance(JsonObject json) {
        return deserializer.deserialize(json, null, null);
    }

    /**
     * Creates an instance of an InventoryMenu.
     * @param path Path where the JSON file is located.
     * @return InventoryMenu, null if an error occurred while reading.
     */
    public static InventoryMenu createInstance(String path) {

        try {
            File file = new File(path);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file),  Charset.forName("UTF-8"));
            return gson.fromJson(reader, InventoryMenu.class);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Searches all folders and their subfolders for json files and tries to serialize them.
     * @param rootDir Start directory.
     * @param listToAdd List to add the InventoryMenu to (optional)
     */
    public static void readMenus(File rootDir, List<InventoryMenu> listToAdd) {

        File[] fList = rootDir.listFiles();

        for (File file : fList) {
            if (file.isFile()) {
                InventoryMenu menu = InventoryMenuFactory.createInstance(file.getPath());
                if (listToAdd != null) listToAdd.add(menu);
            }
            else if (file.isDirectory() && file.listFiles().length != 0) {
                readMenus(file, listToAdd);
            }
        }
    }
}

