package de.bergwerklabs.framework.commons.spigot.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Yannic Rieger on 04.09.2017.
 *
 * <p>Class that represents a parent command.
 *
 * <p>Example:
 *
 * <p>Lets look at the party creation command: {@code /party create}
 *
 * <p>In this example the parent command would be /party. The sub command is create. A sub command
 * can in some cases also be a parent command.
 *
 * @author Yannic Rieger
 */
public abstract class ParentCommand implements CommandExecutor {

  private HashMap<String, ChildCommand> childCommands = new HashMap<>();
  private String command;
  private CommandExecutor executor;

  /**
   * @param command name of the command.
   * @param childCommands child commands of this command.
   */
  public ParentCommand(String command, ChildCommand... childCommands) {
    if (childCommands.length == 0)
      throw new IllegalArgumentException(
          "There cannot be 0 child commands, at least 1 has to be present");
    this.command = command;
    this.addChildCommands(childCommands);
  }

  /**
   * @param command name of the command.
   * @param executor {@link CommandExecutor} that will be invoked if /[command] is executed.
   * @param childCommands child commands of this command.
   */
  public ParentCommand(String command, CommandExecutor executor, ChildCommand... childCommands) {
    if (childCommands.length == 0)
      throw new IllegalArgumentException(
          "There cannot be 0 child commands, at least 1 has to be present");
    this.command = command;
    this.executor = executor;
    this.addChildCommands(childCommands);
  }

  /**
   * @param command name of the command.
   * @param executor {@link CommandExecutor} that will be invoked if /[command] is executed.
   * @param childCommands child commands of this command.
   */
  public ParentCommand(
      String command, CommandExecutor executor, Collection<ChildCommand> childCommands) {
    if (childCommands.size() == 0)
      throw new IllegalArgumentException(
          "There cannot be 0 child commands, at least 1 has to be present");
    ChildCommand[] commands = new ChildCommand[childCommands.size()];
    this.command = command;
    this.executor = executor;
    this.addChildCommands(commands);
  }

  @Override
  public boolean onCommand(
      CommandSender commandSender, Command command, String s, String[] strings) {
    if (s.equalsIgnoreCase(this.command) && this.executor != null && strings.length == 0) {
      this.executor.onCommand(commandSender, command, s, strings);
    } else {
      List<String> params = Arrays.asList(strings);
      this.childCommands
          .keySet()
          .stream()
          .filter(key -> key.equalsIgnoreCase(strings[0]))
          .findFirst()
          .ifPresent(
              key -> {
                this.childCommands
                    .get(key)
                    .onCommand(
                        commandSender,
                        command,
                        key,
                        Arrays.copyOfRange(strings, params.indexOf("leave") + 1, strings.length));
              });
    }
    return false;
  }

  /**
   * Adds the child commands to the map.
   *
   * @param childCommands commands to add.
   */
  private void addChildCommands(ChildCommand[] childCommands) {
    Arrays.stream(childCommands)
        .forEach(
            childCommand -> {
              this.childCommands.put(childCommand.getName(), childCommand);
            });
  }
}
