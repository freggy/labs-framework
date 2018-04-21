package de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entityheadrotation;

import de.bergwerklabs.framework.commons.spigot.nms.packet.Packet;

/**
 * Created by Yannic Rieger on 15.07.2017.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public interface EntityHeadRotationPacket extends Packet {

  Number getHeadYaw();

  void setHeadYaw(Number yaw);

  Number getEntityId();

  void setEntityId(Number id);
}
