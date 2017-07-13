package de.bergwerklabs.framework.commons.spigot.npc;

import java.util.HashMap;

/**
 * Created by Yannic Rieger on 14.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class NpcManager {

    /**
     *
     */
    public static HashMap<Integer, Npc> getNpcs() { return npcs; }

    private static HashMap<Integer, Npc> npcs = new HashMap<>();

}
