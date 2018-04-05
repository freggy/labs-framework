package de.bergwerklabs.framework.commons.spigot.general;

import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import de.bergwerklabs.framework.commons.spigot.entity.EntityUtil;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entitydestroy.WrapperPlayServerEntityDestroy;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.spawnentityliving.v1_8.WrapperPlayServerSpawnEntityLiving;
import de.bergwerklabs.framework.commons.spigot.nms.packet.v1_8.WrapperPlayServerEntityMetadata;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.lang.management.PlatformLoggingMXBean;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Yannic Rieger on 05.04.2018.
 * <p>
 *
 * @author Yannic Rieger
 */
public class BossBar {

    private String text;
    private WrapperPlayServerSpawnEntityLiving entityLiving = new WrapperPlayServerSpawnEntityLiving();

    public BossBar(String text) {
        this.text = text;
        this.entityLiving.setEntityID(EntityUtil.getNmsId());
        this.entityLiving.setType(EntityType.ENDER_DRAGON);
        this.entityLiving.setX(0.0);
        this.entityLiving.setY(0.0);
        this.entityLiving.setZ(0.0);
        this.entityLiving.setVelocityX(0.0);
        this.entityLiving.setVelocityY(0.0);
        this.entityLiving.setVelocityZ(0.0);

        WrappedDataWatcher watcher = new WrappedDataWatcher();
        watcher.setObject(0, (byte)0x20);
        watcher.setObject(6, 100F);
        watcher.setObject(2, text);
        watcher.setObject(11, (byte)1);
        watcher.setObject(3, (byte)1);
        this.entityLiving.setMetadata(watcher);
    }

    public void sendBar(Player player) {
        this.entityLiving.sendPacket(player);
    }

    public void updateBar(Player player, String text) {
        WrapperPlayServerEntityMetadata metadata = new WrapperPlayServerEntityMetadata();
        WrappedDataWatcher watcher = this.getPreconfiguredWatcher();
        watcher.setObject(2, text);
        metadata.setEntityID(this.entityLiving.getEntityID());
        metadata.setMetadata(watcher.getWatchableObjects());
        metadata.sendPacket(player);
    }

    public void updateBar(Player player, String text, float healtPercentage) {
        WrapperPlayServerEntityMetadata metadata = new WrapperPlayServerEntityMetadata();
        WrappedDataWatcher watcher = this.getPreconfiguredWatcher();
        if (text != null) watcher.setObject(2, text);
        if (healtPercentage != -1) watcher.setObject(6, healtPercentage);
        watcher.setObject(8, (byte)3);
        metadata.setEntityID(this.entityLiving.getEntityID());
        metadata.setMetadata(watcher.getWatchableObjects());
        metadata.sendPacket(player);
    }

    public void updateBar(Player player, float healtPercentage) {
        WrapperPlayServerEntityMetadata metadata = new WrapperPlayServerEntityMetadata();
        WrappedDataWatcher watcher = this.getPreconfiguredWatcher();
        watcher.setObject(6, healtPercentage);
        metadata.setEntityID(this.entityLiving.getEntityID());
        metadata.setMetadata(watcher.getWatchableObjects());
        metadata.sendPacket(player);
    }

    public void remove(Player player) {
        WrapperPlayServerEntityDestroy destroy = new WrapperPlayServerEntityDestroy();
        destroy.setEntityIds(new int[] {this.entityLiving.getEntityID()});
        destroy.sendPacket(player);
    }

    private WrappedDataWatcher getPreconfiguredWatcher() {
        WrappedDataWatcher watcher = new WrappedDataWatcher();
        watcher.setObject(0, (byte)0x20);
        watcher.setObject(11, (byte)1);
        watcher.setObject(3, (byte)1);
        return watcher;
    }
}
