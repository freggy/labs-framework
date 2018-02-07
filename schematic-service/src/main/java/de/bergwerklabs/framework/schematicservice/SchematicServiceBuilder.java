package de.bergwerklabs.framework.schematicservice;

import de.bergwerklabs.framework.schematicservice.metadata.MetadataDeserializer;
import de.bergwerklabs.framework.schematicservice.metadata.MetadataSerializer;

/**
 * Created by Yannic Rieger on 29.06.2017.
 * <p>
 * Builder used for creating the {@link SchematicService}.
 *
 * @author Yannic Rieger
 */
public class SchematicServiceBuilder<T> {

    private SchematicService<T> service = new SchematicService<>();

    /**
     * Sets the {@link MetadataSerializer} used for serializing metadata.
     *
     * @param serializer Class implementing {@link MetadataSerializer}.
     * @return another {@code SchematicServiceBuilder}
     */
    public SchematicServiceBuilder setSerializer(MetadataSerializer<T> serializer) {
        service.setSerializer(serializer);
        return this;
    }

    /**
     * Sets the {@link MetadataDeserializer} used for deserializing metadata.
     *
     * @param deserializer Class implementing {@link MetadataDeserializer}.
     * @return another {@code SchematicServiceBuilder}
     */
    public SchematicServiceBuilder setDeserializer(MetadataDeserializer<T> deserializer) {
        service.setDeserializer(deserializer);
        return this;
    }

    /**
     * Creates an instance of {@link SchematicService}.
     */
    public SchematicService<T> build() {
        return service;
    }
}
