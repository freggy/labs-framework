package de.bergwerklabs.framework.bedrock.api.voting;

import com.google.common.primitives.Ints;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Yannic Rieger on 08.02.2018.
 * <p>
 *
 * @author Yannic Rieger
 */
public class MapEntry implements Comparable<MapEntry> {
    private ItemStack displayItem;
    private String name;
    private Set<UUID> voters;

    public MapEntry(ItemStack displayItem, String name) {
        this.displayItem = displayItem;
        this.name = name;
        this.voters = new HashSet<>();
    }

    @Override
    public int compareTo(@NotNull MapEntry mapEntry) {
        return Ints.compare(voters.size(), mapEntry.getVoters().size());
    }

    public Set<UUID> getVoters() {
        return Collections.unmodifiableSet(this.voters);
    }

    public String getName() {
        return name;
    }

    public ItemStack getDisplayItem() {
        return displayItem;
    }

    public void addVoter(UUID uuid) {
        this.voters.add(uuid); // Should prevent duplicates since its a hash set.
        this.getDisplayItem().setAmount(this.voters.size());
    }

    void removeVoter(UUID uuid) {
        this.voters.remove(uuid);
        this.getDisplayItem().setAmount(this.voters.size());
    }
}
