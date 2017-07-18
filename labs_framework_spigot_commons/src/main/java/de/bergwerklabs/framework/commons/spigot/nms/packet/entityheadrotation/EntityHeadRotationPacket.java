package de.bergwerklabs.framework.commons.spigot.nms.packet.entityheadrotation;

import de.bergwerklabs.framework.commons.spigot.nms.packet.Packet;

/**
 * Created by Yannic Rieger on 15.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public interface EntityHeadRotationPacket extends Packet {

    Number getHeadYaw();

    Number getEntityId();

    void setHeadYaw(Number yaw);

    void setEntityId(Number id);
}
