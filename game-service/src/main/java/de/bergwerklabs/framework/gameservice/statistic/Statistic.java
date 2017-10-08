package de.bergwerklabs.framework.gameservice.statistic;

import java.util.function.Function;

/**
 * Created by Yannic Rieger on 19.09.2017.
 * <p>
 * Represents a value saved in the database for a specific user.
 *
 * @author Yannic Rieger
 */
public class Statistic {

    /**
     * Gets the {@link Function} that will be called when custom data needs to be transformed.
     */
    public Function<Object, Object> getDataValueTransformer() {
        return dataValueTransformer;
    }

    /**
     * Gets the data access key.
     */
    public String getKey() {
        return key;
    }

    private String key;
    private Function<Object, Object> dataValueTransformer;
    private Object dataValue;

    /**
     * @param key               Key to access the data in the database (gamemode.category.subcategory.subsubcategory...)
     * @param dataValueSupplier Method that will be called when custom data needs to be transformed.
     *                          e.g. when a JSON string needs to be deserialized.
     */
    public Statistic(String key, Function<Object, Object> dataValueSupplier) {
        this.key = key;
        this.dataValueTransformer = dataValueSupplier;
    }

    /**
     * Loads the data from the database using the provided key.
     */
    void load() {
        // TODO: load statistic from db.
    }

    /**
     * Returns the data as a {@link Boolean}.
     */
    public boolean getAsBoolean() {
        return (boolean) this.dataValue;
    }

    /**
     * Returns the data as a {@link Long}.
     */
    public long getAsLong() {
        return (long) this.dataValue;
    }

    /**
     * Returns the data as a {@link Integer}.
     */
    public int getAsInt() {
        return (int) this.dataValue;
    }

    /**
     * Returns the data as a {@link String}.
     */
    public String getAsString() {
        return (String) this.dataValue;
    }

    /**
     * Returns the data as a {@link Short}.
     */
    public short getAsShort() {
        return (short) this.dataValue;
    }

    /**
     * Returns the data as a {@link Float}.
     */
    public float getAsFloat() {
        return (float) this.dataValue;
    }

    /**
     * Returns the data as a {@link Double}.
     */
    public double getAsDouble() {
        return (double) this.dataValue;
    }

    /**
     * Returns the data as a {@link Byte}.
     */
    public byte getAsByte() {
        return (byte) this.dataValue;
    }

    /**
     * Returns the custom value.
     *
     * @param clazz Type of the custom data.
     */
    public <T> T getByTransformer(Class<T> clazz) {
        if (this.dataValueTransformer != null) {
            return clazz.cast(this.dataValueTransformer.apply(this.dataValue));
        }
        else return null;
    }
}
