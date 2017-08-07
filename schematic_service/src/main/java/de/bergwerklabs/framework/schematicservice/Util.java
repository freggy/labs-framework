package de.bergwerklabs.framework.schematicservice;

import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.util.EditSessionBuilder;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.schematic.SchematicFormat;
import de.bergwerklabs.framework.schematicservice.event.SchematicPlacedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.io.File;

public class Util {

    public static <T> void pasteSchematic(File schematicFile, String world, Vector to, LabsSchematic<T> schematic) {
        EditSession session = new EditSessionBuilder(FaweAPI.getWorld(world)).fastmode(true).checkMemory(true).build(); // Maybe turn fast mode off?

        try {
            SchematicFormat.getFormat(schematicFile).load(schematicFile).paste(session, new com.sk89q.worldedit.Vector(to.getX(), to.getY(), to.getZ()), true, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        session.flushQueue();

        Bukkit.getScheduler().callSyncMethod(SchematicMain.getInstance(), () -> {
            Bukkit.getPluginManager().callEvent(new SchematicPlacedEvent(schematic, new Location(Bukkit.getWorld(world), to.getX(), to.getY(), to.getZ())));
            return null;
        });
    }
}
