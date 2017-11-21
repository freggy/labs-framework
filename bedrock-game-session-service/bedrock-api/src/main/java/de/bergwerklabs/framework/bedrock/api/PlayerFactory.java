package de.bergwerklabs.framework.bedrock.api;

import org.bukkit.entity.Player;

/**
 * Created by Yannic Rieger on 14.11.2017.
 * <p>
 * This class is used for producing wrapped player objects that extend {@link LabsPlayer}.
 * Every mini game should have an implementation of this class.
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
