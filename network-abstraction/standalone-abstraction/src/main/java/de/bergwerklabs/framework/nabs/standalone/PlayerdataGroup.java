package de.bergwerklabs.framework.nabs.standalone;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * Contains all key-value pairs for one of many groups in a {@link PlayerdataSet}.
 *
 * @author Benedikt Wüller
 */
public interface PlayerdataGroup {

    /**
     * Sets the value for the given key. Overrides any existing value for this key.
     *
     * @param key the key to set the value for
     * @param value the value to be set
     */
    void set(String key, Object value);

    /**
     * Returns the value as {@link String} if possible. If the value is {@code null}, the {@code defaultValue} will be returned.
     *
     * @param key the key to get the value for
     * @param defaultValue the value to return if there is no value set for the given key
     * @return the value or the {@code defaultValue}
     */
    String getString(String key, String defaultValue);

    /**
     * Returns the value as {@link String} if possible.
     *
     * @param key the key to get the value for
     * @return the value or {@code null} if no value is set
     */
    default String getString(String key) {
        return getString(key, null);
    }

    /**
     * Returns the value as {@link Integer} if possible. If the value is {@code null}, the {@code defaultValue} will be returned.
     *
     * @param key the key to get the value for
     * @param defaultValue the value to return if there is no value set for the given key
     * @return the value or the {@code defaultValue}
     */
    Integer getInteger(String key, Integer defaultValue);

    /**
     * Returns the value as {@link Integer} if possible.
     *
     * @param key the key to get the value for
     * @return the value or {@code null} if no value is set
     */
    default Integer getInteger(String key) {
        return getInteger(key, null);
    }

    /**
     * Returns the value as {@link Double} if possible. If the value is {@code null}, the {@code defaultValue} will be returned.
     *
     * @param key the key to get the value for
     * @param defaultValue the value to return if there is no value set for the given key
     * @return the value or the {@code defaultValue}
     */
    Double getDouble(String key, Double defaultValue);

    /**
     * Returns the value as {@link Double} if possible.
     *
     * @param key the key to get the value for
     * @return the value or {@code null} if no value is set
     */
    default Double getDouble(String key) {
        return getDouble(key, null);
    }

    /**
     * Returns the value as {@link Float} if possible. If the value is {@code null}, the {@code defaultValue} will be returned.
     *
     * @param key the key to get the value for
     * @param defaultValue the value to return if there is no value set for the given key
     * @return the value or the {@code defaultValue}
     */
    Float getFloat(String key, Float defaultValue);

    /**
     * Returns the value as {@link Float} if possible.
     *
     * @param key the key to get the value for
     * @return the value or {@code null} if no value is set
     */
    default Float getFloat(String key) {
        return getFloat(key, null);
    }

    /**
     * Returns the value as {@link Byte} if possible. If the value is {@code null}, the {@code defaultValue} will be returned.
     *
     * @param key the key to get the value for
     * @param defaultValue the value to return if there is no value set for the given key
     * @return the value or the {@code defaultValue}
     */
    Byte getByte(String key, Byte defaultValue);

    /**
     * Returns the value as {@link Byte} if possible.
     *
     * @param key the key to get the value for
     * @return the value or {@code null} if no value is set
     */
    default Byte getByte(String key) {
        return getByte(key, null);
    }

    /**
     * Returns the value as {@link Long} if possible. If the value is {@code null}, the {@code defaultValue} will be returned.
     *
     * @param key the key to get the value for
     * @param defaultValue the value to return if there is no value set for the given key
     * @return the value or the {@code defaultValue}
     */
    Long getLong(String key, Long defaultValue);

    /**
     * Returns the value as {@link Long} if possible.
     *
     * @param key the key to get the value for
     * @return the value or {@code null} if no value is set
     */
    default Long getLong(String key) {
        return getLong(key, null);
    }

    /**
     * Returns the value as {@link Short} if possible. If the value is {@code null}, the {@code defaultValue} will be returned.
     *
     * @param key the key to get the value for
     * @param defaultValue the value to return if there is no value set for the given key
     * @return the value or the {@code defaultValue}
     */
    Short getShort(String key, Short defaultValue);

    /**
     * Returns the value as {@link Short} if possible.
     *
     * @param key the key to get the value for
     * @return the value or {@code null} if no value is set
     */
    default Short getShort(String key) {
        return getShort(key, null);
    }

    /**
     * Returns the value as {@link Object} if possible. If the value is {@code null}, the {@code defaultValue} will be returned.
     *
     * @param key the key to get the value for
     * @param defaultValue the value to return if there is no value set for the given key
     * @return the value or the {@code defaultValue}
     */
    Object getObject(String key, Object defaultValue);

    /**
     * Returns the value as {@link Object} if possible.
     *
     * @param key the key to get the value for
     * @return the value or {@code null} if no value is set
     */
    default Object getObject(String key) {
        return getObject(key, null);
    }

    /**
     * Returns all defined keys for this group.
     *
     * @return the defined keys
     */
    @NotNull Set<String> getDefinedKeys();

    /**
     * Returns whether the given key has a value set.
     *
     * @param key the key to check for
     * @return whether a value is set
     */
    default boolean definesKey(String key) {
        return getDefinedKeys().contains(key);
    }

    /**
     * Returns all keys which have been modified since the last {@link PlayerdataSet#save()} call.
     *
     * @return the modified keys
     */
    @NotNull Set<String> getModifiedKeys();
}
