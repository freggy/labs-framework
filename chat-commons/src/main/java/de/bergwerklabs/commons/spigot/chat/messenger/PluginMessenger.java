package de.bergwerklabs.commons.spigot.chat.messenger;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Class that provides methods for sending messages to players with a specified prefix.
 *
 * @author Yannic
 */
public class PluginMessenger {

    /**
     * Gets the prefix.
     */
    public String getPrefix() { return this.prefix; }

    private String prefix;

    /**
     * @param name The name that will be used in the prefix.
     */
    public PluginMessenger(String name) {
        this.prefix = "§6>> §e" + name + " §6❘§7 "; // TODO: use utf8 symbol instead of '|', make configurable  in
        // framework config.
    }

    /**
     * Sends a message to the specified {@link Player}.
     *
     * @param message Message to send
     * @param player  {@link Player} to send the message to.
     */
    public void message(String message, Player player) {
        player.sendMessage(this.prefix + message);
    }

    /**
     * Sends a given message to the specified {@link Player}s.
     *
     * @param message Message to send.
     * @param players Players to send the message to.
     */
    public void messageSome(String message, Player... players) {
        Arrays.stream(players).forEach(player -> player.sendMessage(this.prefix + message));
    }

    /**
     * Invokes {@link Consumer#accept(Object)} with an instance of the {@link ConsumerInfo} class.
     * This class provides the {@link Player} and the prefix that should be used when calling {@link Player#sendMessage(String)}.
     * The recommended way is to invoke {@link PluginMessenger#message(String, Player)} within {@link Consumer#accept(Object)}.
     *
     * @param func    {@link Consumer} used for providing more advanced behavior.
     * @param players Players to send the message to.
     */
    public void messageSome(Consumer<ConsumerInfo> func, Player... players) {
        Arrays.stream(players).forEach(player -> func.accept(new ConsumerInfo(this.prefix, player)));
    }

    /**
     * Sends a message to the {@link Player}s in the given {@link Collection}.
     *
     * @param message Message to send to the players.
     * @param players {@link Collection} of players.
     */
    public void messageSome(String message, Collection<? extends Player> players) {
        players.forEach(player -> player.sendMessage(this.prefix + message));
    }

    /**
     * Invokes {@link Consumer#accept(Object)} with an instance of the {@link ConsumerInfo} class on every player in the collection.
     *
     * @param func    {@link Consumer} used for providing more advanced behavior.
     * @param players {@link Collection} of players.
     */
    public void messageSome(Consumer<ConsumerInfo> func, Collection<? extends Player> players) {
        players.forEach(player -> func.accept(new ConsumerInfo(this.prefix, player)));
    }

    /**
     * Sends a message to all players on the server.
     *
     * @param message Message to broadcast.
     */
    public void messageAll(String message) {
        Bukkit.broadcastMessage(this.prefix + message);
    }

    /**
     * Invokes {@link Consumer#accept(Object)} with an instance of the {@link ConsumerInfo} class.
     * This class provides the {@link Player} and the prefix that should be used when calling {@link Player#sendMessage(String)}.
     *
     * @param func {@link Consumer} used for providing more advanced behavior.
     */
    public void messageAll(Consumer<ConsumerInfo> func) {
        Bukkit.getOnlinePlayers().forEach(player -> func.accept(new ConsumerInfo(this.prefix, player)));
    }
}
