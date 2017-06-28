package de.bergwerklabs.framework.schematicservice;

import com.flowpowered.nbt.CompoundTag;
import com.flowpowered.nbt.IntTag;
import com.flowpowered.nbt.Tag;
import com.flowpowered.nbt.stream.NBTInputStream;
import com.flowpowered.nbt.stream.NBTOutputStream;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Yannic Rieger on 28.06.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class NbtUtil {

    /**
     * Reads a compound tag from a .schematic file
     *
     * @return Compound tag
     */
    public static CompoundTag readCompoundTag(File schematicFile) {
        NBTInputStream in = null;
        try {
            in = new NBTInputStream(new FileInputStream(schematicFile));
            CompoundTag d = (CompoundTag) in.readTag();
            in.close();
            return d;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Writes the distance to the provided compound tag.
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

        NBTOutputStream out = new NBTOutputStream(new FileOutputStream(file));
        tagToWrite.getValue().put(x, new IntTag(x, distance.getBlockX()));
        tagToWrite.getValue().put(y, new IntTag(y, distance.getBlockY()));
        tagToWrite.getValue().put(z, new IntTag(z, distance.getBlockZ()));
        out.writeTag(tagToWrite);
        out.flush();
        out.close();
    }

    public static Tag<?> readTag(String tagName, CompoundTag tag) {
        return tag.getValue().get(tagName);
    }



    /**
     * Writes a tag to a compound tag.
     *
     * @param tag NBT tag
     * @param tagToWrite Compound tag to write to.
     * @param file File which contains the NBT data.
     */
    public static void writeTag(Tag<?> tag, CompoundTag tagToWrite, File file) throws IOException {
        NBTOutputStream out = new NBTOutputStream(new FileOutputStream(file));
        tagToWrite.getValue().put(tag.getName(), tag);
        out.writeTag(tagToWrite);
        out.flush();
        out.close();
    }
}
