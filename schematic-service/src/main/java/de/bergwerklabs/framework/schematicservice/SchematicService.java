package de.bergwerklabs.framework.schematicservice;

import com.flowpowered.nbt.CompoundTag;
import com.flowpowered.nbt.stream.NBTOutputStream;
import com.google.common.base.Preconditions;
import de.bergwerklabs.framework.schematicservice.metadata.MetadataDeserializer;
import de.bergwerklabs.framework.schematicservice.metadata.MetadataSerializer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * Created by Yannic Rieger on 29.06.2017.
 *
 * <p>Class reading and writing metadata to schematic files.
 *
 * @author Yannic Rieger
 */
public class SchematicService<T> {

  private MetadataDeserializer<T> deserializer;
  private MetadataSerializer<T> serializer;

  /**
   * Gets the {@link Location} defined by the given Vector. This Vector contains the distance
   * between the start point and the other point.
   *
   * @param start {@link Location} where the schematic has been pasted to.
   * @param relative Contains the distance between the start point and the other point.
   * @return the {@link Location} defined by the given Vector.
   */
  public static Location getRelativeLocation(Location start, Vector relative) {
    return start.clone().subtract(relative);
  }

  /**
   * Sets the {@link MetadataDeserializer}.
   *
   * @param deserializer Class implementing {@link MetadataDeserializer}.
   */
  void setDeserializer(MetadataDeserializer<T> deserializer) {
    this.deserializer = deserializer;
  }

  /**
   * Sets the {@link MetadataSerializer<T>}.
   *
   * @param serializer Class implementing {@link MetadataSerializer}.
   */
  void setSerializer(MetadataSerializer<T> serializer) {
    this.serializer = serializer;
  }

  /**
   * Creates a {@link LabsSchematic} while deserializing metadata.
   *
   * @param file File representing a schematic.
   * @return a {@link LabsSchematic} with metadata.
   */
  public LabsSchematic<T> createSchematic(File file) {
    Preconditions.checkNotNull(file);

    LabsSchematic<T> schematic = new LabsSchematic<>(file);
    CompoundTag tag = NbtUtil.readCompoundTag(file);

    if (!tag.getValue().containsKey("Metadata")) {
      System.out.println(
          SchematicMain.CONSOLE_PREFIX + "File " + file.getName() + "does not contain metadata");
    } else {
      this.deserializer.deserialize((CompoundTag) tag.getValue().get("Metadata"));
      T metadata =
          this.deserializer.deserialize(
              (CompoundTag) tag.getValue().get("Metadata")); // TODO: in line
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
    CompoundTag compoundTag = NbtUtil.readCompoundTag(file);

    if (!metadataTag.getName().equals("Metadata"))
      metadataTag = new CompoundTag("Metadata", metadataTag.getValue());

    NbtUtil.writeTag(metadataTag, compoundTag);

    this.writeToFile(compoundTag, file);
  }

  /**
   * Writes metadata to the given schematic file.
   *
   * @param file File write the metadata to.
   * @param tag {@link CompoundTag} to write to.
   * @param metadata Metadata to write.
   */
  public void saveSchematic(CompoundTag tag, File file, T metadata) {
    CompoundTag metadataTag = this.serializer.serialize(metadata);

    if (!metadataTag.getName().equals("Metadata"))
      metadataTag = new CompoundTag("Metadata", metadataTag.getValue());

    NbtUtil.writeTag(metadataTag, tag);
    this.writeToFile(tag, file);
  }

  /**
   * Writes a {@link CompoundTag} to a given file.
   *
   * @param tag {@link CompoundTag} to write.
   * @param file {@link File} to write the tag to.
   */
  private void writeToFile(CompoundTag tag, File file) {
    try {
      try (NBTOutputStream out = new NBTOutputStream(new FileOutputStream(file))) {
        out.writeTag(tag);
        out.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
