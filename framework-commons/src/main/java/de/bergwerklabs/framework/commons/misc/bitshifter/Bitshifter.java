package de.bergwerklabs.framework.commons.misc.bitshifter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class allowing low level bit operations.
 *
 * @author Benedikt Wüller
 * @param <FlagType> the type of the flags to shift
 */
public abstract class Bitshifter<FlagType> {

    private final List<Object> entries = new ArrayList<>();

    protected FlagType flags;

    /**
     * Initializes a new bit shifter.
     *
     * @param initialFlags initial flag values
     * @param entries entries to map the values to
     *
     * @throws IllegalArgumentException if there are more entries than allowed. See {@code getMaxEntryCount()}.
     */
    public Bitshifter(FlagType initialFlags, Object... entries) {
        this.flags = initialFlags;
        if (entries.length > getMaxEntryCount()) {
            throw new IllegalArgumentException("You cannot add more than " + getMaxEntryCount() + " entries.");
        }
        Collections.addAll(this.entries, entries);
    }

    /**
     * Adds an entry to the end of the entry map.
     *
     * @param object the entry to add
     *
     * @throws IllegalArgumentException if there are more entries than allowed. See {@code getMaxEntryCount()}.
     */
    public final void addEntry(Object object) {
        if (entries.size() == getMaxEntryCount()) {
            throw new IllegalArgumentException("You cannot add more than " + getMaxEntryCount() + " entries.");
        }
        this.entries.add(object);
    }

    /**
     * Returns the current flag values.
     *
     * @return the flag values
     */
    public final FlagType getFlags() {
        return flags;
    }

    /**
     * Sets the flag values.
     *
     * @param flags the flag values
     */
    public final void setFlags(FlagType flags) {
        this.flags = flags;
    }

    protected final FlagType getFlagByEntry(Object entry) {
        if (!entries.contains(entry)) {
            throw new IllegalArgumentException("The given entry does not exist.");
        }
        return getFlag(entries.indexOf(entry));
    }

    /**
     * Returns whether the flag for the given entry is set or not.
     *
     * @param entry the entry to check for
     * @return whether the flag is set
     */
    public abstract boolean isFlagSet(Object entry);

    /**
     * Sets the flag associated to the given entry.
     *
     * @param entry the entry to set the flag for
     */
    public abstract void setFlag(Object entry);

    /**
     * Removes the flag associated to the given entry.
     *
     * @param entry the entry to unset the flag for
     */
    public abstract void unsetFlag(Object entry);

    protected abstract FlagType getFlag(int shift);

    /**
     * Returns the maximum of allowed entries for this bit shifter. You must not add more entries.
     *
     * @return the amount of entries
     */
    public abstract int getMaxEntryCount();
}
