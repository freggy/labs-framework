package de.bergwerklabs.framework.party.client.command;

import de.bergwerklabs.framework.party.client.command.manager.ChildCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by Yannic Rieger on 04.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class PartyLeaveCommand implements ChildCommand {

    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}
