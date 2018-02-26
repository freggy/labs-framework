package de.bergwerklabs.framework.permissionbridge;

/**
 * Created by Yannic Rieger on 27.02.2018.
 * <p>
 *
 * @author Yannic Rieger
 */
public class GroupId {

    private int id;

    public GroupId(int id) {
        this.id = id;
    }

    public int getIntValue() {
        return id;
    }

    public boolean isSocialMedia() {
        return this.id >= 300 && this.id <= 399;
    }

    public boolean isTeam() {
        return this.id >= 200 && this.id <= 299;
    }


    public boolean isDefault() {
        return this.id >= 100 && this.id <= 199;
    }

    public boolean isPremium() {
        return this.id >= 400 && this.id <= 499;
    }
}
