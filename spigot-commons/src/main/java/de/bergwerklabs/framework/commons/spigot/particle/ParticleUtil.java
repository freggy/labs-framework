package de.bergwerklabs.framework.commons.spigot.particle;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import de.bergwerklabs.framework.commons.math.vector.Vector3F;
import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ParticleUtil {

  public static void spawnParticle(
      Player player, EnumWrappers.Particle particle, int amount, Vector3F position) {
    spawnParticle(player, particle, amount, position, new Vector3F(), 0.0f, false);
  }

  public static void spawnParticle(
      Player player,
      EnumWrappers.Particle particle,
      int amount,
      Vector3F position,
      Vector3F maxOffset) {
    spawnParticle(player, particle, amount, position, maxOffset, 0.0f, false);
  }

  public static void spawnParticle(
      Player player,
      EnumWrappers.Particle particle,
      int amount,
      Vector3F position,
      Vector3F maxOffset,
      float data) {
    spawnParticle(player, particle, amount, position, maxOffset, data, false);
  }

  public static void spawnParticle(
      Player player,
      EnumWrappers.Particle particle,
      int amount,
      Vector3F position,
      Vector3F maxOffset,
      float data,
      boolean longDistance) {
    PacketContainer container =
        createPacket(particle, amount, position, maxOffset, data, longDistance);
    try {
      SpigotCommons.getInstance().getProtocolManager().sendServerPacket(player, container);
    } catch (InvocationTargetException ignored) {
    }
  }

  public static void broadcastParticle(
      EnumWrappers.Particle particle, int amount, Vector3F position) {
    broadcastParticle(particle, amount, position, new Vector3F(), 0.0f, false);
  }

  public static void broadcastParticle(
      EnumWrappers.Particle particle, int amount, Vector3F position, Vector3F maxOffset) {
    broadcastParticle(particle, amount, position, maxOffset, 0.0f, false);
  }

  public static void broadcastParticle(
      EnumWrappers.Particle particle,
      int amount,
      Vector3F position,
      Vector3F maxOffset,
      float data) {
    broadcastParticle(particle, amount, position, maxOffset, data, false);
  }

  public static void broadcastParticle(
      EnumWrappers.Particle particle,
      int amount,
      Vector3F position,
      Vector3F maxOffset,
      float data,
      boolean longDistance) {
    PacketContainer container =
        createPacket(particle, amount, position, maxOffset, data, longDistance);
    Bukkit.getServer()
        .getOnlinePlayers()
        .forEach(
            player -> {
              try {
                SpigotCommons.getInstance()
                    .getProtocolManager()
                    .sendServerPacket(player, container);
              } catch (InvocationTargetException ignored) {
              }
            });
  }

  public static void broadcastParticle(
      Collection<Player> players, EnumWrappers.Particle particle, int amount, Vector3F position) {
    broadcastParticle(players, particle, amount, position, new Vector3F(), 0.0f, false);
  }

  public static void broadcastParticle(
      Collection<Player> players,
      EnumWrappers.Particle particle,
      int amount,
      Vector3F position,
      Vector3F maxOffset) {
    broadcastParticle(players, particle, amount, position, maxOffset, 0.0f, false);
  }

  public static void broadcastParticle(
      Collection<Player> players,
      EnumWrappers.Particle particle,
      int amount,
      Vector3F position,
      Vector3F maxOffset,
      float data) {
    broadcastParticle(players, particle, amount, position, maxOffset, data, false);
  }

  public static void broadcastParticle(
      Collection<Player> players,
      EnumWrappers.Particle particle,
      int amount,
      Vector3F position,
      Vector3F maxOffset,
      float data,
      boolean longDistance) {
    PacketContainer container =
        createPacket(particle, amount, position, maxOffset, data, longDistance);
    players.forEach(
        player -> {
          try {
            SpigotCommons.getInstance().getProtocolManager().sendServerPacket(player, container);
          } catch (InvocationTargetException ignored) {
          }
        });
  }

  private static PacketContainer createPacket(
      EnumWrappers.Particle particle,
      int amount,
      Vector3F position,
      Vector3F maxOffset,
      float data,
      boolean longDistance) {
    PacketContainer container =
        SpigotCommons.getInstance()
            .getProtocolManager()
            .createPacket(PacketType.Play.Server.WORLD_PARTICLES);
    container.getParticles().write(0, particle);
    container.getFloat().write(0, position.getX()); // X
    container.getFloat().write(1, position.getY()); // Y
    container.getFloat().write(2, position.getZ()); // Z
    container.getFloat().write(3, maxOffset.getX()); // OFFSET X
    container.getFloat().write(4, maxOffset.getY()); // OFFSET Y
    container.getFloat().write(5, maxOffset.getZ()); // OFFSET Z
    container.getFloat().write(6, data); // DATA
    container.getIntegers().write(0, amount); // COUNT
    container.getBooleans().write(0, longDistance); // LONG DISTANCE
    return container;
  }
}
