package de.bergwerklabs.framework.party.client.api;

import de.bergwerklabs.framework.party.client.Party;
import de.bergwerklabs.framework.party.client.PartyBuilder;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Yannic Rieger on 04.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public interface PartyApi {

    /**
     *
     * @param player
     * @return
     */
    boolean isPartied(Player player);

    /**
     *
     * @param player
     * @return
     */
    boolean isPartyOwner(Player player);

    /**
     *
     * @param player
     * @return
     */
    boolean isPartyMember(Player player);

    /**
     *
     * @param player
     * @return
     */
    Party getParty(Player player);

    /**
     *
     * @param owner
     * @param members
     * @return
     */
    Party createParty(Player owner, List<String> members);

    /**
     *
     * @param owner
     * @return
     */
    Party createParty(Player owner);

    /**
     *
     */
    PartyBuilder getPartyBuilder();

}
