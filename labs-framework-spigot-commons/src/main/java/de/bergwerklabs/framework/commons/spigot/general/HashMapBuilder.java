package de.bergwerklabs.framework.commons.spigot.general;

import java.util.HashMap;

/**
 * Created by Yannic Rieger on 20.05.2017.
 * <p> HashMap builder class. </p>
 * @author Yannic Rieger
 */
public class HashMapBuilder<K, V> {

    private HashMap<K, V> map;

    public HashMapBuilder() {
        this.map = new HashMap<>();
    }

    /**
     * Adds a new entry to the hash map.
     * @param key Key to access the value.
     * @param value Value that can be accessed via key.
     * @return another HashMapBuilder
     */
    public HashMapBuilder<K, V> addEntry(K key, V value) {
        map.put(key, value);
        return this;
    }

    public HashMap<K, V> create() {
        return this.map;
    }
}
