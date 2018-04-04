package de.bergwerklabs.framework.commons.spigot.chat.messenger;

import org.bukkit.entity.Player;

/**
 * Data class containing basic message information.
 *
 * @author Yannic
 */
public class ConsumerInfo {

    /**
     * Gets the prefix.
     */
    public String getPrefix() { return this.prefix; }

    /**
     * Gets the {@link Player}.
     */
    public Player getPlayer() { return this.player; }

    private String prefix;
    private Player player;

    /**
     * @param prefix Prefix that should be used.
     * @param player {@link Player} to send a message to.
     */
    public ConsumerInfo(String prefix, Player player) {
        this.prefix = prefix;
        this.player = player;
    }
}
