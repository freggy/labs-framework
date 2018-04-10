package de.bergwerklabs.framework.commons.bungee;

/**
 * Created by Yannic Rieger on 10.04.2018.
 * <p>
 *
 * @author Yannic Rieger
 */
public class Players {

    public static boolean isDefault(int rankId) {
        return rankId >= 100 && rankId <= 199;
    }

    public static boolean isPremium(int rankId) {
        return rankId >= 400 && rankId <= 499;
    }

    public static boolean isVipOrUp(int rankId) {
        return rankId >= 200 && rankId <= 399;
    }

}
