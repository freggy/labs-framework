package de.bergwerklabs.framework.commons.spigot.nms.packet.entitiyheadrotation;

import de.bergwerklabs.framework.commons.spigot.nms.Version;
import de.bergwerklabs.framework.commons.spigot.nms.packet.Packet;

/**
 * Created by Yannic Rieger on 15.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class EntityHeadRotationBuilder {

    private EntityHeadRotationPacket packet;

    public EntityHeadRotationBuilder(Version version) {
        try {
            this.packet = (EntityHeadRotationPacket) Class.forName("de.bergwerklabs.framework.commons.spigot.nms.packet.entitiyheadrotation." + version.name() + ".WrapperPlayServerEntityHeadRotation").newInstance();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public EntityHeadRotationBuilder setHeadYaw(Number yaw) {
        this.packet.setHeadYaw(yaw);
        return this;
    }

    public EntityHeadRotationBuilder setEntityId(Number id) {
        this.packet.setEntityId(id);
        return this;
    }

    public Packet build() {
        return this.packet;
    }

}
