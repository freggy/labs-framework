package de.bergwerklabs.framework.schematicservice;

import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.util.EditSessionBuilder;
import com.boydti.fawe.util.TaskManager;
import com.flowpowered.nbt.CompoundTag;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;
import de.bergwerklabs.framework.schematicservice.event.SchematicPlacedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;

/**
 * Created by Yannic Rieger on 05.05.2017.
 * <p> This class is used for pasting schematics asynchronously.
 *
 *  @author Yannic Rieger
 */
public class LabsSchematic<T> {

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

    /**
     * @param schematicFile File representing the schematic (File extension: .schematic)
     */
    public LabsSchematic(File schematicFile) {
        this.schematicFile = schematicFile;
    }

    /**
     * Pastes a WorldEdit schematic asynchronously in the specified world.
     *
     * @param world World where to paste the schematic in.
     * @param to Vector which conains x, y and z coordinates representing the paste location.
     */
    public void pasteAsync(String world, Vector to) {

        TaskManager.IMP.async(() -> {
            EditSession session = new EditSessionBuilder(FaweAPI.getWorld(world)).fastmode(true).checkMemory(true).build(); // Maybe turn fast mode off?
            try {
                SchematicFormat.getFormat(schematicFile).load(schematicFile).paste(session, new com.sk89q.worldedit.Vector(to.getX(), to.getY(), to.getZ()), true, true);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            session.flushQueue();

            // TODO: execute sync
            Bukkit.getPluginManager().callEvent(new SchematicPlacedEvent(this, new Location(Bukkit.getWorld(world), to.getX(), to.getY(), to.getZ())));
        });
    }
}
