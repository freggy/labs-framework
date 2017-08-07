package de.bergwerklabs.framework.schematicservice;

import com.boydti.fawe.object.RunnableVal;
import com.boydti.fawe.util.TaskManager;
import org.bukkit.util.Vector;

import java.io.File;

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
            Util.pasteSchematic(this.schematicFile, world, to, this);
        });
    }

    /**
     *
     * @param world
     * @param to
     */
    public void pasteSync(String world, Vector to) {
        File file = this.schematicFile;
        LabsSchematic<T> instance = this;
        TaskManager.IMP.syncWhenFree(new RunnableVal<Object>() {
            @Override
            public void run(Object value) {
                Util.pasteSchematic(file, world, to, instance);
            }
        });
    }
}
