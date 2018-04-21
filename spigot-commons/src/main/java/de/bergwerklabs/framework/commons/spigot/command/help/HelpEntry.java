package de.bergwerklabs.framework.commons.spigot.command.help;

/**
 * Created by Yannic Rieger on 12.10.2017.
 *
 * <p>Entry that represents a command.
 *
 * @author Yannic Rieger
 */
public class HelpEntry {

  private String description, usage;

  /**
   * @param description description of a command.
   * @param usage usage of the command e.g /say [message]
   */
  public HelpEntry(String description, String usage) {
    this.description = description;
    this.usage = usage;
  }

  /** Description of a command. */
  public String getDescription() {
    return description;
  }

  /** Usage of the command e.g /say [message] */
  public String getUsage() {
    return usage;
  }
}
