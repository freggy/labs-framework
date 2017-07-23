package de.bergwerklabs.framework.commons.spigot.entity.hologram;

import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by Yannic Rieger on 22.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class GlobalHologram extends Hologram {

    public GlobalHologram(String line, Location location) {
        super(line, location);
    }

    @Override
    public void spawn() {
        this.isVisible = true;
        Bukkit.getOnlinePlayers().forEach(player -> {
            this.entityPacket.sendPacket(player);
        });
    }

    @Override
    public void despawn() {
        this.isVisible = false;
        Bukkit.getOnlinePlayers().forEach(player -> {
            this.entityDestroyPacket.sendPacket(player);
        });
    }
}
