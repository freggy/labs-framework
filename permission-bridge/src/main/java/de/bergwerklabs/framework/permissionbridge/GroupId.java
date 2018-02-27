package de.bergwerklabs.framework.permissionbridge;

/**
 * Created by Yannic Rieger on 27.02.2018.
 * <p>
 * Provides useful methods for interacting with the group id.
 * The group id consists of two parts: The rank id prefix and the rank number.
 *
 * <pre>
 *     104
 *     │└┴─ Rank Number
 *     └─── Rank ID Prefix
 * </pre>
 *
 * @author Yannic Rieger
 */
public class GroupId {

    private int id;

    /**
     * @param id int value of the group id.
     */
    public GroupId(int id) {
        this.id = id;
    }

    /**
     * Gets the group id as an integer.
     */
    public int getIntValue() {
        return id;
    }

    /**
     * Determines whether or not the player is in a social media subgroup. The range goes from 300 to 399.
     */
    public boolean isSocialMedia() {
        return this.id >= 300 && this.id <= 399;
    }

    /**
     * Determines whether or not the player is in a team subgroup. The range goes from 200 to 299.
     */
    public boolean isTeam() {
        return this.id >= 200 && this.id <= 299;
    }

    /**
     * Determines whether or not the player is in a default subgroup. The range goes from 100 to 199.
     */
    public boolean isDefault() {
        return this.id >= 100 && this.id <= 199;
    }

    /**
     * Determines whether or not the player is in a premium subgroup. The range goes from 400 to 4399.
     */
    public boolean isPremium() {
        return this.id >= 400 && this.id <= 499;
    }
}
