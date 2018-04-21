package de.bergwerklabs.framework.commons.bungee.chat;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by Yannic Rieger on 29.11.2017.
 *
 * <p>Same as the PluginMessenger in SpigotCommons but this time re-implemented for BungeeCord.
 *
 * @author Yannic Rieger
 */
public class PluginMessenger {

  private String prefix;

  /** @param name */
  public PluginMessenger(String name) {
    this.prefix = "§6>> §e" + name + " §6❘§7 ";
  }

  /**
   * @param message
   * @param player
   */
  public void message(String message, ProxiedPlayer player) {
    player.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(prefix + message));
  }
}
