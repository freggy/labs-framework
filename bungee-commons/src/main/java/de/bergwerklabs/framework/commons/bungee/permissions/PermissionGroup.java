package de.bergwerklabs.framework.commons.bungee.permissions;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Yannic Rieger on 29.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public enum PermissionGroup {
    ADMIN(9),
    SR_DEV(37),
    SR_BULDER(7),
    SR_MOD(6),
    DEV(8),
    BULDER(6),
    MOD(4),
    F_MOD(15),
    YOUTUBER(3),
    PREMIUM(2),
    PLAYER(1);

    public int getGroupId() {
        return groupId;
    }

    private int groupId;

    PermissionGroup(int groupId) {
        this.groupId = groupId;
    }

    public static PermissionGroup getById(int id) {
        return Arrays.stream(values()).filter(permissionGroup -> permissionGroup.groupId == id).findFirst().orElse(PLAYER);
    }
}
