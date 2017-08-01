package de.bergwerklabs.framework.commons.spigot.entity.hologram;

import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.entity.Entity;
import de.bergwerklabs.framework.commons.spigot.entity.EntityManager;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entitydestroy.WrapperPlayServerEntityDestroy;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.spawnentityliving.v1_8.WrapperPlayServerSpawnEntityLiving;
import de.bergwerklabs.util.entity.EntityUtil;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * Created by Yannic Rieger on 13.07.2017.
 * <p> This class implements floating text and {@link org.bukkit.inventory.ItemStack}s in Minecraft.
 *     internally this is implemented by using invisible armor stands.
 *
 * @author Yannic Rieger
 */
public abstract class Hologram extends Entity {

    public String getText() { return this.line; }

    public Location getLocation() { return this.location; }

    protected String line;
    protected Location location;

    protected WrapperPlayServerSpawnEntityLiving entityPacket = new WrapperPlayServerSpawnEntityLiving();
    protected WrapperPlayServerEntityDestroy entityDestroyPacket = new WrapperPlayServerEntityDestroy();
    protected WrappedDataWatcher watcher = new WrappedDataWatcher();

    public Hologram(String line, Location location) {
        this.line = line;

        this.entityPacket.setEntityID(this.entityId);

        this.entityPacket.setType(EntityType.ARMOR_STAND);

        this.location = location.clone();
        this.entityPacket.setHeadPitch(location.getPitch());
        this.entityPacket.setPitch(this.location.getPitch());
        this.entityPacket.setYaw(this.location.getYaw());
        this.entityPacket.setX(this.location.getX());
        this.entityPacket.setY(this.location.getY());
        this.entityPacket.setZ(this.location.getZ());

        this.entityPacket.setVelocityX(0);
        this.entityPacket.setVelocityY(0);
        this.entityPacket.setVelocityZ(0);

        this.entityPacket.setMetadata(this.getMetadata());

        EntityManager.getEntities().put(this.entityId, this);
    }

    public Hologram(String line, Location location, WrappedDataWatcher metadata) {
        this.line = line;

        this.entityPacket.setEntityID(this.entityId);

        this.entityPacket.setType(EntityType.ARMOR_STAND);

        this.location = location.clone();
        this.entityPacket.setHeadPitch(location.getPitch());
        this.entityPacket.setPitch(this.location.getPitch());
        this.entityPacket.setYaw(this.location.getYaw());
        this.entityPacket.setX(this.location.getX());
        this.entityPacket.setY(this.location.getY());
        this.entityPacket.setZ(this.location.getZ());

        this.entityPacket.setVelocityX(0);
        this.entityPacket.setVelocityY(0);
        this.entityPacket.setVelocityZ(0);

        metadata.setObject(2, this.line);

        this.entityPacket.setMetadata(metadata);

        EntityManager.getEntities().put(this.entityId, this);
    }



    /**
     *
     * @return
     */
    protected WrappedDataWatcher getMetadata() {
        byte dataEntity = 0;
        dataEntity |= 0x20;

        byte armorStandData = 0;
        armorStandData |= 0x10;

        this.watcher.setObject(0, dataEntity);
        this.watcher.setObject(2, this.line);
        this.watcher.setObject(3, (byte) 1);
        this.watcher.setObject(11, armorStandData);
        return this.watcher;
    }

    public void handleRespawn(Player player) {
        this.entityPacket.sendPacket(player);
    }

    public void handleJoin(Player player) {
        this.entityPacket.sendPacket(player);
    }

    public void handleDespawn(Player player) {
        this.entityDestroyPacket.setEntityIds(new int[] { this.entityId });
        this.entityDestroyPacket.sendPacket(player);
    }

    /**
     *
     * @param player
     * @param to
     * @param from
     */
    public void handleMove(Player player, Location to, Location from) {
        if (SpigotCommons.getInstance().getJoiningPlayers().contains(player.getUniqueId())) return;

        if (!this.getLocation().getWorld().getName().equals(to.getWorld().getName())) return;

        // TODO: use LocationUtil#calculateDistanceFast
        double distanceTo = this.getLocation().distanceSquared(to);
        double distanceFrom = this.getLocation().distanceSquared(from);

        if (distanceTo < this.drawDistanceSquared && distanceFrom > drawDistanceSquared) {
            this.handleJoin(player);
        }
        else if (distanceTo > drawDistanceSquared && distanceFrom < drawDistanceSquared) {
            this.handleDespawn(player);
        }
    }

    public void handleTeleport(PlayerTeleportEvent e) {
        Location from = e.getFrom();
        Location to = e.getTo();
        String currentWorldName = this.getLocation().getWorld().getName();

        if (currentWorldName.equals(from.getWorld().getName()) && currentWorldName.equals(to.getWorld().getName())) {
            this.handleMove(e.getPlayer(), to, from);
        }
    }
}
