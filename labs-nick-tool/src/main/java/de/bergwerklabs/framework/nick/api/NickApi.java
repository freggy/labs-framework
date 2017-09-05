package de.bergwerklabs.framework.nick.api;

import org.bukkit.entity.Player;

import java.util.Set;

/**
 * Created by Yannic Rieger on 03.09.2017.
 * <p>
 * Interface providing methods for nicking players and receiving nick information.
 *
 * @author Yannic Rieger
 */
public interface NickApi {

    /**
     * Checks whether a player is nicked or not.
     *
     * @param player Player to check.
     * @return a value indicating whether or not the player is nicked.
     */
    boolean isNicked(Player player);

    /**
     * Gets the real name of the player, {@code null} if he is not nicked.
     *
     * @param player Player to get the real name from.
     * @return the real name of the player, {@code null} if he is not nicked.
     */
    String getRealName(Player player);

    /**
     * Gets a list of {@link NickInfo}, {@code null} if he is not nicked.
     *
     * @return {@link NickInfo} of this player, {@code null} if he is not nicked.
     */
    Set<NickInfo> getNickedPlayerInfos();

    /**
     * Gets {@link NickInfo} of this player, {@code null} if he is not nicked.
     *
     * @param player Player to retrieve info from.
     * @return {@link NickInfo} of this player, {@code null} if he is not nicked.
     */
    NickInfo getNickInfo(Player player);

    /**
     * Removes the nick of a player.
     *
     * @param player Player to remove nick from.
     */
    void removeNick(Player player);

    /**
     * Nicks a player. Nicking a player changes the skin and the name to other players.
     * Party members will not see the change.
     *
     * @param player Player to nick.
     * @return the {@link NickInfo} object containing basic information.
     */
    NickInfo nickPlayer(Player player);
}
