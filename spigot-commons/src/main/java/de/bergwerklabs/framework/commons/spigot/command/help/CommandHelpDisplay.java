package de.bergwerklabs.framework.commons.spigot.command.help;

import com.google.common.collect.Sets;
import de.bergwerklabs.framework.commons.spigot.chat.messenger.PluginMessenger;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by Yannic Rieger on 12.10.2017.
 * <p>
 * Class responsible for displaying command help.
 *
 * @author Yannic Rieger
 */
public class CommandHelpDisplay {

    private static final PluginMessenger MESSENGER = new PluginMessenger("Help");

    private Set<HelpEntry> commands;

    /**
     * @param entries {@link HelpEntry}s that contain usage and description of a command.
     */
    public CommandHelpDisplay(HelpEntry... entries) {
        commands = Sets.newHashSet(entries);
    }

    /**
     * Displays the help to a player.
     * The following shows how commands would be displayed.
     *
     * <pre>
     *     §bCommands:
     *        §a■§b [usage] §7- [description]
     *        §a■§b [usage] §7- [description]
     *        §a■§b [usage] §7- [description]
     * </pre>
     *
     * @param player player to display the commands to.
     */
    public void displayHelp(Player player) {
        MESSENGER.message("§bCommands: ", player);
        commands.forEach(entry -> {
            MESSENGER.message( "§e■§b " + entry.getUsage() + "§7 - " + entry.getDescription(), player);
        });
    }
}
