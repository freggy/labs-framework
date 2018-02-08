package de.bergwerklabs.framework.bedrock.api.voting;

import com.google.common.collect.Ordering;
import de.bergwerklabs.commons.spigot.chat.messenger.PluginMessenger;
import de.bergwerklabs.framework.bedrock.api.event.voting.VotingStoppedEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Yannic Rieger on 08.02.2018.
 * <p>
 *
 * @author Yannic Rieger
 */
public class MapVoting {

    private Inventory votingInventory;
    private int displayCount;
    private List<MapEntry> entries;
    private List<MapEntry> pickedMaps = new ArrayList<>();
    private PluginMessenger messenger;
    private Listener inventoryListener = new VotingEventHandlers(this);

    public MapVoting(List<MapEntry> entries, int displayCount, PluginMessenger messenger) {
        this.entries = entries;
        this.displayCount = displayCount;
        this.messenger = messenger;
    }

    public void startVoting() {
        Random random = new SecureRandom();
        do {
            MapEntry entry = entries.get(random.nextInt(entries.size() - 1));
            entries.remove(entry);
            this.pickedMaps.add(entry);
            displayCount -= 1;
        }
        while (displayCount != 0);
    }

    public void stopVoting() {
        this.messenger.messageAll("§bDie Mapabstimmung ist beendet!");
        Bukkit.getPluginManager().callEvent(new VotingStoppedEvent(pickedMaps, Ordering.natural().max(pickedMaps)));
    }

    public Listener getInventoryListener() {
        return inventoryListener;
    }

    public PluginMessenger getMessenger() {
        return messenger;
    }

    public List<MapEntry> getPickedMaps() {
        return pickedMaps;
    }

    public Inventory getVotingInventory() {
        return votingInventory;
    }
}
