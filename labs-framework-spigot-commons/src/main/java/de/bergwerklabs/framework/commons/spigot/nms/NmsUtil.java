package de.bergwerklabs.framework.commons.spigot.nms;

import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;
import org.bukkit.entity.Player;

/**
 * Created by Yannic Rieger on 06.06.2017.
 * <p>
 * Contains methods for dealing with NMS.
 *
 * @author Yannic Rieger
 */
public class NmsUtil {

    /**
     * Gets an {@code IChatBaseComponent} with a given string.
     *
     * @param string String that will be contained in the {@code IChatBaseComponent}.
     * @return {@code IChatBaseComponent}.
     */
    public static Object getIChatBaseComponent(String string) {
        Class<?> cs = LabsReflection.getNmsClass("IChatBaseComponent$ChatSerializer");

        try {
            return cs.getMethod("a", String.class).invoke(null, "{\"text\": \"" + string + "\"}");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sends a packet over the the player connection.
     *
     * @param player         Player to send the packet to.
     * @param packetInstance Instance of a packet.
     */
    public static void sendPacketOverPlayerConnection(Player player, Object packetInstance) {
        try {
            Object handle = LabsReflection.getHandle(player);
            Object connection = LabsReflection.getField(handle.getClass(), "playerConnection").get(handle);
            connection.getClass().getMethod("sendPacket", LabsReflection.getNmsClass("Packet")).invoke(connection, packetInstance);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a fix yaw or  pitch.
     *
     * @param yawpitch pitch or yaw to convert.
     * @return converted yaw pitch value.
     */
    public static byte getFixYawPitch(float yawpitch) {
        return (byte) (yawpitch * 256.0F / 360.0F);
    }
}
