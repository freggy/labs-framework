package de.bergwerklabs.framework.schematicservice;

import com.boydti.fawe.Fawe;
import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.object.schematic.Schematic;
import com.boydti.fawe.util.TaskManager;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yannic Rieger on 05.05.2017.
 * <p>
 * This class is used for pasting schematics asynchronously.
 *
 *  @author Yannic Rieger
 */
public class LabsSchematic<T> implements Cloneable {

    /**
     * Gets the schematic as a file object.
     */
    public File getSchematicFile() {
        return schematicFile;
    }

    /**
     * Gets the metadata contained in this schematic.
     */
    public T getMetadata() { return this.metadata; }

    /**
     * Gets a value indicating whether or not the {@code LabsSchematic} has metadata.
     */
    public boolean hasMetadata() { return this.metadata != null; }

    /**
     * Sets the metadata.
     *
     * @param metadata Metadata object that will be written into the schematic file.
     */
    void setMetadata(T metadata) { this.metadata = metadata; }

    private File schematicFile;
    private T metadata;
    private Schematic schematic;
    private Vector offset;
    private List<Vector> blockVectors = new ArrayList<>();

    /**
     * @param schematicFile File representing the schematic (File extension: .schematic)
     */
    public LabsSchematic(File schematicFile) {
        this.schematicFile = schematicFile;

        try {
            this.schematic = ClipboardFormat.SCHEMATIC.load(schematicFile);

            // Preprocess schematic to make removal easier. This needs to be done since
            // undoing the schematic by using an EditSession does not work as intended.
            // I tried everything and this is the best solution I came up with.
            // Maybe we could find a way by not using deprecated classes, but at this point in time
            // I do not give a single fuck.
            CuboidClipboard clip = CuboidClipboard.loadSchematic(this.schematicFile);
            this.offset = new Vector(clip.getOffset().getX(), clip.getOffset().getY(), clip.getOffset().getZ());

            for (int x = 0; x < clip.getSize().getBlockX(); x++) {
                for (int y = 0; y < clip.getSize().getBlockY(); y++) {
                    for (int z = 0; z < clip.getSize().getBlockZ(); z++) {
                        final BaseBlock baseBlock = clip.getBlock(x, y, z);
                        if (baseBlock.isAir()) continue;
                        this.blockVectors.add(new Vector(x, y, z));
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Pastes a WorldEdit schematic asynchronously in the specified world.
     *
     * @param world World where to paste the schematic in.
     * @param to    Vector which conains x, y and z coordinates representing the paste location.
     */
    public void pasteAsync(String world, Vector to) {
        TaskManager.IMP.async(() -> {
            this.paste(world, to);
        });
    }

    /**
     * Pastes the {@link LabsSchematic} synchronously.
     *
     * @param world World where to paste the schematic in.
     * @param to    Vector which conains x, y and z coordinates representing the paste location.
     */
    public void pasteSync(String world, Vector to) {
        this.paste(world, to);
    }

    @Override
    public LabsSchematic<T> clone() {
        try {
            return (LabsSchematic<T>)super.clone();
        }
        catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Removes the schematic.
     *
     * @param location the {@link Location} where this schematic has been pasted to.
     */
    public void remove(Location location) {
        Location offsetLoc = location.clone().add(this.offset);
        this.blockVectors.forEach(vector -> {
            Block block = location.getWorld().getBlockAt(
                    offsetLoc.getBlockX() + vector.getBlockX(),
                    offsetLoc.getBlockY() + vector.getBlockY(),
                    offsetLoc.getBlockZ() + vector.getBlockZ()
            );
            block.setType(Material.AIR);
        });
    }

    /**
     * Pastes the {@link LabsSchematic}.
     *
     * @param world World where to paste the schematic in.
     * @param to    Vector which conains x, y and z coordinates representing the paste location.
     */
    private void paste(String world, Vector to) {
        this.schematic.paste(
                FaweAPI.getWorld(world),
                new com.sk89q.worldedit.Vector(to.getX(), to.getY(), to.getZ()),
                false,
                false,
                null
        );
    }
}
