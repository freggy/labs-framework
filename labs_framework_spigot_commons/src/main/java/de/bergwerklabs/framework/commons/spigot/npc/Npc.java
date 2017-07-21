package de.bergwerklabs.framework.commons.spigot.npc;

import com.comphenix.protocol.wrappers.*;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.sun.jna.platform.win32.NTSecApi;
import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;
import de.bergwerklabs.framework.commons.spigot.hologram.compound.GlobalHologramCompound;
import de.bergwerklabs.framework.commons.spigot.hologram.compound.PlayerHologramCompound;
import de.bergwerklabs.framework.commons.spigot.nms.MinecraftVersion;
import de.bergwerklabs.framework.commons.spigot.nms.packet.entitydestroy.WrapperPlayServerEntityDestroy;
import de.bergwerklabs.framework.commons.spigot.nms.packet.entityheadrotation.EntityHeadRotationBuilder;
import de.bergwerklabs.framework.commons.spigot.nms.packet.entityheadrotation.EntityHeadRotationPacket;
import de.bergwerklabs.framework.commons.spigot.nms.packet.entityequipment.v1_8.WrapperPlayServerEntityEquipment;
import de.bergwerklabs.framework.commons.spigot.nms.packet.namedentityspawn.v1_8.WrapperPlayServerNamedEntitySpawn;
import de.bergwerklabs.framework.commons.spigot.nms.packet.entitiylook.v1_8.WrapperPlayServerEntityLook;
import de.bergwerklabs.framework.commons.spigot.nms.packet.v1_8.WrapperPlayServerPlayerInfo;
import de.bergwerklabs.util.entity.EntityUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * Created by Yannic Rieger on 12.07.2017.
 * <p> Class for creating NPCs.
 *
 * @author Yannic Rieger
 */
public abstract class Npc {

    /**
     * Gets the entity id of this NPC.
     */
    public int getEntityId() {
        return entityId;
    }

    /**
     * Gets the {@link Location} where this NPC is located.
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     */
    public Map<EnumWrappers.ItemSlot, ItemStack> getEquipment() { return this.equipment; }

    protected int entityId;
    protected Location location;
    private PlayerSkin skin;
    protected HashMap<EnumWrappers.ItemSlot, ItemStack> equipment = new HashMap<>();

    protected GameProfile gameProfile;
    protected WrapperPlayServerNamedEntitySpawn spawnPacket = new WrapperPlayServerNamedEntitySpawn();
    protected WrapperPlayServerEntityEquipment entityEquipmentPacket = new WrapperPlayServerEntityEquipment();
    protected WrapperPlayServerPlayerInfo info = new WrapperPlayServerPlayerInfo();
    protected WrapperPlayServerEntityLook entityLookPacket = new WrapperPlayServerEntityLook();
    protected WrapperPlayServerEntityDestroy entityDestroyPacket = new WrapperPlayServerEntityDestroy();
    protected WrappedDataWatcher watcher = new WrappedDataWatcher();

    protected EntityHeadRotationPacket entityHeadRotationPacket;


    public Npc() {}


    public Npc(Location location, PlayerSkin skin, String name) {
        this.location = location;

        try {
            this.entityId = EntityUtil.getNewNMSID();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        this.gameProfile = new GameProfile(UUID.randomUUID(), name);
        this.spawnPacket.setEntityID(this.entityId);
        this.spawnPacket.setPosition(location.toVector());
        this.spawnPacket.setMetadata(this.getMetadata());
        this.spawnPacket.setPlayerUUID(this.gameProfile.getId());
        this.spawnPacket.setYaw(this.location.getYaw());
        this.spawnPacket.setPitch(this.location.getPitch());

        this.entityHeadRotationPacket = new EntityHeadRotationBuilder(MinecraftVersion.formString(LabsReflection.getVersion()))
                .setEntityId(this.entityId)
                .setHeadYaw(this.location.getYaw())
                .build();
    }

    /**
     *
     * @return
     */
    protected WrappedDataWatcher getMetadata()  {
        this.watcher.setObject(10, (byte) 127);
        this.watcher.setObject(6, 20.0F);
        return watcher;
    }

    /**
     *
     * @param pitch
     * @param yaw
     */
    public abstract void setHeadRotation(float pitch, float yaw);

    /**
     *
     * @param skin
     */
    public abstract void setSkin(PlayerSkin skin);

    /**
     *
     * @param itemStack
     */
    public abstract void setEquipment(EnumWrappers.ItemSlot slot, ItemStack itemStack);

    /**
     *
     */
    public abstract void spawn();

    /**
     *
     */
    public abstract void despawn();

    /**
     *
     * @param player
     * @param action
     */
    protected void handleTabList(Player player, EnumWrappers.PlayerInfoAction action) {
        PlayerInfoData playerData = new PlayerInfoData(new WrappedGameProfile(gameProfile.getId(),
                                                                              gameProfile.getName()), 1,
                                                       EnumWrappers.NativeGameMode.NOT_SET,
                                                       WrappedChatComponent.fromText(gameProfile.getName()));
        this.info.setData(Arrays.asList(playerData));
        this.info.setAction(action);
        this.info.sendPacket(player);
    }

}
