package de.bergwerklabs.framework.commons.spigot.scoreboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.bergwerklabs.framework.commons.spigot.json.version.VersionedJsonDeserializer;
import de.bergwerklabs.framework.commons.spigot.json.version.VersionedJsonSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by Yannic Rieger on 03.05.2017.
 * <p> Provides Methods for serializing and deserializing scoreboards. </p>
 * @author Yannic Rieger
 */
public class LabsScoreboardFactory {

    private final static String DESERIALIZER_PATH = "de.bergwerklabs.framework.commons.spigot.scoreboard.version.{version}.LabsScoreboardDeserializer{version}";
    private final static String SERIALIZER_PATH   = "de.bergwerklabs.framework.commons.spigot.scoreboard.version.{version}.LabsScoreboardSerializer{version}";

    private static VersionedJsonSerializer<LabsScoreboard> serializer     = new VersionedJsonSerializer<>(SERIALIZER_PATH);
    private static VersionedJsonDeserializer<LabsScoreboard> deserializer = new VersionedJsonDeserializer<>(DESERIALIZER_PATH);


    private static Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LabsScoreboard.class, deserializer)
            .registerTypeAdapter(LabsScoreboard.class, serializer)
            .create();

    /**
     * Creates an instance of an LabsScoreboard.
     * @param path Path where the JSON file is located.
     * @return LabsScoreboard, null if an error occurred while reading.
     */
    public static LabsScoreboard createInstance(String path) {
        try {
            File file = new File(path);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file),  Charset.forName("UTF-8"));
            return gson.fromJson(reader, LabsScoreboard.class);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Searches all folders and their subfolders for json files and tries to serialize them.
     * Scoreboards that have been found will be saved by the ScoreboardManager
     * @param rootDir Start directory.
     */
    public static void readScoreboards(File rootDir) {
        File[] fList = rootDir.listFiles();

        for (File file : fList) {
            if (file.isFile()) {
                LabsScoreboard board = LabsScoreboardFactory.createInstance(file.getPath());
                ScoreboardManager.scoreboards.put(board.getId(), board);
            }
            else if (file.isDirectory() && file.listFiles().length != 0) {
                readScoreboards(file);
            }
        }
    }
}
