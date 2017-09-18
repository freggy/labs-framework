package de.bergwerklabs.gameservice.statistic;

import java.util.function.Function;

/**
 * Created by Yannic Rieger on 19.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class Statistic {

    public Function<Object, Object> getDataValueTransformer() {
        return dataValueTransformer;
    }

    public String getKey() {
        return key;
    }

    private String key;
    private Function<Object, Object> dataValueTransformer;
    private Object dataValue;

    public Statistic(String key, Function<Object, Object> dataValueSupplier) {
        this.key = key;
        this.dataValueTransformer = dataValueSupplier;
    }

    public Statistic load() {
        // TODO: load statistic from db.
        return this;
    }

    public boolean getAsBoolean() {
        return (boolean) this.dataValue;
    }

    public long getAsLong() {
        return (long) this.dataValue;
    }

    public int getAsInt() {
        return (int) this.dataValue;
    }

    public String getAsString() {
        return (String) this.dataValue;
    }

    public short getAsShort() {
        return (short) this.dataValue;
    }

    public float getAsFloat() {
        return (float) this.dataValue;
    }

    public double getAsDouble() {
        return (double) this.dataValue;
    }

    public byte getAsByte() {
        return (byte) this.dataValue;
    }

    public <T> T getByTransformer(Class<T> clazz) {
        if (this.dataValueTransformer != null) {
            return clazz.cast(this.dataValueTransformer.apply(this.dataValue));
        }
        else return null;
    }
}
