package de.bergwerklabs.framework.commons.spigot.pluginmessage;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Created by Yannic Rieger on 08.10.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class PluginMessages {

    /**
     *
     * @param plugin
     * @param option
     * @param args
     */
    public static void sendPluginMessage(Plugin plugin, PluginMessageOption option, String... args) {
        plugin.getServer().sendPluginMessage(plugin, "BungeeCord", writeData(option, args));
    }

    /**
     *
     * @param player
     * @param plugin
     * @param option
     * @param args
     */
    public static void sendPluginMessage(Player player, Plugin plugin, PluginMessageOption option, String... args) {
        player.sendPluginMessage(plugin, "BungeeCord", writeData(option, args));
    }

    /**
     *
     * @param option
     * @param args
     * @return
     */
    private static byte[] writeData(PluginMessageOption option, String... args) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF(option.getId());
        for (String argument : args) output.writeUTF(argument);
        return output.toByteArray();
    }
}
