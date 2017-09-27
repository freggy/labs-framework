package de.bergwerklabs.framework.schematicservice;

import com.flowpowered.nbt.*;
import com.flowpowered.nbt.stream.NBTInputStream;
import org.bukkit.util.Vector;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Yannic Rieger on 28.06.2017.
 * <p>
 * Contains useful utilities for reading and writing NBT data.
 *
 * @author Yannic Rieger
 */
public class NbtUtil {

    /**
     * Reads a {@link CompoundTag} from a .schematic file
     *
     * @return Compound tag
     */
    public static CompoundTag readCompoundTag(File schematicFile) {
        try {
            try (NBTInputStream in = new NBTInputStream(new FileInputStream(schematicFile))) {
                return (CompoundTag) in.readTag();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Writes the distance to the provided {@link CompoundTag}.
     *
     * @param distance Vector representing a distance between two points.
     * @param tagToWrite Compound tag to write to.
     * @param tagName Name of the tag e.g "WEOffset"
     */
    public static void writeDistance(Vector distance, CompoundTag tagToWrite, String tagName) {
        String x = tagName + "X";
        String y = tagName + "Y";
        String z = tagName + "Z";
        tagToWrite.getValue().put(x, new IntTag(x, distance.getBlockX()));
        tagToWrite.getValue().put(y, new IntTag(y, distance.getBlockY()));
        tagToWrite.getValue().put(z, new IntTag(z, distance.getBlockZ()));
    }

    /**
     * Reads a {@link Tag} from a {@link CompoundTag}.
     *
     * @param tagName Name of the {@link Tag} that should be read.
     * @param tag {@link CompoundTag} that contains the desired {@link Tag}.
     * @return a NBT tag with the given name, null if not found.
     */
    public static Tag<?> readTag(String tagName, CompoundTag tag) {
        return tag.getValue().get(tagName);
    }

    /**
     * Writes a {@link Tag} to a {@link CompoundTag}
     *
     * @param tag NBT tag
     * @param tagToWrite {@link CompoundTag} to write to.
     */
    public static void writeTag(Tag<?> tag, CompoundTag tagToWrite) {
        tagToWrite.getValue().put(tag.getName(), tag);
    }

    /**
     * Creates a new {@link CompoundTag} with the given {@link Tag}s.
     *
     * @param name Name of the {@link CompoundTag}.
     * @param tags Array of Tags that should be contained.
     * @return a new {@link CompoundTag}.
     */
    public static CompoundTag newCompoundTag(String name, Tag<?>... tags) {
        return new CompoundTag(name, new CompoundMap(Arrays.asList(tags)));
    }

    /**
     * Converts a {@link com.sk89q.worldedit.Vector} to NBT.
     * The {@link CompoundTag} is structured as follows:
     * <pre>
     *     <code>
     *        TAG_Compound("name"): 3 entries {
     *           TAG_Double("x"): -9
     *           TAG_Double("y"): -3
     *           TAG_Double("z"): -15
     *        }
     *     </code>
     * </pre>
     *
     * @param name Name of the {@link CompoundTag}.
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     * @return a {@link CompoundTag} that represents a vector.
     */
    public static CompoundTag vectorToNbt(String name, Double x, Double y, Double z) {
        return newCompoundTag(name, new DoubleTag("x", x),
                                    new DoubleTag("y", y),
                                    new DoubleTag("z", z));
    }

    /**
     * Converts a {@link CompoundTag} to a {@link Vector}.
     * Structure of the {@link CompoundTag}:
     * <pre>
     *     <code>
     *        TAG_Compound("name"): 3 entries {
     *           TAG_Double("x"): -9
     *           TAG_Double("y"): -3
     *           TAG_Double("z"): -15
     *        }
     *     </code>
     * </pre>
     *
     * @param tag {@link CompoundTag} that represents a {@link Vector}.
     * @return a {@link Vector}
     */
    public static Vector vectorFromNbt(CompoundTag tag) {
        return new Vector(new Double(tag.getValue().get("x").getValue().toString()),
                          new Double(tag.getValue().get("y").getValue().toString()),
                          new Double(tag.getValue().get("z").getValue().toString()));
    }

}
