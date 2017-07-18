package de.bergwerklabs.framework.commons.spigot.npc;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.wrappers.*;
import com.mojang.authlib.GameProfile;
import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;
import de.bergwerklabs.framework.commons.spigot.nms.MinecraftVersion;
import de.bergwerklabs.framework.commons.spigot.nms.packet.entityheadrotation.EntityHeadRotationBuilder;
import de.bergwerklabs.framework.commons.spigot.nms.packet.entityheadrotation.EntityHeadRotationPacket;
import de.bergwerklabs.framework.commons.spigot.nms.packet.entityequipment.v1_8.WrapperPlayServerEntityEquipment;
import de.bergwerklabs.framework.commons.spigot.nms.packet.namedentityspawn.v1_8.WrapperPlayServerNamedEntitySpawn;
import de.bergwerklabs.framework.commons.spigot.nms.packet.entitiylook.v1_8.WrapperPlayServerEntityLook;
import de.bergwerklabs.framework.commons.spigot.nms.packet.v1_8.WrapperPlayServerPlayerInfo;
import de.bergwerklabs.util.entity.EntityUtil;
import net.minecraft.server.v1_8_R2.ServerPing;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Yannic Rieger on 12.07.2017.
 * <p> Class for creating NPCs.
 *
 * @author Yannic Rieger
 */
public class Npc {

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



    private int entityId;
    private Location location;
    private HashMap<EnumWrappers.ItemSlot, ItemStack> equipment = new HashMap<>();

    private GameProfile gameProfile;
    private WrapperPlayServerNamedEntitySpawn spawnPacket = new WrapperPlayServerNamedEntitySpawn();
    private WrapperPlayServerEntityEquipment entityEquipmentPacket = new WrapperPlayServerEntityEquipment();
    private WrapperPlayServerPlayerInfo info = new WrapperPlayServerPlayerInfo();
    private WrappedDataWatcher watcher = new WrappedDataWatcher();
    private WrapperPlayServerEntityLook entityLookPacket = new WrapperPlayServerEntityLook();

    private EntityHeadRotationPacket entityHeadRotationPacket;


    public Npc(Location location, String name) {
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

        NpcManager.getNpcs().put(this.entityId, this);
    }

    /**
     * Spawns this NPC.
     *
     * @param player {@link Player} that will see the NPC.
     */
    public void spawn(Player player) {
        // The PlayerInfo packet has to be sent BEFORE sending the PacketPlayOutNamedEntitySpawn
        // because otherwise the client will not render the npc.
        this.handleTabList(player, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        this.spawnPacket.sendPacket(player);
        this.entityHeadRotationPacket.sendPacket(player);
        this.sendFullEquipment(player);
        Bukkit.getScheduler().runTaskLater(SpigotCommons.getInstance(), () -> this.handleTabList(player, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER), 1L);
    }

    /**
     *
     * @param itemSlot
     * @param item
     */
    public void setEquipment(EnumWrappers.ItemSlot itemSlot, ItemStack item) {
        this.equipment.putIfAbsent(itemSlot, item);
    }

    /**
     *
     * @param player
     * @param itemSlot
     * @param item
     */
    public void setEquipment(Player player, EnumWrappers.ItemSlot itemSlot, ItemStack item) {
        this.equipment.putIfAbsent(itemSlot, item);
        this.sendEquipmentPart(player, itemSlot, item);
    }

    /**
     *
     * @param player
     */
    private void sendFullEquipment(Player player) {
        this.equipment.forEach((slot, item) -> this.sendEquipmentPart(player, slot, item));
    }

    /**
     *
     * @param player
     * @param slot
     * @param item
     */
    private void sendEquipmentPart(Player player, EnumWrappers.ItemSlot slot, ItemStack item) {
        this.entityEquipmentPacket.setEntityID(this.entityId);
        this.entityEquipmentPacket.setSlot(slot);
        this.entityEquipmentPacket.setItem(item);
        this.entityEquipmentPacket.sendPacket(player);
    }



    /**
     *
     * @param player
     * @param pitch
     * @param yaw
     */
    public void updateHeadRotation(Player player, float pitch, float yaw) {
        this.entityLookPacket.setEntityID(this.entityId);
        this.entityLookPacket.setOnGround(true);
        this.entityLookPacket.setPitch(pitch);
        this.entityLookPacket.setYaw(yaw);
    }

    /**
     *
     *
     * @return
     */
    private WrappedDataWatcher getMetadata()  {
        this.watcher.setObject(10, (byte) 127);
        this.watcher.setObject(6, 20.0F);
        return watcher;
    }

    /**
     *
     * @param player
     * @param action
     */
    private void handleTabList(Player player, EnumWrappers.PlayerInfoAction action) {
        PlayerInfoData playerData = new PlayerInfoData(new WrappedGameProfile(gameProfile.getId(),
                                                                              gameProfile.getName()), 1,
                                                       EnumWrappers.NativeGameMode.NOT_SET,
                                                       WrappedChatComponent.fromText(gameProfile.getName()));
        this.info.setData(Arrays.asList(playerData));
        this.info.setAction(action);
        this.info.sendPacket(player);
    }
}
