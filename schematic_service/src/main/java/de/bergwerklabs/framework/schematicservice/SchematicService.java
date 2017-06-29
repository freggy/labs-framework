package de.bergwerklabs.framework.schematicservice;

import com.flowpowered.nbt.CompoundTag;
import de.bergwerklabs.framework.schematicservice.metadata.MetadataDeserializer;
import de.bergwerklabs.framework.schematicservice.metadata.MetadataSerializer;

import java.io.File;
import java.io.IOException;

/**
 * Created by Yannic Rieger on 29.06.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class SchematicService<T> {

    /**
     *
     * @param deserializer
     */
    void setDeserializer(MetadataDeserializer<T> deserializer) { this.deserializer = deserializer; }

    /**
     *
     * @param serializer
     */
    void setSerializer(MetadataSerializer<T> serializer) { this.serializer = serializer; }

    private MetadataDeserializer<T> deserializer;
    private MetadataSerializer<T> serializer;

    /**
     *
     * @param file
     * @return
     */
    public LabsSchematic createSchematic(File file) {
        LabsSchematic schematic = new LabsSchematic(file);
        CompoundTag tag = NbtUtil.readCompoundTag(file);
        this.deserializer.deserialize((CompoundTag) tag.getValue().get("Metadata"));
        T metadata = this.deserializer.deserialize((CompoundTag) tag.getValue().get("Metadata")); // TODO: in line
        schematic.setMetadata(metadata);
        return schematic;
    }

    /**
     *
     * @param file
     * @param metadata
     */
    public void saveSchematic(File file, T metadata) {
        CompoundTag metadataTag = this.serializer.serialize(metadata);

        if (!metadataTag.getName().equals("Metadata"))
            metadataTag = new CompoundTag("Metadata", metadataTag.getValue());

        try {
            NbtUtil.writeTag(metadataTag, NbtUtil.readCompoundTag(file), file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
