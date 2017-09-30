package de.bergwerklabs.framework.commons.spigot.command;

import org.bukkit.command.CommandExecutor;


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
