package de.bergwerklabs.framework.bedrock.api;

import de.bergwerklabs.commons.spigot.chat.messenger.PluginMessenger;

/**
 * Created by Yannic Rieger on 07.07.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public abstract class LabsGame<T extends LabsPlayer> {

    /**
     * Gets the {@link PlayerRegistry} for this game.
     */
    public PlayerRegistry<T> getPlayerRegistry() { return this.playerRegistry; }

    protected PlayerRegistry<T> playerRegistry = new PlayerRegistry<>();
    protected PluginMessenger messenger;

    public LabsGame(String name) {
        this.messenger = new PluginMessenger(name);
    }

    public abstract void start();

    public abstract void stop();
}
