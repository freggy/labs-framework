package de.bergwerklabs.framework.permissionbridge;

import java.util.UUID;

/**
 * Created by Yannic Rieger on 27.02.2018.
 * <p>
 *
 * @author Yannic Rieger
 */
public interface PermissionBridge {

    int getGroupIdAsInt(UUID uuid);

    GroupId getGroupAsObject(UUID uuid);

    String getGroupPrefix(UUID uuid);

    String getGroupSuffix(UUID uuid);
}
