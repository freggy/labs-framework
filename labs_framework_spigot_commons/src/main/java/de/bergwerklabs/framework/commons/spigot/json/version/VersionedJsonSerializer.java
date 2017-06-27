package de.bergwerklabs.framework.commons.spigot.json.version;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by Yannic Rieger on 12.04.2017.
 * <p> Serializes json files based on version. </p>
 * @author Yannic Rieger
 */
public class VersionedJsonSerializer<T extends Versionable> extends VersionedJsonCoderBase implements JsonSerializer<T> {

    /**
     * @param pathTovDeserializer Path where the deserializer is located. For conventions see the documentation.
     */
    public VersionedJsonSerializer(String pathTovDeserializer) {
        super(pathTovDeserializer);
    }

    @Override
    public JsonElement serialize(T versionable, Type type, JsonSerializationContext jsonSerializationContext) {
        return VersionedJsonReflectionUtil.getSerializer(pathTovDeserializer, versionable.getVersion()).serialize(versionable);
    }
}

