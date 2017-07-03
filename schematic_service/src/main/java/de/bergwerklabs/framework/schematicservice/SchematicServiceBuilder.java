package de.bergwerklabs.framework.schematicservice;

import de.bergwerklabs.framework.schematicservice.metadata.MetadataDeserializer;
import de.bergwerklabs.framework.schematicservice.metadata.MetadataSerializer;

/**
 * Created by Yannic Rieger on 29.06.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class SchematicServiceBuilder<T> {

    private SchematicService<T> service = new SchematicService<>();


    public SchematicServiceBuilder setSerializer(MetadataSerializer<T> serializer) {
        service.setSerializer(serializer);
        return this;
    }

    public SchematicServiceBuilder setDeserializer(MetadataDeserializer<T> deserializer) {
        service.setDeserializer(deserializer);
        return this;
    }

    public SchematicService<T> build() {
        return service;
    }
}
