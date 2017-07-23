package de.bergwerklabs.framework.commons.spigot.entity.npc;

import com.comphenix.protocol.wrappers.EnumWrappers;
import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.entity.hologram.GlobalHologramCompound;
import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;
import de.bergwerklabs.framework.commons.spigot.nms.NmsUtil;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entityheadrotation.v1_8.WrapperPlayServerEntityHeadRotation;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entitylook.v1_8.WrapperPlayServerEntityLook;
import de.bergwerklabs.framework.commons.spigot.nms.packet.v1_8.WrapperPlayServerAttachEntity;
import de.bergwerklabs.util.network.PacketUtil;
import net.amoebaman.util.Reflection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Yannic Rieger on 20.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class GlobalNpc extends Npc {

    @Override
    public NpcType getType() { return NpcType.GLOBAL; }

    private GlobalHologramCompound compound;

    public GlobalNpc(Location location, PlayerSkin skin, String... text) {
        super(location, "");
        this.compound = new GlobalHologramCompound(this.location.clone().add(0, 0.45, 0), text);
        this.setName(this.compound.getHolograms().getLast().getText());
        compound.getHolograms().remove(this.compound.getHolograms().getLast());

        if (skin != null) this.setSkin(skin);
    }

    @Override
    public void spawn() {
        this.isVisible = true;
        this.compound.display();
        Bukkit.getOnlinePlayers().forEach(this::handleSpawn);
    }

    @Override
    public void despawn() {
        this.compound.destroy();
        this.isVisible = false;
        Bukkit.getOnlinePlayers().forEach(player -> {
            this.entityDestroyPacket.setEntityIds(new int[] { this.entityId });
            this.entityDestroyPacket.sendPacket(player);
        });
    }

    @Override
    public void setEquipment(EnumWrappers.ItemSlot slot, ItemStack itemStack) {
        this.equipment.putIfAbsent(slot, itemStack);
        this.sendFullEquipment();
    }

    @Override
    public void setHeadRotation(float pitch, float yaw) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            try {
                // Due to a bug in ProtocolLib we have to do it the Saph way unfortunately.
                Constructor<?> constructor_PacketPlayOutEntityLook  = LabsReflection
                        .getNmsClass("PacketPlayOutEntity$PacketPlayOutEntityLook")
                        .getDeclaredConstructor(int.class, byte.class, byte.class, boolean.class);
                constructor_PacketPlayOutEntityLook.setAccessible(true);

                Object lookPacket = constructor_PacketPlayOutEntityLook.newInstance(this.entityId, NmsUtil.getFixYawPitch(yaw), NmsUtil.getFixYawPitch(pitch), true);

                Object rotationPacket = Reflection.getNMSClass("PacketPlayOutEntityHeadRotation").newInstance();
                LabsReflection.getField(rotationPacket.getClass(), "a").set(rotationPacket, this.entityId);
                LabsReflection.getField(rotationPacket.getClass(), "b").set(rotationPacket, NmsUtil.getFixYawPitch(yaw));

                NmsUtil.sendPacketOverPlayerConnection(player, lookPacket);
                NmsUtil.sendPacketOverPlayerConnection(player, rotationPacket);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public void handleJoin(Player player) {
        this.handleSpawn(player);
    }

    @Override
    public void handleSpawn(Player player) {
        // attach to player because some 1.8 clients will not render the holograms properly
        this.compound.getHolograms().forEach(hologram -> {
            WrapperPlayServerAttachEntity playServerAttachEntity = new WrapperPlayServerAttachEntity();
            playServerAttachEntity.setVehicleId(this.entityId);
            playServerAttachEntity.setEntityID(hologram.getEntityId());
            playServerAttachEntity.sendPacket(player);
        });

        super.handleSpawn(player);
    }

    @Override
    public void handleDespawn(Player player) {
        super.handleDespawn(player);
    }

    @Override
    public void handleTeleport(PlayerTeleportEvent e) {
        super.handleTeleport(e);
    }
}
