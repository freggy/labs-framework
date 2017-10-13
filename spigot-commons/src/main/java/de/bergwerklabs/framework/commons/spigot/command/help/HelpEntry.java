package de.bergwerklabs.framework.commons.spigot.command.help;

/**
 * Created by Yannic Rieger on 12.10.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class HelpEntry {

    private String description, usage;

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public HelpEntry(String description, String usage) {
        this.description = description;
        this.usage = usage;
    }
}
