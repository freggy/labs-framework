package de.bergwerklabs.framework.permissionbridge;

import java.util.Set;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Yannic Rieger on 27.02.2018.
 *
 * <p>Interface for providing basic functionality, that will be provided by various permission
 * systems.
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
   * Gets the group prefix.
   *
   * @param groupName Name of the group.
   */
  String getGroupPrefix(@NotNull String groupName);

  /**
   * Gets the group suffix.
   *
   * @param uuid {@link UUID} of the player.
   */
  String getGroupSuffix(@NotNull UUID uuid);

  /**
   * Gets the group suffix.
   *
   * @param groupName Name of the group.
   */
  String getGroupSuffix(@NotNull String groupName);

  /**
   * Gets the name of primary group of a player.
   *
   * @param uuid players {@link UUID}.
   */
  String getPrimaryGroup(@NotNull UUID uuid);

  /** Gets all the available groups. */
  Set<String> getGroups();

  /**
   * Retrieves the specified group metadata.
   *
   * @param group group where the metadata is contained in.
   * @param key Key of the metadata.
   */
  @Nullable
  String getGroupMetadata(@NotNull String group, @NotNull String key);
}
