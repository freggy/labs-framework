package de.bergwerklabs.framework.commons.spigot.hologram.compound;

import de.bergwerklabs.framework.commons.spigot.hologram.Hologram;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by Yannic Rieger on 19.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class PlayerHologramCompound {

    public LinkedList<Hologram> getHolograms() { return this.holograms; }

    private LinkedList<Hologram> holograms = new LinkedList<>();
    private Location location;

    /**
     *
     * @param text
     */
    public PlayerHologramCompound(String... text) {
        for (String aText : text) {
            this.holograms.add(new Hologram(aText));
        }
    }

    /**
     *
     * @param player
     * @param location
     */
    public void display(Player player, Location location) {
        this.location = location;
        Location startHeigt = location.clone().add(0, this.holograms.size() * 0.33, 0);
        this.holograms.forEach(hologram -> {
            hologram.display(player, startHeigt);
            startHeigt.subtract(0, 0.30, 0);
        });
    }

    /**
     *
     * @param player
     * @param index
     */
    public void removeLine(Player player, int index) {
        this.holograms.remove(this.holograms.get(index));
        this.display(player, this.location);
    }

    /**
     *
     * @param player
     * @param line
     */
    public void appendLine(Player player, String line) {
        this.holograms.add(new Hologram(line));
        this.display(player, this.location);
    }

    // TODO: public void changeLine(int index, String replacement)
}
