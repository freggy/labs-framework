package de.bergwerklabs.framework.commons.spigot.command.help;

import de.bergwerklabs.commons.spigot.chat.messenger.PluginMessenger;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by Yannic Rieger on 12.10.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class CommandHelpDisplay {

    private static final PluginMessenger MESSENGER = new PluginMessenger("Help");

    private List<HelpEntry> commands;

    public CommandHelpDisplay(HelpEntry... entries) {
        commands = Arrays.asList(entries);
    }

    public void displayHelp(Player player) {
        MESSENGER.message("§bCommands: ", player);
        commands.forEach(entry -> {
            MESSENGER.message( "§a■§b " + entry.getUsage() + "§7 - " + entry.getDescription(), player);
        });
    }
}
