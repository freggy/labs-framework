package de.bergwerklabs.framework.nick;

import de.bergwerklabs.framework.commons.spigot.entity.npc.PlayerSkin;

import java.util.*;

/**
 * Created by Yannic Rieger on 03.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
class NickUtil {

    /**
     *
     * @return
     */
    static List<String> retrieveNickNames() {
        return new ArrayList<>();
    }

    /**
     *
     * @return
     */
    static List<PlayerSkin> retrieveSkins() {
        return new ArrayList<>();
    }

    /**
     *
     * @param nickComponent
     * @param takenNickNames
     * @return
     */
    static String getUniqueNickName(List<String> nickComponent, Set<String> takenNickNames) {
        Random random = new Random();
        String selected;

        do {
            selected = nickComponent.get(random.nextInt(nickComponent.size()));
        }
        while (takenNickNames.contains(selected));

        return selected;
    }
}
