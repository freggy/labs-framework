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

    boolean isNicked(Player player);

    String getRealName(Player player);

    Set<NickInfo> getNickedPlayerInfos();

    NickInfo getNickInfo(Player player);

    void removeNick(Player player);

    NickInfo nickPlayer(Player player);
}
