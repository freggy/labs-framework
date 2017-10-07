package de.bergwerklabs.framework.commons.spigot.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yannic Rieger on 13.04.2017.
 * <p> Class which contains useful methods for working with JSON. </p>
 * @author Yannic Rieger
 */
public class JsonUtil {

    /**
     * Converts a JsonArray containing Strings to a list.
     * @param array JsonArray with strings.
     *  @return list containing all Strings previously contained in the array
     */
    public static List<String> jsonArrayToStringList(JsonArray array) {
        ArrayList<String> list = new ArrayList<>();
        array.iterator().forEachRemaining(line -> list.add(line.getAsString()));
        return list;
    }

    /**
     * Converts a JsonArray containing JsonObjects to a list.
     * @param array JsonArray with JsonObjects.
     * @return list containing all JsonObjects previously contained in the array
     */
    public static List<JsonObject> jsonArrayToJsonObjectList(JsonArray array) {
        ArrayList<JsonObject> list = new ArrayList<>();
        array.iterator().forEachRemaining(line -> list.add(line.getAsJsonObject()));
        return list;
    }


}

