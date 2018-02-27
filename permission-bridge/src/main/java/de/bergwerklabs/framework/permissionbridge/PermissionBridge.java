package de.bergwerklabs.framework.permissionbridge;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Created by Yannic Rieger on 27.02.2018.
 * <p>
 * Interface for providing basic functionality, that will be provided by various permission systems.
 *
 * @author Yannic Rieger
 */
public interface PermissionBridge {

    /**
     * Gets the group id as an int. This method is temporary.
     *
     * @param uuid {@link UUID} of the player.
     */
    int getGroupIdAsInt(@NotNull UUID uuid);

    /**
     * A {@link GroupId} object.
     *
     * @param uuid {@link UUID} of the player.
     */
    GroupId getGroupAsObject(@NotNull UUID uuid);

    /**
     * Gets the group prefix.
     *
     * @param uuid {@link UUID} of the player.
     */
    String getGroupPrefix(@NotNull UUID uuid);

    /**
     * Gets the group suffix.
     *
     * @param uuid {@link UUID} of the player.
     */
    String getGroupSuffix(@NotNull UUID uuid);
}
