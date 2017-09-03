package de.bergwerklabs.framework.nick.api;

import org.bukkit.entity.Player;

import java.util.Set;

/**
 * Created by Yannic Rieger on 03.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public interface NickApi {

    /**
     *
     * @param player
     * @return
     */
    boolean isNicked(Player player);

    /**
     *
     * @param player
     * @return
     */
    String getRealName(Player player);

    /**
     *
     * @return
     */
    Set<NickInfo> getNickedPlayerInfos();

    /**
     *
     * @param player
     * @return
     */
    NickInfo getNickInfo(Player player);

    /**
     *
     * @param player
     */
    void removeNick(Player player);

    /**
     *
     * @param player
     * @return
     */
    NickInfo nickPlayer(Player player);
}
