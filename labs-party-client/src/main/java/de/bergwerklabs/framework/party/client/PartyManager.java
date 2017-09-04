package de.bergwerklabs.framework.party.client;

import de.bergwerklabs.framework.party.client.api.PartyApi;
import de.bergwerklabs.framework.party.client.api.event.PartyCreateEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.List;

/**
 * Created by Yannic Rieger on 04.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class PartyManager implements PartyApi {

    private PluginManager pluginManager = Bukkit.getPluginManager();


    @Override
    public boolean isPartied(Player player) {
        return false;
    }

    @Override
    public boolean isPartyOwner(Player player) {
        return false;
    }

    @Override
    public boolean isPartyMember(Player player) {
        return false;
    }

    @Override
    public Party getParty(Player player) {
        return null;
    }

    @Override
    public Party createParty(Player owner, List<String> members) {

        this.pluginManager.callEvent(new PartyCreateEvent());
        return null;
    }

    @Override
    public Party createParty(Player owner) {



        this.pluginManager.callEvent(new PartyCreateEvent());
        return null;
    }

    @Override
    public PartyBuilder getPartyBuilder() {
        return null;
    }
}
