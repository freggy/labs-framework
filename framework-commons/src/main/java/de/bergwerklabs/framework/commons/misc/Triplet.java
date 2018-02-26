package de.bergwerklabs.framework.commons.misc;

public class Triplet<A, B, C> {

    private final A value1;
    private final B value2;
    private final C value3;

    public Triplet(A value1, B value2, C value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public A getValue1() {
        return value1;
    }

    public B getValue2() {
        return value2;
    }

    public C getValue3() {
        return value3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triplet<?, ?, ?> triplet = (Triplet<?, ?, ?>) o;

        return (value1 != null ? value1.equals(triplet.value1) : triplet.value1 == null)
                && (value2 != null ? value2.equals(triplet.value2) : triplet.value2 == null)
                && (value3 != null ? value3.equals(triplet.value3) : triplet.value3 == null);
    }

    @Override
    public int hashCode() {
        int result = value1 != null ? value1.hashCode() : 0;
        result = 31 * result + (value2 != null ? value2.hashCode() : 0);
        result = 31 * result + (value3 != null ? value3.hashCode() : 0);
        return result;
    }
}
