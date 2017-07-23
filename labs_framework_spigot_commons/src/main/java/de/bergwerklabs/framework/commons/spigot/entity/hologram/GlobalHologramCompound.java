package de.bergwerklabs.framework.commons.spigot.entity.hologram;

import de.bergwerklabs.framework.commons.spigot.entity.hologram.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Yannic Rieger on 19.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class GlobalHologramCompound {

    public Location getLocation() { return this.location; }

    public LinkedList<GlobalHologram> getHolograms() { return this.holograms; }

    private LinkedList<GlobalHologram> holograms = new LinkedList<>();
    private Location location;

    public GlobalHologramCompound(Location location, String... lines) {
        this.location = location;
        Location startHeigt = location.clone().add(0, this.holograms.size() * 0.33, 0);
        Arrays.stream(lines).forEach(line -> {
            this.holograms.add(new GlobalHologram(line, startHeigt));
            startHeigt.subtract(0, 0.30, 0);
        });
    }

    public void display() {
        this.holograms.forEach(GlobalHologram::spawn);
    }

    /**
     *
     */
    public void destroy() {
        this.holograms.forEach(GlobalHologram::despawn);
    }


    public void removeLine(int index) {
        // TODO
    }

    /**
     *
     * @param line
     */
    public void appendLine(String line) {
        // TODO:
    }
}
