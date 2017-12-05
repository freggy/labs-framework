package de.bergwerklabs.framework.commons.spigot.title;

import com.comphenix.protocol.wrappers.WrappedChatComponent;
import de.bergwerklabs.framework.commons.spigot.nms.packet.v1_8.WrapperPlayServerChat;
import org.bukkit.Bukkit;

/**
 * Created by Yannic Rieger on 18.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class ActionbarTitle {

    /**
     *
     * @param text
     */
    public static void broadcastTitle(String text) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            WrapperPlayServerChat chat = new WrapperPlayServerChat();
            chat.setMessage(WrappedChatComponent.fromText(text));
            chat.setPosition((byte)2);
            chat.sendPacket(player);
        });
    }

}
