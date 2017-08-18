package de.bergwerklabs.framework.schematicservice;

import com.flowpowered.nbt.CompoundTag;
import de.bergwerklabs.framework.schematicservice.metadata.MetadataDeserializer;
import de.bergwerklabs.framework.schematicservice.metadata.MetadataSerializer;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;

/**
 * Created by Yannic Rieger on 29.06.2017.
 * <p> Class reading and writing metadata to schematic files.
 *
 * @author Yannic Rieger
 */
public class SchematicService<T> {

    /**
     * Sets the {@link MetadataDeserializer<T>}.
     *
     * @param deserializer Class implementing {@link MetadataDeserializer<T>}.
     */
    void setDeserializer(MetadataDeserializer<T> deserializer) { this.deserializer = deserializer; }

    /**
     * Sets the {@link MetadataSerializer<T>}.
     *
     * @param serializer Class implementing {@link MetadataSerializer<T>}.
     */
    void setSerializer(MetadataSerializer<T> serializer) { this.serializer = serializer; }

    private MetadataDeserializer<T> deserializer;
    private MetadataSerializer<T> serializer;

    /**
     * Gets the {@link Location} defined by the given Vector.
     * This Vector contains the distance between the start point and the other point.
     *
     * @param start {@link Location} where the schematic has been pasted to.
     * @param relative Contains the distance between the start point and the other point.
     * @return the {@link Location} defined by the given Vector.
     */
    public static Location getRelativeLocation(Location start, Vector relative) {
        return start.clone().subtract(relative);
    }

    /**
     * Creates a {@link LabsSchematic<T>} while deserializing metadata.
     *
     * @param file File representing a schematic.
     * @return a {@link LabsSchematic} with metadata.
     */
    public LabsSchematic<T> createSchematic(File file) {
        LabsSchematic<T> schematic = new LabsSchematic<>(file);
        CompoundTag tag = NbtUtil.readCompoundTag(file);

        if (!tag.getValue().containsKey("Metadata")) {
            System.out.println(SchematicMain.CONSOLE_PREFIX + "File " + file.getName() + "does not contain metadata");
        }
        else {
            this.deserializer.deserialize((CompoundTag) tag.getValue().get("Metadata"));
            T metadata = this.deserializer.deserialize((CompoundTag) tag.getValue().get("Metadata")); // TODO: in line
            schematic.setMetadata(metadata);
        }

        return schematic;
    }

    /**
     * Writes metadata to the given schematic file.
     *
     * @param file File write the metadata to.
     * @param metadata Metadata to write.
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
