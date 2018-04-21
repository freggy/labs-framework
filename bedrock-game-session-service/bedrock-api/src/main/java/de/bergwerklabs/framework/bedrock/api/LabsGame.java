package de.bergwerklabs.framework.bedrock.api;

import de.bergwerklabs.framework.commons.spigot.chat.messenger.PluginMessenger;

/**
 * Created by Yannic Rieger on 07.07.2017.
 *
 * <p>This class represents a mini game. For more information head to the Confluence page.
 *
 * @author Yannic Rieger
 */
public abstract class LabsGame<T extends LabsPlayer> {

  // Do this to prevent NPEs
  protected PlayerRegistry<T> playerRegistry = new PlayerRegistry<>();
  protected PluginMessenger messenger;
  protected String name;

  /** @param name name of the game. */
  public LabsGame(String name) {
    this.messenger = new PluginMessenger(name);
    this.name = name;
  }

  /** Gets the {@link PlayerRegistry} for this game. */
  public PlayerRegistry<T> getPlayerRegistry() {
    return this.playerRegistry;
  }

  public String getName() {
    return name;
  }

  /** Gets the {@link PluginMessenger} associated with this game. */
  public PluginMessenger getMessenger() {
    return messenger;
  }

  /** Starts the game. */
  public abstract void start(PlayerRegistry<T> registry);

  /** Stops the game. */
  public abstract void stop();
}
