package de.bergwerklabs.framework.bedrock.api;

import org.bukkit.entity.Player;

/**
 * Created by Yannic Rieger on 14.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public interface PlayerFactory<T extends LabsPlayer> {

    /**
     * Creates an instance of {@link LabsPlayer}.
     *
     * @param player {@link Player} to create the wrapper of.
     * @return a new instance of {@link LabsPlayer}.
     */
    T createPlayer(Player player);
}
