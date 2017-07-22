package de.bergwerklabs.framework.commons.spigot.hologram;

import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entitydestroy.WrapperPlayServerEntityDestroy;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.spawnentityliving.v1_8.WrapperPlayServerSpawnEntityLiving;
import de.bergwerklabs.util.entity.EntityUtil;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/**
 * Created by Yannic Rieger on 13.07.2017.
 * <p> This class implements floating text and {@link org.bukkit.inventory.ItemStack}s in Minecraft.
 *     internally this is implemented by using invisible armor stands.
 *
 * @author Yannic Rieger
 */
public class Hologram {

    public String getText() { return this.line; }

    private String line;
    protected Location location;
    private int entityId;

    private WrapperPlayServerSpawnEntityLiving entityPacket = new WrapperPlayServerSpawnEntityLiving();
    private WrapperPlayServerEntityDestroy entityDestroyPacket = new WrapperPlayServerEntityDestroy();
    private WrappedDataWatcher watcher = new WrappedDataWatcher();

    public Hologram(String line) {
        this.line = line;

        try {
            this.entityId = EntityUtil.getNewNMSID();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        HologramManager.getHolograms().add(this);
    }

    /**
     *
     * @param player
     * @param location
     */
    public void display(Player player, Location location) {
        this.location = location.clone().subtract(0, 1.85, 0);
        this.entityPacket.setEntityID(this.entityId);

        this.entityPacket.setHeadPitch(location.getPitch());
        this.entityPacket.setPitch(this.location.getPitch());
        this.entityPacket.setYaw(this.location.getYaw());

        this.entityPacket.setType(EntityType.ARMOR_STAND);

        this.entityPacket.setX(this.location.getX());
        this.entityPacket.setY(this.location.getY());
        this.entityPacket.setZ(this.location.getZ());

        this.entityPacket.setVelocityX(0);
        this.entityPacket.setVelocityY(0);
        this.entityPacket.setVelocityZ(0);

        this.entityPacket.setMetadata(this.getMetadata());
        this.entityPacket.sendPacket(player);
    }

    /**
     *
     * @param player
     */
    public void destroy(Player player) {
        this.entityDestroyPacket.setEntityIds(new int[] { this.entityId });
        this.entityDestroyPacket.sendPacket(player);
    }

    /**
     *
     * @return
     */
    protected WrappedDataWatcher getMetadata() {
        byte data = 0;
        data |= 0x20;

        this.watcher.setObject(0, data);
        this.watcher.setObject(2, this.line);
        this.watcher.setObject(3, (byte) 1);
        return this.watcher;
    }

    void handleRespawn(Player player) {
        this.entityPacket.sendPacket(player);
    }

    void handleJoin(Player player) {
        this.entityPacket.sendPacket(player);
    }
}
