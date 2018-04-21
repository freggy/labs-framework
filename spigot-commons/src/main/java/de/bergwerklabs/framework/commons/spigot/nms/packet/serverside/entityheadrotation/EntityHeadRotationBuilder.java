package de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entityheadrotation;

import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;
import de.bergwerklabs.framework.commons.spigot.nms.MinecraftVersion;
import de.bergwerklabs.framework.commons.spigot.nms.packet.PacketBuilder;

/**
 * Created by Yannic Rieger on 15.07.2017.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public class EntityHeadRotationBuilder extends PacketBuilder<EntityHeadRotationPacket> {

  /** @param version */
  public EntityHeadRotationBuilder(MinecraftVersion version) {
    this.assignPacketClass(version, "entityheadrotation", "WrapperPlayServerEntityHeadRotation");
  }

  /** */
  public EntityHeadRotationBuilder() {
    this.assignPacketClass(
        MinecraftVersion.formString(LabsReflection.getVersion()),
        "entityheadrotation",
        "WrapperPlayServerEntityHeadRotation");
  }

  /**
   * @param yaw
   * @return
   */
  public EntityHeadRotationBuilder setHeadYaw(Number yaw) {
    this.packet.setHeadYaw(yaw);
    return this;
  }

  /**
   * @param id
   * @return
   */
  public EntityHeadRotationBuilder setEntityId(Number id) {
    this.packet.setEntityId(id);
    return this;
  }
}
