package de.bergwerklabs.framework.commons.spigot.general;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by Yannic Rieger on 22.06.2017.
 * <p> This class is used for picking items randomly based on their weight.
 *
 * @author Yannic Rieger
 */
public class WeightedLootTable<T> {

    private final NavigableMap<Double, T> map = new TreeMap<>();
    private final Random random;
    private double total = 0;

    public WeightedLootTable() {
        this(new Random());
    }

    /**
     * @param random Used for choosing randomly.
     */
    public WeightedLootTable(Random random) {
        this.random = random;
    }

    /**
     * Adds a new entry to the table.
     *
     * @param weight Weight the entry should have.
     * @param result Associated object
     * @return another {@code WeightedLootTable} for method chaining.
     */
    public WeightedLootTable<T> add(double weight, T result) {
        if (weight <= 0) return this;
        total += weight;
        map.put(total, result);
        return this;
    }

    /**
     * Gets randomly weight-based chosen entry.
     * @return randomly weight-based chosen entry.
     */
    public T next() {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }
}
