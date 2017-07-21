package de.bergwerklabs.framework.commons.spigot.npc;

import com.comphenix.protocol.wrappers.EnumWrappers;
import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.hologram.compound.GlobalHologramCompound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Yannic Rieger on 20.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class GlobalNpc extends Npc {

    private GlobalHologramCompound compound;

    public GlobalNpc(Location location, PlayerSkin skin, GlobalHologramCompound compound) {
        super(location, skin, compound.getHolograms().getLast().getText());
        compound.getHolograms().remove(compound.getHolograms().getLast());
        this.compound = compound;
        if (skin != null) skin.inject(this.gameProfile);
        NpcManager.getGlobalNpcs().put(this.entityId, this);
    }

    /**
     *
     */
    @Override
    public void spawn() {
        this.compound.display(this.location.clone().add(0, 1.64, 0));
        Bukkit.getOnlinePlayers().forEach(this::handleSpawn);
    }

    @Override
    public void despawn() {
        this.compound.destroy();
        Bukkit.getOnlinePlayers().forEach(player -> {
            this.entityDestroyPacket.setEntityIds(new int[] { this.entityId });
            this.entityDestroyPacket.sendPacket(player);
        });
    }

    /**
     *
     * @param player
     */
    void spawnSingle(Player player) {
        this.handleSpawn(player);
    }

    @Override
    public void setSkin(PlayerSkin skin) {
        skin.inject(this.gameProfile);
        this.spawn();
    }

    @Override
    public void setEquipment(EnumWrappers.ItemSlot slot, ItemStack itemStack) {
        this.equipment.putIfAbsent(slot, itemStack);
        this.sendFullEquipment();
    }

    @Override
    public void setHeadRotation(float pitch, float yaw) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            this.entityLookPacket.setEntityID(this.entityId);
            this.entityLookPacket.setOnGround(true);
            this.entityLookPacket.setPitch(pitch);
            this.entityLookPacket.setYaw(yaw);

            this.entityHeadRotationPacket.setEntityId(this.entityId);
            this.entityHeadRotationPacket.setHeadYaw(yaw);

            this.entityLookPacket.sendPacket(player);
            this.entityHeadRotationPacket.sendPacket(player);
        });
    }

    /**
     *
     */
    private void sendFullEquipment() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            this.equipment.forEach((slot, itemStack) -> {
                this.entityEquipmentPacket.setSlot(slot);
                this.entityEquipmentPacket.setItem(itemStack);
                this.entityEquipmentPacket.setEntityID(this.entityId);
                this.entityEquipmentPacket.sendPacket(player);
            });
        });
    }

    private void handleSpawn(Player player) {
        // The PlayerInfo packet has to be sent BEFORE sending the PacketPlayOutNamedEntitySpawn
        // because otherwise the client will not render the npc.
        this.handleTabList(player, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        this.spawnPacket.sendPacket(player);
        this.entityHeadRotationPacket.sendPacket(player);
        this.sendFullEquipment();
        Bukkit.getScheduler().runTaskLater(SpigotCommons.getInstance(), () -> this.handleTabList(player, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER), 5L);
    }
}
