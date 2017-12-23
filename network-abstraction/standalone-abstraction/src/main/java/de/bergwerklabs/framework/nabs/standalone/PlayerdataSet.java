package de.bergwerklabs.framework.nabs.standalone;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Yannic Rieger on 20.12.2017.
 * <p>
 * Contains all data specific to a player.
 *
 * @author Benedikt Wüller
 */
public interface PlayerdataSet {

    enum Status {
        INITIALIZED,
        ERROROUS_COULD_NOT_LOAD,
        ERROROUS_TIMED_OUT,
        SUCCESSFUL
    }

    /**
     * Pulls all entries for the given player and waits until a response has been received. If no response has been
     * received withing {@code timeout} in milliseconds, the request will be cancelled. The last status will be
     * overridden by this method.
     *
     * @param timeout time in milliseconds after which the request should be cancelled automatically.
     * @return the {@link Status} representing whether the request has been {@link Status#SUCCESSFUL} or not.
     */
    @NotNull Status loadAndWait(long timeout);

    /**
     * Pulls all entries for the given player and waits until a response has been received. If no response has been
     * received withing 3000 milliseconds, the request will be cancelled. The last status will be overridden by this
     * method.
     *
     * @return the {@link Status} representing whether the request has been {@link Status#SUCCESSFUL} or not.
     */
    @NotNull default Status loadAndWait() {
        return loadAndWait(3000);
    }

    /**
     * Pulls all entries for the given player asynchronously and replaces every entry which has been modified
     * locally since the last {@link PlayerdataSet#save()} call or replaces all entries regardless of changes if
     * {@code overwrite} is {@code true}. The last status will be overridden by this method.
     *
     * @param overwrite whether local changes should be overridden.
     */
    void update(boolean overwrite);

    /**
     * Pulls all entries for the given player asynchronously and replaces every entry which has been modified
     * locally since the last {@link PlayerdataSet#save()} call.
     * The last status will be overridden by this method.
     */
    default void update() {
        update(false);
    }

    /**
     * Pushes all changes made locally. If {@code update} is set to {@code true}, a {@link PlayerdataSet#update()} will
     * automatically be performed. If the last status was not {@link PlayerdataSet.Status#SUCCESSFUL}, the data will not
     * be pushed but a update can be performed regardless.
     *
     * @param update whether a update should be performed
     * @return whether the push has been successful.
     */
    boolean save(boolean update);

    /**
     * Pushes all changes made locally. {@link PlayerdataSet#update()} will automatically be performed afterwards. If
     * the last status was not {@link PlayerdataSet.Status#SUCCESSFUL}, the data will not be pushed but a update can be
     * performed regardless.
     *
     * @return whether the push has been successful.
     */
    default boolean save() {
        return save(true);
    }

    /**
     * Returns the last status set by any of the following methods:
     * {@link PlayerdataSet#save()}, {@link PlayerdataSet#update()}, {@link PlayerdataSet#loadAndWait()}
     *
     * @return the last status set
     */
    @NotNull Status getStatus();

    /**
     * The uuid for which the entries have been or will be loaded.
     *
     * @return the uuid
     */
    @NotNull UUID getTarget();

    /**
     * Returns a {@link PlayerdataGroup} for the given key.
     *
     * @param group the key to get the group for
     * @return the group or a new group if there wasn't any
     */
    @NotNull PlayerdataGroup getGroup(String group);

    /**
     * Returns all groups that are assigned to this player.
     *
     * @return the defined group keys
     */
    @NotNull Set<String> getDefinedGroups();

    /**
     * Returns whether a group key has a assigned group.
     *
     * @param group the group key to check for
     * @return whether the group key has a assigned group
     */
    default boolean definesGroup(String group) {
        return getDefinedGroups().contains(group);
    }

    /**
     * Returns a object representing player specific network settings.
     *
     * @return the unique settings object
     */
    @NotNull PlayerSettings getSettings();
}
