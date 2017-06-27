package de.bergwerklabs.framework.commons.spigot.general;

import com.google.common.collect.Iterables;

import java.util.Iterator;

/**
 * Created by Yannic Rieger on 23.06.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class CylceIterator<T> {

    private Iterator<T> iterator;

    /**
     * @param collection
     */
    public CylceIterator(T collection) {
        this.iterator = Iterables.cycle(collection).iterator();
    }

    /**
     * @param args
     */
    public CylceIterator(T... args) {
        this.iterator = Iterables.cycle(args).iterator();
    }

    /**
     *
     * @return
     */
    public T next() {
        return this.iterator.next();
    }
}
