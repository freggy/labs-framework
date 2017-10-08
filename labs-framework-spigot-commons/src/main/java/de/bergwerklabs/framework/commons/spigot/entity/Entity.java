package de.bergwerklabs.framework.commons.spigot.entity;

import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.lang.reflect.Field;

/**
 * Created by Yannic Rieger on 22.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public abstract class Entity {

    /**
     *
     */
    public double getDrawDistance() { return this.drawDistanceSquared; }

    /**
     *
     */
    public boolean isVisible() { return this.isVisible; }

    /**
     *
     */
    public int getEntityId() { return this.entityId; }

    protected double drawDistanceSquared = 30 * 30;
    protected boolean isVisible = false;
    protected int entityId;

    protected Entity() {
//        try {
//            this.entityId = EntityUtil.getNewNMSID();
//        }
//        catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }

    public abstract void spawn();

    public abstract void despawn();

    public abstract void handleDespawn(Player player);

    public abstract void handleJoin(Player player);

    public abstract void handleRespawn(Player player);

    public abstract void handleMove(Player player, Location to, Location from);

    public abstract void handleTeleport(PlayerTeleportEvent e);
}
