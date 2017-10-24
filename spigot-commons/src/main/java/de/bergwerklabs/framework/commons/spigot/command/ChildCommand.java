package de.bergwerklabs.framework.commons.spigot.command;

import org.bukkit.command.CommandExecutor;


/**
 * Created by Yannic Rieger on 04.09.2017.
 * <p>
 * Class represents a child command. For instance the sub command of {@code /party create} would be "create".
 *
 * @author Yannic Rieger
 */
public interface ChildCommand extends CommandExecutor {

    /**
     * Name of the sub command.
     */
    String getName();
}
