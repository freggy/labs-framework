package de.bergwerklabs.framework.schematicservice.event;

import de.bergwerklabs.framework.schematicservice.LabsSchematic;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Yannic Rieger on 28.06.2017.
 * <p> Event that will be called when a Schematic has been pasted.
 *
 * @author Yannic Rieger
 */
public class SchematicPlacedEvent extends Event {

    /**
     * Gets the schematic that has been placed.
     */
    public LabsSchematic getSchematic() {
        return schematic;
    }

    /**
     * Gets the location where the schematic has been placed to.
     */
    public Location getPlaced() {
        return placed;
    }

    /**
     * Gets the list of all handlers.
     */
    public static HandlerList getHandlerList() { return handlers; }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    private static HandlerList handlers = new HandlerList();
    private LabsSchematic schematic;
    private Location placed;

    /**
     * @param schematic The schematic that has been placed.
     * @param placed The location where the schematic has been placed to.
     */
    public SchematicPlacedEvent(LabsSchematic schematic, Location placed) {
        this.schematic = schematic;
        this.placed = placed;
    }
}
