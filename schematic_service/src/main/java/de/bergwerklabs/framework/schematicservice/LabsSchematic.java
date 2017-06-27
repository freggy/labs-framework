package de.bergwerklabs.framework.schematicservice;

import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.object.schematic.Schematic;
import com.boydti.fawe.util.TaskManager;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sun.istack.internal.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;

/**
 * Created by Yannic Rieger on 05.05.2017.
 * <p> This class is used for pasting schematics asynchronously </p>
 * @author Yannic Rieger
 */
public class LabsSchematic {

    /**
     * Gets the schematic as a file object.
     */
    public File getSchematicFile() {
        return schematicFile;
    }

    /**
     * Gets the schematic object.
     */
    public Schematic getSchematic() { return schematic; }

    private File schematicFile;
    private Schematic schematic;

    /**
     * @param schematicFile File representing the schematic (File extension: .schematic)
     */
    public LabsSchematic(@NotNull File schematicFile) throws IOException {
        this.schematicFile = schematicFile;
        this.schematic =  ClipboardFormat.SCHEMATIC.load(schematicFile);
    }

    /**
     * Pastes a WorldEdit schematic asynchronously in the specified world.
     * @param world World where to paste the schematic in.
     * @param to Vector which conains x, y and z coordinates representing the paste location.
     */
    public void pasteAsync(@NotNull String world, @NotNull Vector to) {
        Bukkit.getLogger().info("Pasting Schematic " + this.getSchematicFile().getName() + "in World " + world +"to" + to);
        TaskManager.IMP.async(() -> this.schematic.paste(FaweAPI.getWorld(world), new com.sk89q.worldedit.Vector(to.getX(),
                                                                                                                 to.getY(),
                                                                                                                 to.getZ())));
    }
}
