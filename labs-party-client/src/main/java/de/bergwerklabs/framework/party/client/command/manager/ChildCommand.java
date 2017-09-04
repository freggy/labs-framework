package de.bergwerklabs.framework.party.client.command.manager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;

import java.util.Set;

/**
 * Created by Yannic Rieger on 04.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public interface ChildCommand extends CommandExecutor {

    /**
     *
     * @return
     */
    String getName();
}
