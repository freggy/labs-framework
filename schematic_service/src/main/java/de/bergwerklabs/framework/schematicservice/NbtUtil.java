package de.bergwerklabs.framework.schematicservice;

import com.flowpowered.nbt.*;
import com.flowpowered.nbt.stream.NBTInputStream;
import com.flowpowered.nbt.stream.NBTOutputStream;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Yannic Rieger on 28.06.2017.
 * <p> Contains useful utilities for reading and writing NBT data.
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
     * @param file File File which contains the NBT data.
     */
    public static void writeDistance(Vector distance, CompoundTag tagToWrite, String tagName, File file) throws IOException {
        String x = tagName + "X";
        String y = tagName + "Y";
        String z = tagName + "Z";

       try (NBTOutputStream out = new NBTOutputStream(new FileOutputStream(file))) {
           tagToWrite.getValue().put(x, new IntTag(x, distance.getBlockX()));
           tagToWrite.getValue().put(y, new IntTag(y, distance.getBlockY()));
           tagToWrite.getValue().put(z, new IntTag(z, distance.getBlockZ()));
           out.writeTag(tagToWrite);
           out.flush();
       }
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
     * @param file {@link File} which contains the NBT data.
     */
    public static void writeTag(Tag<?> tag, CompoundTag tagToWrite, File file) throws IOException {
        try (NBTOutputStream out = new NBTOutputStream(new FileOutputStream(file))) {
            tagToWrite.getValue().put(tag.getName(), tag);
            out.writeTag(tagToWrite);
            out.flush();
        }
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
     * @param vector {@link com.sk89q.worldedit.Vector} to be converted.
     * @return a {@link CompoundTag} that represents a {@link com.sk89q.worldedit.Vector}.
     */
    public static CompoundTag intVectorToNbt(String name, com.sk89q.worldedit.Vector vector) {
        return newCompoundTag(name, new IntTag("x", vector.getBlockX()),
                                    new IntTag("y", vector.getBlockY()),
                                    new IntTag("z", vector.getBlockZ()));
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
    public static Vector intVectorFromNbt(CompoundTag tag) {
        return new Vector((Integer) tag.getValue().get("x").getValue(),
                          (Integer) tag.getValue().get("y").getValue(),
                          (Integer) tag.getValue().get("z").getValue());
    }

}
