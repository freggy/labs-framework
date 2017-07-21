package de.bergwerklabs.framework.commons.spigot.hologram.compound;

import de.bergwerklabs.framework.commons.spigot.hologram.Hologram;
import de.bergwerklabs.framework.commons.spigot.hologram.HologramManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.LinkedList;

/**
 * Created by Yannic Rieger on 19.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class GlobalHologramCompound {

    public Location getLocation() { return this.location; }

    public LinkedList<Hologram> getHolograms() { return this.holograms; }

    private LinkedList<Hologram> holograms = new LinkedList<>();
    private Location location;

    public GlobalHologramCompound(String... text) {
        for (String aText : text) {
            this.holograms.add(new Hologram(aText));
        }
    }

    /**
     *
     * @param location
     */
    public void display(Location location) {
        this.location = location;
        Bukkit.getOnlinePlayers().forEach(player -> {
            Location startHeigt = location.clone().add(0, this.holograms.size() * 0.33, 0);
            this.holograms.forEach(hologram -> {
                hologram.display(player, startHeigt);
                startHeigt.subtract(0, 0.30, 0);
            });
        });
    }

    /**
     *
     */
    public void destroy() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            this.holograms.forEach(hologram -> {
                hologram.destroy(player);
            });
        });
    }

    /**
     *
     * @param index
     */
    public void removeLine(int index) {
        this.holograms.remove(this.holograms.get(index));
        this.display(this.location);
    }

    /**
     *
     * @param line
     */
    public void appendLine(String line) {
        this.holograms.add(new Hologram(line));
        this.display(this.location);
    }
}
