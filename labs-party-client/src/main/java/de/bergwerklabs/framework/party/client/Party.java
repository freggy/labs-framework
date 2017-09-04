package de.bergwerklabs.framework.party.client;

import com.google.common.collect.Sets;
import de.bergwerklabs.framework.party.client.api.event.PlayerJoinPartyEvent;
import de.bergwerklabs.framework.party.client.api.event.PlayerLeavePartyEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Yannic Rieger on 04.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class Party {

    /**
     *
     */
    public UUID getOwner() {
        return owner;
    }

    /**
     *
     */
    public Set<UUID> getMembers() {
        return members;
    }

    /**
     *
     */
    public int getSize() { return 1 + this.members.size(); }

    private UUID owner;
    private Set<UUID> members;
    private PluginManager pluginManager = Bukkit.getPluginManager();

    public Party(UUID owner, Collection<UUID> members) {
        this.owner = owner;
        this.members = Sets.newHashSet(members);
    }

    /**
     *
     */
    public void disband() {

    }

    /**
     *
     * @param member
     */
    public void addMember(UUID member) {
        this.pluginManager.callEvent(new PlayerJoinPartyEvent());
        this.members.add(member);
    }

    public void removeMember(UUID member) {

        if (member.equals(owner)) {

        }
        else {

        }

        this.pluginManager.callEvent(new PlayerLeavePartyEvent());
    }

    /**
     *
     * @param player
     * @param lefParty
     */
    void changeOwner(Player player, boolean lefParty) {
        if (lefParty) {
            this.owner = player.getUniqueId();
        }
        else {
            this.members.add(this.owner);
            this.members.remove(player.getUniqueId());
            this.owner = player.getUniqueId();
        }
        this.pluginManager.callEvent(new PlayerLeavePartyEvent());
    }
}
