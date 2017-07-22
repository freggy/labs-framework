package de.bergwerklabs.framework.commons.spigot.npc;

import com.comphenix.protocol.wrappers.*;
import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;
import de.bergwerklabs.framework.commons.spigot.nms.MinecraftVersion;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entitydestroy.WrapperPlayServerEntityDestroy;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entityheadrotation.EntityHeadRotationBuilder;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entityheadrotation.EntityHeadRotationPacket;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entityequipment.v1_8.WrapperPlayServerEntityEquipment;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.namedentityspawn.v1_8.WrapperPlayServerNamedEntitySpawn;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entitylook.v1_8.WrapperPlayServerEntityLook;
import de.bergwerklabs.framework.commons.spigot.nms.packet.v1_8.WrapperPlayServerPlayerInfo;
import de.bergwerklabs.util.entity.EntityUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
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

    /**
     *
     * @return
     */
    public UUID getUuid() { return this.gameProfile.getUUID(); }

    /**
     *
     * @param skin
     */
    public void setSkin(PlayerSkin skin) {
        this.skin = skin;
        this.skin.inject(this.gameProfile);
    }

    protected int entityId;
    protected Location location;
    private PlayerSkin skin;
    protected HashMap<EnumWrappers.ItemSlot, ItemStack> equipment = new HashMap<>();

    protected WrappedGameProfile gameProfile;
    protected WrapperPlayServerNamedEntitySpawn spawnPacket = new WrapperPlayServerNamedEntitySpawn();
    protected WrapperPlayServerEntityEquipment entityEquipmentPacket = new WrapperPlayServerEntityEquipment();
    protected WrapperPlayServerPlayerInfo info = new WrapperPlayServerPlayerInfo();
    protected WrapperPlayServerEntityLook entityLookPacket = new WrapperPlayServerEntityLook();
    protected WrapperPlayServerEntityDestroy entityDestroyPacket = new WrapperPlayServerEntityDestroy();
    protected WrappedDataWatcher watcher = new WrappedDataWatcher();

    protected EntityHeadRotationPacket entityHeadRotationPacket;

    private final double drawDistanceSquared = 20 * 20; // TODO: make configurable

    private Npc() {}

    protected Npc(Location location, String name) {
        this.location = location;

        try {
            this.entityId = EntityUtil.getNewNMSID();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        this.gameProfile = new WrappedGameProfile(UUID.randomUUID(), name);
        this.spawnPacket.setEntityID(this.entityId);
        this.spawnPacket.setPosition(location.toVector());
        this.spawnPacket.setMetadata(this.getMetadata());
        this.spawnPacket.setPlayerUUID(this.gameProfile.getUUID());
        this.spawnPacket.setYaw(this.location.getYaw());
        this.spawnPacket.setPitch(this.location.getPitch());

        this.entityHeadRotationPacket = new EntityHeadRotationBuilder(MinecraftVersion.formString(LabsReflection.getVersion()))
                .setEntityId(this.entityId)
                .setHeadYaw(this.location.getYaw())
                .build();
        NpcManager.geNpcs().put(this.entityId, this);
    }

    /**
     *
     * @param pitch
     * @param yaw
     */
    public abstract void setHeadRotation(float pitch, float yaw);


    /**
     *
     * @param itemStack
     */
    public abstract void setEquipment(EnumWrappers.ItemSlot slot, ItemStack itemStack);

    /**
     *
     */
    public abstract void updateSkin();

    /**
     *
     */
    public abstract void updateSkin(PlayerSkin skin);

    /**
     *
     */
    public abstract void spawn();

    /**
     *
     */
    public abstract NpcType getType();

    /**
     *
     */
    public abstract void despawn();

    /**
     *
     * @param player
     */
    abstract void handleSpawn(Player player);

    /**
     *
     * @param player
     */
    abstract void handleDespawn(Player player);

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
     * @param player
     * @param action
     */
    protected void handleTabList(Player player, EnumWrappers.PlayerInfoAction action) {

        PlayerInfoData playerData = new PlayerInfoData(this.gameProfile, 1,
                                                       EnumWrappers.NativeGameMode.NOT_SET,
                                                       WrappedChatComponent.fromText(gameProfile.getName()));
        this.info.setData(Arrays.asList(playerData));
        this.info.setAction(action);
        this.info.sendPacket(player);
    }

    /**
     *
     * @param player
     */
    protected void sendNpcData(Player player) {
        this.handleTabList(player, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        this.spawnPacket.sendPacket(player);
        this.entityLookPacket.sendPacket(player);
        this.entityHeadRotationPacket.sendPacket(player);
        this.sendFullEquipment();
        Bukkit.getScheduler().runTaskLater(SpigotCommons.getInstance(), () -> this.handleTabList(player, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER), 5L);
    }

    /**
     *
     */
    protected void sendFullEquipment() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            this.equipment.forEach((slot, itemStack) -> {
                this.entityEquipmentPacket.setSlot(slot);
                this.entityEquipmentPacket.setItem(itemStack);
                this.entityEquipmentPacket.setEntityID(this.entityId);
                this.entityEquipmentPacket.sendPacket(player);
            });
        });
    }

    /**
     *
     * @param player
     */
    void handleRespawn(Player player) {
        this.sendNpcData(player);
    }

    /**
     *
     * @param player
     */
    void handleJoin(Player player) {
        this.sendNpcData(player);
    }

    /**
     *
     * @param e
     */
    void handleMove(PlayerMoveEvent e) {
        if (!this.getLocation().getWorld().getName().equals(e.getTo().getWorld().getName())) return;

        Player player = e.getPlayer();

        double distanceTo = this.getLocation().distanceSquared(e.getTo());
        double distanceFrom = this.getLocation().distanceSquared(e.getFrom());

        if (distanceTo < drawDistanceSquared && distanceFrom > drawDistanceSquared) {
            System.out.println("APPEAR");
            this.handleSpawn(player);
        }
        else if (distanceTo > drawDistanceSquared && distanceFrom < drawDistanceSquared) {
            System.out.println("DISAPPEAR");
            this.handleDespawn(player);
        }
    }

}
