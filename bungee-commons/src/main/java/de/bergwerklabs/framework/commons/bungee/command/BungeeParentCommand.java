package de.bergwerklabs.framework.commons.bungee.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.util.*;

/**
 * Created by Yannic Rieger on 28.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class BungeeParentCommand extends Command implements BungeeCommand {

    public String getDescription() {
        return this.description;
    }

    public String getUsage() {
        return this.usage;
    }

    public Collection<BungeeCommand> getSubCommands() { return this.childCommandsMap.values(); }

    private Map<String, BungeeCommand> childCommandsMap = new HashMap<>();
    private String usage, description;
    private BungeeCommand defaultCommand;

    public BungeeParentCommand(String name, String description, String usage, BungeeCommand defaultCommand, BungeeCommand... childCommands) {
        super(name);
        this.usage = usage;
        this.description = description;
        this.defaultCommand = defaultCommand;
        this.addChildCommands(childCommands);
    }

    public void execute(CommandSender commandSender, String[] args) {
        List<String> params = Arrays.asList(args);

        if (this.defaultCommand != null && args.length == 0) {
            this.defaultCommand.execute(commandSender, args);
        }
        else {
            this.childCommandsMap.keySet().stream()
                              .filter(key -> key.equalsIgnoreCase(args[0])).findFirst()
                              .ifPresent(key -> {
                                  this.childCommandsMap.get(key).execute(commandSender, Arrays.copyOfRange(args, params.indexOf(args[0]) + 1, args.length));
                              });
        }
    }

    /**
     * Adds the child commands to the map.
     *
     * @param childCommands commands to add.
     */
    private void addChildCommands(BungeeCommand[] childCommands) {
        Arrays.stream(childCommands).forEach(childCommand -> {
            this.childCommandsMap.put(childCommand.getName(), childCommand);
        });
    }
}
