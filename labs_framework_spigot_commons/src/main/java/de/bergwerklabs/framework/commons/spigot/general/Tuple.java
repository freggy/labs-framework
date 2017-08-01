package de.bergwerklabs.framework.commons.spigot.general;

/**
 * Created by Yannic Rieger on 01.05.2017.
 * <p> Class represents a pair of two different values. </p>
 * @author Yannic Rieger
 */
public class Tuple<A, B> {

    /**
     * Returns value1
     */
    public A getValue1() { return this.value1; }

    /**
     * Returns value2
     */
    public B getValue2() { return this.value2; }

    private A value1;
    private B value2;

    /**
     * @param value1 Some value
     * @param value2 Some value
     */
    public Tuple(A value1, B value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public boolean equals(Object o) {
        Tuple tuple = (Tuple)o;
        return tuple != null && tuple.getValue2().equals(this.getValue2()) && tuple.getValue1().equals(this.getValue1());
    }
}
