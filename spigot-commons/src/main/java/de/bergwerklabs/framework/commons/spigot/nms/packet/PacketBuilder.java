package de.bergwerklabs.framework.commons.spigot.nms.packet;

import de.bergwerklabs.framework.commons.spigot.nms.MinecraftVersion;

/**
 * Created by Yannic Rieger on 17.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class PacketBuilder<T extends Packet> {

    protected T packet;

    // TODO: determine if serverside or clientside.

    protected void assignPacketClass(MinecraftVersion version, String packageString, String className) {
        try {
            this.packet = (T) Class.forName("de.bergwerklabs.framework.commons.spigot.nms.packet.serverside." + packageString + "." + version.name().toLowerCase() + "." + className).newInstance();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public T build() {
        return this.packet;
    }
}
