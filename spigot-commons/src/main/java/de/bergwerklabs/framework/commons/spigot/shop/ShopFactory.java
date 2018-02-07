package de.bergwerklabs.framework.commons.spigot.shop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.bergwerklabs.framework.commons.spigot.json.version.VersionedJsonDeserializer;
import de.bergwerklabs.framework.commons.spigot.json.version.VersionedJsonSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by Yannic Rieger on 29.04.2017.
 * <p> Class used for serializing and deserializing npc shops from/to JSON. </p>
 * @author Yannic Rieger
 */
public class ShopFactory {

    private static final String NPC_SHOP_SERIALIZER_PATH   = "de.bergwerklabs.framework.commons.spigot.shop.json.version.{version}.NpcShopSerializer{version}";
    private static final String NPC_SHOP_DESERIALIZER_PATH = "de.bergwerklabs.framework.commons.spigot.shop.json.version.{version}.NpcShopDeserializer{version}";

    private static VersionedJsonDeserializer<NPCShop> npcShopDeserializer = new VersionedJsonDeserializer<>(NPC_SHOP_DESERIALIZER_PATH);
    private static VersionedJsonSerializer<NPCShop> npcShopSerializer     = new VersionedJsonSerializer<>(NPC_SHOP_SERIALIZER_PATH);

    private static Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(NPCShop.class, npcShopDeserializer)
                                                                    .registerTypeAdapter(NPCShop.class, npcShopSerializer).create();

    /**
     * Creats an NPC shop from JSON.
     * @param path Path where the file is located.
     * @return NPCShop created from JSON.
     */
    public static NPCShop createSNPCShop(String path) {
        try {
            File file = new File(path);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file),  Charset.forName("UTF-8"));
            return gson.fromJson(reader, NPCShop.class);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Searches all folders and their subfolders for json files and tries to serialize them.
     * @param rootDir Start directory.
     */
    public static void readNPCShops(File rootDir) {
        File[] fList = rootDir.listFiles();

        for (File file : fList) {
            if (file.isFile()) {
                NPCShop shop = ShopFactory.createSNPCShop(file.getPath());
                NPCShopManager.getShops().put(shop.getId(), shop);
            }
            else if (file.isDirectory()) {
                readNPCShops(file);
            }
        }
    }
}
