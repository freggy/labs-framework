package de.bergwerklabs.framework.commons.bungee.command;

import net.md_5.bungee.api.CommandSender;

/**
 * Created by Yannic Rieger on 28.11.2017.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public interface BungeeCommand {

  String getDescription();

  String getUsage();

  String getName();

  void execute(CommandSender sender, String[] args);
}
