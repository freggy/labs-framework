package de.bergwerklabs.framework.commons.bungee.command.help;

import com.google.common.collect.Sets;
import de.bergwerklabs.framework.commons.bungee.chat.PluginMessenger;
import de.bergwerklabs.framework.commons.bungee.command.BungeeCommand;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Set;

/**
 * Created by Yannic Rieger on 04.12.2017.
 * <p>
 * Class responsible for displaying command help.
 *
 * @author Yannic Rieger
 */
public class CommandHelpDisplay {

    private Set<BungeeCommand> commands;

    /**
     * @param commands commands to add to the display.
     */
    public CommandHelpDisplay(Set<BungeeCommand> commands) {
        this.commands = commands;
    }

    /**
     * @param commands commands to add to the display.
     */
    public CommandHelpDisplay(BungeeCommand... commands) {
        this.commands = Sets.newHashSet(commands);
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
     * @param player    player to display the commands to.
     * @param messenger {@link PluginMessenger} that sends the messages.
     */
    public void display(ProxiedPlayer player, PluginMessenger messenger) {
        messenger.message("§bCommands: ", player);
        this.commands.forEach(command -> {
            player.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText("§e■§b " + command.getUsage() + "§7 - " + command.getDescription()));
        });
    }

}
