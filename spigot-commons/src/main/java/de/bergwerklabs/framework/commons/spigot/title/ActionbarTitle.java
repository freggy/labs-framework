package de.bergwerklabs.framework.commons.spigot.title;

import com.comphenix.protocol.wrappers.WrappedChatComponent;
import de.bergwerklabs.framework.commons.spigot.nms.packet.v1_8.WrapperPlayServerChat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Yannic Rieger on 18.07.2017.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public class ActionbarTitle {

  /** @param text */
  public static void broadcastTitle(String text) {
    Bukkit.getOnlinePlayers()
        .forEach(
            player -> {
              WrapperPlayServerChat chat = new WrapperPlayServerChat();
              chat.setMessage(WrappedChatComponent.fromText(text));
              chat.setPosition((byte) 2);
              chat.sendPacket(player);
            });
  }

  public static void send(Player player, String text) {
    WrapperPlayServerChat chat = new WrapperPlayServerChat();
    chat.setMessage(WrappedChatComponent.fromText(text));
    chat.setPosition((byte) 2);
    chat.sendPacket(player);
  }
}
