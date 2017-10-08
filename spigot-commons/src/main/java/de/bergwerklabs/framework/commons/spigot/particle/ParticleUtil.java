package de.bergwerklabs.framework.commons.spigot.particle;

import com.comphenix.protocol.wrappers.EnumWrappers;
import de.bergwerklabs.framework.commons.math.vector.Vector3F;
import de.bergwerklabs.framework.commons.spigot.nms.packet.v1_8.WrapperPlayServerWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

public class ParticleUtil {

    public static void spawnParticle(Player player, EnumWrappers.Particle particle, Vector3F position) {
        spawnParticle(player, particle, position, new Vector3F(), 0.0f, false);
    }

    public static void spawnParticle(Player player, EnumWrappers.Particle particle, Vector3F position, Vector3F maxOffset) {
        spawnParticle(player, particle, position, maxOffset, 0.0f, false);
    }

    public static void spawnParticle(Player player, EnumWrappers.Particle particle, Vector3F position, Vector3F maxOffset, float data) {
        spawnParticle(player, particle, position, maxOffset, data, false);
    }

    public static void spawnParticle(Player player, EnumWrappers.Particle particle, Vector3F position, Vector3F maxOffset, float data, boolean longDistance) {
        createPacket(particle, position, maxOffset, data, longDistance).sendPacket(player);
    }

    public static void broadcastParticle(EnumWrappers.Particle particle, Vector3F position) {
        broadcastParticle(particle, position, new Vector3F(), 0.0f, false);
    }

    public static void broadcastParticle(EnumWrappers.Particle particle, Vector3F position, Vector3F maxOffset) {
        broadcastParticle(particle, position, maxOffset, 0.0f, false);
    }

    public static void broadcastParticle(EnumWrappers.Particle particle, Vector3F position, Vector3F maxOffset, float data) {
        broadcastParticle(particle, position, maxOffset, data, false);
    }

    public static void broadcastParticle(EnumWrappers.Particle particle, Vector3F position, Vector3F maxOffset, float data, boolean longDistance) {
        WrapperPlayServerWorldParticles packet = createPacket(particle, position, maxOffset, data, longDistance);
        Bukkit.getServer().getOnlinePlayers().forEach(packet::sendPacket);
    }

    public static void broadcastParticle(Collection<Player> players, EnumWrappers.Particle particle, Vector3F position) {
        broadcastParticle(players, particle, position, new Vector3F(), 0.0f, false);
    }

    public static void broadcastParticle(Collection<Player> players, EnumWrappers.Particle particle, Vector3F position, Vector3F maxOffset) {
        broadcastParticle(players, particle, position, maxOffset, 0.0f, false);
    }

    public static void broadcastParticle(Collection<Player> players, EnumWrappers.Particle particle, Vector3F position, Vector3F maxOffset, float data) {
        broadcastParticle(players, particle, position, maxOffset, data, false);
    }

    public static void broadcastParticle(Collection<Player> players, EnumWrappers.Particle particle, Vector3F position, Vector3F maxOffset, float data, boolean longDistance) {
        WrapperPlayServerWorldParticles packet = createPacket(particle, position, maxOffset, data, longDistance);
        players.forEach(packet::sendPacket);
    }

    private static WrapperPlayServerWorldParticles createPacket(EnumWrappers.Particle particle, Vector3F position, Vector3F maxOffset, float data, boolean longDistance) {
        WrapperPlayServerWorldParticles packet = new WrapperPlayServerWorldParticles();
        packet.setParticleType(particle);
        packet.setX(position.getX());
        packet.setX(position.getY());
        packet.setX(position.getZ());
        packet.setOffsetX(maxOffset.getX());
        packet.setOffsetY(maxOffset.getY());
        packet.setOffsetZ(maxOffset.getZ());
        packet.setParticleData(data);
        packet.setLongDistance(longDistance);
        return packet;
    }
}
