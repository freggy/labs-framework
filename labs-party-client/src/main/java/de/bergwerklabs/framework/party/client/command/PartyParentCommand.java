package de.bergwerklabs.framework.party.client.command;

import de.bergwerklabs.framework.party.client.command.manager.ChildCommand;
import de.bergwerklabs.framework.party.client.command.manager.ParentCommand;

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
