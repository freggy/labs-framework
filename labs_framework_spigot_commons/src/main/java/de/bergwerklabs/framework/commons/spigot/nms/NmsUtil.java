package de.bergwerklabs.framework.commons.spigot.nms;

import de.bergwerklabs.framework.commons.spigot.general.reflection.LabsReflection;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Yannic Rieger on 06.06.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class NmsUtil {

    /**
     *
     * @param string
     * @return
     */
    public static Object getIChatBaseComponent(String string) {
        Class<?> cs = LabsReflection.getNmsClass("IChatBaseComponent$ChatSerializer");

        try {
            return cs.getMethod("a", String.class).invoke(null, "{\"text\": \"" + string + "\"}");
        }
        catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param player
     * @param packetInstance
     */
    public static void sendPacketOverPlayerConnection(Player player, Object packetInstance) {

        try {
            Object handle = LabsReflection.getHandle(player);
            Object connection = LabsReflection.getField(handle.getClass(), "playerConnection").get(handle);
            connection.getClass().getMethod("sendPacket", LabsReflection.getNmsClass("Packet")).invoke(connection, packetInstance);
        }
        catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
