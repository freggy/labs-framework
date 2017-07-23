package de.bergwerklabs.framework.commons.spigot.entity.npc;

import com.comphenix.protocol.wrappers.*;
import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.entity.Entity;
import de.bergwerklabs.framework.commons.spigot.entity.EntityManager;
import de.bergwerklabs.framework.commons.spigot.entity.npc.behavior.AbstractBehavior;
import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;
import de.bergwerklabs.framework.commons.spigot.nms.MinecraftVersion;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entitydestroy.WrapperPlayServerEntityDestroy;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entityheadrotation.EntityHeadRotationBuilder;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entityheadrotation.EntityHeadRotationPacket;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entityequipment.v1_8.WrapperPlayServerEntityEquipment;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.namedentityspawn.v1_8.WrapperPlayServerNamedEntitySpawn;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entitylook.v1_8.WrapperPlayServerEntityLook;
import de.bergwerklabs.framework.commons.spigot.nms.packet.v1_8.WrapperPlayServerPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * Created by Yannic Rieger on 12.07.2017.
 * <p> Class for creating NPCs.
 *
 * @author Yannic Rieger
 */
public abstract class Npc extends Entity {

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
     * @return
     */
    public String getName() { return this.name; }

    /**
     *
     * @param skin
     */
    public void setSkin(PlayerSkin skin) {
        this.skin = skin;
        this.skin.inject(this.gameProfile);
    }

    /**
     *
     * @param
     */
    public void setName(String name) {
        // TODO: set name
        this.gameProfile = new WrappedGameProfile(this.getUuid(), name);
    }

    protected Location location;
    protected PlayerSkin skin;
    protected String name;
    protected HashMap<EnumWrappers.ItemSlot, ItemStack> equipment = new HashMap<>();

    protected WrappedGameProfile gameProfile;
    protected WrapperPlayServerNamedEntitySpawn spawnPacket = new WrapperPlayServerNamedEntitySpawn();
    protected WrapperPlayServerEntityEquipment entityEquipmentPacket = new WrapperPlayServerEntityEquipment();
    protected WrapperPlayServerPlayerInfo info = new WrapperPlayServerPlayerInfo();
    protected WrapperPlayServerEntityLook entityLookPacket = new WrapperPlayServerEntityLook();
    protected WrapperPlayServerEntityDestroy entityDestroyPacket = new WrapperPlayServerEntityDestroy();
    protected WrappedDataWatcher watcher = new WrappedDataWatcher();
    protected EntityHeadRotationPacket entityHeadRotationPacket;

    protected Npc(Location location, String name) {
        super();
        this.location = location;

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

        EntityManager.getEntities().put(this.entityId, this);
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
    public abstract NpcType getType();

    /**
     *
     */
    public void updateSkin() {
        this.despawn();
        this.spawn();
    }

    /**
     *
     * @param skin
     */
    public void updateSkin(PlayerSkin skin) {
        this.despawn();
        skin.inject(this.gameProfile);
        this.spawn();
    }

    /**
     *
     * @param behavior
     */
    public void addBehavior(AbstractBehavior behavior) {
        behavior.setNpc(this);
        Bukkit.getServer().getPluginManager().registerEvents(behavior, SpigotCommons.getInstance());
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
        Bukkit.getScheduler().runTaskLater(SpigotCommons.getInstance(), () -> this.handleTabList(player, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER), 60L);
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
    public void handleRespawn(Player player) {
        this.sendNpcData(player);
    }

    /**
     *
     * @param player
     */
    public void handleJoin(Player player) {
        this.sendNpcData(player);
    }

    /**
     *
     * @param player
     */
    public void handleSpawn(Player player) {
        this.sendNpcData(player);
    }

    /**
     *
     * @param player
     */
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
            this.handleSpawn(player);
        }
        else if (distanceTo > drawDistanceSquared && distanceFrom < drawDistanceSquared) {
            this.handleDespawn(player);
        }
    }

    /**
     *
     * @param e
     */
    public void handleTeleport(PlayerTeleportEvent e) {
        Location from = e.getFrom();
        Location to = e.getTo();
        String currentWorldName = this.getLocation().getWorld().getName();

        if (currentWorldName.equals(from.getWorld().getName()) && currentWorldName.equals(to.getWorld().getName())) {
            this.handleMove(e.getPlayer(), to, from);
        }
    }
}
