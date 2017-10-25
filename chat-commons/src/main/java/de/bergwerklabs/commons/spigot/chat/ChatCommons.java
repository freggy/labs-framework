package de.bergwerklabs.commons.spigot.chat;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

/**
 * Created by Yannic Rieger on 25.09.2017.
 * <p>
 * Main class for the chat commons.
 *
 * @author Yannic Rieger
 */
public class ChatCommons extends JavaPlugin implements Listener {

    /**
     * Gets the fitting {@link ChatColor} by the given color code. Valid color codes are: e.g {@code §e} or {@code &e}
     *
     * @param colorCode can be {@code §e} or {@code &e} where {@code e} is the char specifying the color.
     * @return          optional that can contains the {@link ChatColor}.
     */
    public static Optional<ChatColor> chatColorFromColorCode(String colorCode) {
        for (ChatColor color : ChatColor.values()) {
            String colorString1 = ChatColor.COLOR_CHAR + String.valueOf(color.getChar()); // e.g §e
            String colorString2 = "&" + String.valueOf(color.getChar()); // e.g &e
            if (colorString1.equalsIgnoreCase(colorCode) || colorString2.equalsIgnoreCase(colorCode)) {
                return Optional.of(color);
            }
        }
        return Optional.empty();
    }
}
