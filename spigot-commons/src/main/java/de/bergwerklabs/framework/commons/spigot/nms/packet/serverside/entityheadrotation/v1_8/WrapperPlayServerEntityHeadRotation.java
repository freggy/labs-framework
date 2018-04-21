/**
 * PacketWrapper - ProtocolLib wrappers for Minecraft packets Copyright (C) dmulloy2
 * <http://dmulloy2.net> Copyright (C) Kristian S. Strangeland
 *
 * <p>This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * <p>You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entityheadrotation.v1_8;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import de.bergwerklabs.framework.commons.spigot.nms.packet.AbstractPacket;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entityheadrotation.EntityHeadRotationPacket;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class WrapperPlayServerEntityHeadRotation extends AbstractPacket
    implements EntityHeadRotationPacket {
  public static final PacketType TYPE = PacketType.Play.Server.ENTITY_HEAD_ROTATION;

  public WrapperPlayServerEntityHeadRotation() {
    super(new PacketContainer(TYPE), TYPE);
    handle.getModifier().writeDefaults();
  }

  public WrapperPlayServerEntityHeadRotation(PacketContainer packet) {
    super(packet, TYPE);
  }

  /**
   * Retrieve Entity ID.
   *
   * <p>Notes: entity's ID
   *
   * @return The current Entity ID
   */
  public Number getEntityId() {
    return handle.getIntegers().read(0);
  }

  /**
   * Set Entity ID.
   *
   * @param value - new value.
   */
  public void setEntityId(Number value) {
    handle.getIntegers().write(0, value.intValue());
  }

  /**
   * Retrieve the entity of the painting that will be spawned.
   *
   * @param world - the current world of the entity.
   * @return The spawned entity.
   */
  public Entity getEntity(World world) {
    return handle.getEntityModifier(world).read(0);
  }

  /**
   * Retrieve the entity of the painting that will be spawned.
   *
   * @param event - the packet event.
   * @return The spawned entity.
   */
  public Entity getEntity(PacketEvent event) {
    return getEntity(event.getPlayer().getWorld());
  }

  /**
   * Retrieve Head Yaw.
   *
   * <p>Notes: head yaw in steps of 2p/256
   *
   * @return The current Head Yaw
   */
  public Number getHeadYaw() {
    return (handle.getBytes().read(0) * 360.F) / 256.0F;
  }

  /**
   * Set Head Yaw.
   *
   * @param value - new value.
   */
  public void setHeadYaw(Number value) {
    handle.getBytes().write(0, (byte) (value.floatValue() * 256.0F / 360.0F));
  }
}
