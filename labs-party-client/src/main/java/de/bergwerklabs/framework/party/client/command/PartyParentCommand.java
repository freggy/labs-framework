package de.bergwerklabs.framework.party.client.command;

import de.bergwerklabs.framework.party.client.command.manager.ChildCommand;
import de.bergwerklabs.framework.party.client.command.manager.ParentCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Set;

/**
 * Created by Yannic Rieger on 04.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class PartyParentCommand extends ParentCommand {

    public PartyParentCommand(String command, ChildCommand... childCommands) {
        super(command, childCommands);
    }
}
