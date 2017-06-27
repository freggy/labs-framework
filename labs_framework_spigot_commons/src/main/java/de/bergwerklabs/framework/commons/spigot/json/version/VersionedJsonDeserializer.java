package de.bergwerklabs.framework.commons.spigot.json.version;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by Yannic Rieger on 12.04.2017.
 * <p> Class used for deserializing an InventoryMenu from JSON. </p>
 * @author Yannic Rieger
 */
public class VersionedJsonDeserializer<T> extends VersionedJsonCoderBase implements JsonDeserializer {

    /**
     * @param pathTovDeserializer Path where the deserializer is located. For conventions see the documentation.
     */
    public VersionedJsonDeserializer(String pathTovDeserializer) {
        super(pathTovDeserializer);
    }

    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject json = jsonElement.getAsJsonObject();
        String version = "V" + json.get("version").getAsString().replace(".", "_");
        return (T)VersionedJsonReflectionUtil.getDeserializer(pathTovDeserializer, version).deserialize(json);
    }
}

