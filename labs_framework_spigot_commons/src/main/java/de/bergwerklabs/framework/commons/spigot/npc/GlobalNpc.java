package de.bergwerklabs.framework.commons.spigot.npc;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.mojang.authlib.properties.Property;
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

    @Override
    public NpcType getType() { return NpcType.GLOBAL; }

    private GlobalHologramCompound compound;

    public GlobalNpc(Location location, PlayerSkin skin, GlobalHologramCompound compound) {
        super(location, compound.getHolograms().getLast().getText());
        compound.getHolograms().remove(compound.getHolograms().getLast());
        this.compound = compound;
    }

    @Override
    public void spawn() {
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

    @Override
    public void setSkin(PlayerSkin skin) {
        this.despawn();
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

    @Override
    public void updateSkin() {
        this.despawn();
        this.spawn();
    }

    @Override
    public void updateSkin(PlayerSkin skin) {
        this.despawn();
        skin.inject(this.gameProfile);
        this.spawn();
    }

    @Override
    void handleSpawn(Player player) {
        this.compound.display(this.location.clone().add(0, 1.64, 0));
        this.sendNpcData(player);
    }

    @Override
    void handleDespawn(Player player) {
        this.compound.getHolograms().forEach(hologram -> hologram.destroy(player));
        this.entityDestroyPacket.setEntityIds(new int[] { this.entityId });
        this.entityDestroyPacket.sendPacket(player);
    }
}
